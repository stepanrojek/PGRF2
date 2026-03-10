package renderer;

import model.Part;
import model.Vertex;
import rasterize.LineRasterizer;
import rasterize.TriangleRasterizer;
import solid.Solid;
import util.Lerp;

public class RendererSolid {
    private LineRasterizer lineRasterizer;
    private TriangleRasterizer triangleRasterizer;

    public RendererSolid(LineRasterizer lineRasterizer, TriangleRasterizer triangleRasterizer) {
        this.lineRasterizer = lineRasterizer;
        this.triangleRasterizer = triangleRasterizer;
    }

    public void render(Solid solid) {
        for (Part part : solid.getPartBuffer()) {
            switch (part.getType()) {
                case POINTS:
                    // TODO: points
                    break;
                case LINES:
                    int index = part.getStartIndex();
                    for (int i = 0; i < part.getCount(); i++) {
                        int indexA = solid.getIndexBuffer().get(index++);
                        int indexB = solid.getIndexBuffer().get(index++);

                        Vertex a = solid.getVertexBuffer().get(indexA);
                        Vertex b = solid.getVertexBuffer().get(indexB);

                        // TODO: vrcholy pronásobím MVP

                        // TODO: ořezání

                        // TODO: dehomog

                        // TODO: transformace do okna

                        // Rasterizace
                        lineRasterizer.rasterize(
                                (int) Math.round(a.getX()),
                                (int) Math.round(a.getY()),
                                (int) Math.round(b.getX()),
                                (int) Math.round(b.getY())
                        );
                    }
                    break;
                case TRIANGLES:
                    index = part.getStartIndex();
                    for (int i = 0; i < part.getCount(); i++) {
                        int indexA = solid.getIndexBuffer().get(index++);
                        int indexB = solid.getIndexBuffer().get(index++);
                        int indexC = solid.getIndexBuffer().get(index++);

                        Vertex a = solid.getVertexBuffer().get(indexA);
                        Vertex b = solid.getVertexBuffer().get(indexB);
                        Vertex c = solid.getVertexBuffer().get(indexC);

                        // TODO: vrcholy pronásobím MVP

                        // TODO: ořezání
                        //TODO:1. Fast clip
                        //2. Ořezání podle z
                        //TODO: proházet vrcholy podle z od max po min
                        double zMin = 0;
                        if(a.getZ() < zMin)
                            continue;

                        if(b.getZ() < zMin) {
                            Lerp<Vertex> lerp = new Lerp<>();

                            double tAB = (zMin - a.getZ()) / (b.getZ() - a.getZ());
                            double tAC = (zMin - a.getZ()) / (c.getZ() - a.getZ());

                            Vertex ab = lerp.lerp(a, b, tAB);
                            Vertex ac = lerp.lerp(a, c, tAC);

                            b = ab;
                            c = ac;
                            //TODO: najit novy trojuhelnik
                            //TODO: hledam vrchol AB, AC
                            //TODO: musim spocitat interpolacni koeficienty a pak najit vrcholy
                            //Vertex ab
                            //Vertex ac
                        }

                        if(c.getZ() < zMin) {
                            //TODO: najit 2 nove trojuhelniky
                            //TODO: rasterizovat
                        }

                        // TODO: dehomog

                        // TODO: transformace do okna

                        // Rasterizace
                        triangleRasterizer.rasterize(a, b, c);
                    }
                    break;
            }
        }
    }
}
