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

        // TODO: Setřídit podle y -> Ay <= By <= Cy (prohazovat všechny souřadnice bodů)

        // 1. část trojúhelníku
        for (int y = ay; y <= by; y++) {
            // Hrana AB
            double tAB = (y - ay) / (double) (by - ay);
            int xAB = (int) Math.round((1 - tAB) * ax + tAB * bx);
            // TODO: spočítat zAB
            double zAB = (1 - tAB) * az + tAB * bz;

            // Hrana AC
            double tAC = (y - ay) / (double) (cy - ay);
            int xAC = (int) Math.round((1 - tAC) * ax + tAC * cx);
            // TODO: spočítat zAC
            double zAC = (1 - tAC) * az + tAC * cz;

            // TODO: kontrola, že xAB < xAC, pokud ne, prohazuji
            if (xAB > xAC) {
                int tmp = xAB;
                xAB = xAC;
                xAC = tmp;
            }
            // napsat cyklus od xAB do xAC a obarvit pixely
            for (int x = xAB; x <= xAC; x++) {
                double t = (x - xAB) / (double) (xAC - xAB);
                // TODO: spočítat finální Z
                double z = (1 - t) * zAB + t * zAC;

                zBuffer.setPixelWithZTest(x, y, 0.5, new Col(0xff0000));
            }
        }

        // TODO: 2. část trojúhelníku
    }
}
