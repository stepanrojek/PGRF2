package rasterize;

import model.Vertex;
import raster.ZBuffer;
import transforms.Col;

public class TriangleRasterizer {
    private final ZBuffer zBuffer;

    public TriangleRasterizer(ZBuffer zBuffer) {
        this.zBuffer = zBuffer;
    }

    public void rasterize(Vertex a, Vertex b, Vertex c) {
        int ax = (int) Math.round(a.getX());
        int ay = (int) Math.round(a.getY());
        double az = a.getZ();

        int bx = (int) Math.round(b.getX());
        int by = (int) Math.round(b.getY());
        double bz = b.getZ();

        int cx = (int) Math.round(c.getX());
        int cy = (int) Math.round(c.getY());
        double cz = c.getZ();

        // TODO: Setridit podle y -> Ay <= By <= Cy (prohazovat vsechny souradnice bodu)

        // 1. část trojúhelníku
        for (int y = ay; y <= by; y++) {
            // Hrana AB
            double tAB = (y - ay) / (double) (by - ay);
            int xAB = (int) Math.round((1 - tAB) * ax + tAB * bx);
            //spočítat zAB
            double zAB = (1 - tAB) * az + tAB * bz;

            // Hrana AC
            double tAC = (y - ay) / (double) (cy - ay);
            int xAC = (int) Math.round((1 - tAC) * ax + tAC * cx);
            //spočítat zAC
            double zAC = (1 - tAC) * az + tAC * cz;

            // Kontrola, ze xAB < xAC, pokud ne, prohazuji
            if (xAB > xAC) {
                int tmp = xAB;
                xAB = xAC;
                xAC = tmp;

                double tmpZ = zAB;
                zAB = zAC;
                zAC = tmpZ;
            }

            for (int x = xAB; x <= xAC; x++) {
                double t = xAB == xAC ? 0.0 : (x - xAB) / (double) (xAC - xAB);
                double z = (1 - t) * zAB + t * zAC;
                zBuffer.setPixelWithZTest(x, y, z, new Col(0xff0000));
            }
        }

        // 2. cast trojuhelniku
        for (int y = by + 1; y <= cy; y++) {
            // Hrana BC
            double tBC = (y - by) / (double) (cy - by);
            int xBC = (int) Math.round((1 - tBC) * bx + tBC * cx);
            double zBC = (1 - tBC) * bz + tBC * cz;

            // Hrana AC
            double tAC = (y - ay) / (double) (cy - ay);
            int xAC = (int) Math.round((1 - tAC) * ax + tAC * cx);
            double zAC = (1 - tAC) * az + tAC * cz;

            if (xBC > xAC) {
                int tmp = xBC;
                xBC = xAC;
                xAC = tmp;

                double tmpZ = zBC;
                zBC = zAC;
                zAC = tmpZ;
            }

            for (int x = xBC; x <= xAC; x++) {
                double t = xBC == xAC ? 0.0 : (x - xBC) / (double) (xAC - xBC);
                double z = (1 - t) * zBC + t * zAC;
                zBuffer.setPixelWithZTest(x, y, z, new Col(0xff0000));
            }
        }
    }
}
