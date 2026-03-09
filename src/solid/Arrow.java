package solid;

import model.Part;
import model.PartType;
import model.Vertex;
import transforms.Col;

public class Arrow extends Solid {
    public Arrow() {
        // TODO: až bude celý řetězec, nechci souřadnice ve screen space
        vertexBuffer.add(new Vertex(200, 300, 0.5)); // v0
        vertexBuffer.add(new Vertex(250, 300, 0.5)); // v1
        vertexBuffer.add(new Vertex(250, 320, 0.5, new Col(0xff0000))); // v2
        vertexBuffer.add(new Vertex(230, 300, 0.5, new Col(0x00ff00))); // v3
        vertexBuffer.add(new Vertex(250, 280, 0.5, new Col(0x0000ff))); // v4

        addIndices(0, 1); // LINES
        addIndices(4, 3, 2); // TRIANGLES

        partBuffer.add(new Part(PartType.LINES, 0, 1));
        partBuffer.add(new Part(PartType.TRIANGLES, 2, 1));
    }
}
