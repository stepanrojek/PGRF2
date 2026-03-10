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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Controller3D {
    private final Panel panel;
    private final ZBuffer zBuffer;
    private final LineRasterizer lineRasterizer;
    private final TriangleRasterizer triangleRasterizer;
    private final RendererSolid renderer;

    //textura
    private final BufferedImage texture;

    // Solids
    private final Solid arrow;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.zBuffer = new ZBuffer(panel.getRaster());
        this.lineRasterizer = new LineRasterizerGraphics(panel.getRaster());
        this.triangleRasterizer = new TriangleRasterizer(zBuffer);
        this.renderer = new RendererSolid(lineRasterizer, triangleRasterizer);

        //textures
        try {
            texture = ImageIO.read(new File("res/textures/kamen.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.arrow = new Arrow();

        initListeners();

        drawScene();
    }

    private void initListeners() {
        // TODO: Inicializace listenerů např. pohyb kamerou
        @Override
                public Col getColor(Vertex pixel) {
            int x = (int) Math.round(pixel.getUv().getX() * (texture.getWidth()-1));
            int y = (int) Math.round(pixel.getUv().getY() * (texture.getHeight()-1));
            if (x <)
            return new Col(texture.getRGB(x, y));
        }


        //TODO: Normala
        //TODO: Pozice svetla
        Point3D lightPosition = new Point3D(0, 0, 0.5);
        //TODO: Vektor ke svetlu = pozice svetla - pozice vertexu (vertex je raster)
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
