package model;

import transforms.Mat4;
import transforms.Mat4Identity;

import java.util.List;

public class Arrow extends Solid {
    public Arrow() {
        super(
                List.of(
                        new Vertex(200, 400, 0.5),

                ),
                List.of(
                        0, 1,2,3,4

                ),
                List.of(
                        new SolidPart(Topology.LINE_LIST, 4, 0)
                ),
                new Mat4Identity());
    }
}
