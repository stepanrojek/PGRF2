package raster;

import transforms.Col;

public class ZBuffer {
    private final Raster<Col> imageBuffer;
    private final Raster<Double> depthBuffer;

    public ZBuffer(Raster<Col> imageBuffer) {
        this.imageBuffer = imageBuffer;
        this.depthBuffer = new DepthBuffer(imageBuffer.getWidth(), imageBuffer.getHeight());
    }

    public void setPixelWithZTest(int x, int y, double z, Col color) {
        // TODO: načtu hodnotu z depth bufferu
        // TODO: porovnám hodnotu s hodnout Z, která přišla do metody
        // TODO: podle podmíny
        // TODO: 1. nedělám nic
        // TODO: 2. obarvím pixel, updatuju depth buffer

        imageBuffer.setValue(x, y, color);
    }

}
