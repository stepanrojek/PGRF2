package controller;

import model.Vertex;
import raster.ZBuffer;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerGraphics;
import rasterize.TriangleRasterizer;
import renderer.RendererSolid;
import solid.Arrow;
import solid.Solid;
import transforms.*;
import view.Panel;


public class Controller3D {
    private final Panel panel;
    private final ZBuffer zBuffer;
    private final LineRasterizer lineRasterizer;
    private final TriangleRasterizer triangleRasterizer;
    private final RendererSolid renderer;

    // Solids
    private final Solid arrow;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.zBuffer = new ZBuffer(panel.getRaster());
        this.lineRasterizer = new LineRasterizerGraphics(panel.getRaster());
        this.triangleRasterizer = new TriangleRasterizer(zBuffer);
        this.renderer = new RendererSolid(lineRasterizer, triangleRasterizer);

        this.arrow = new Arrow();

        initListeners();

        drawScene();
    }

    private void initListeners() {
        // TODO: Inicializace listenerů např. pohyb kamerou
    }

    private void drawScene() {
        panel.getRaster().clear();

//        triangleRasterizer.rasterize(
//                new Vertex(400,0,0.5),
//                new Vertex(0,300,0.5),
//                new Vertex(799,599,0.5)
//        );

        renderer.render(arrow);

        panel.repaint();
    }
}
