package model;

public class SolidPart {
    private final Topology topology;
    private final int primitiveCount;
    private final int startIndex;

    public SolidPart(final Topology topology, final int primitiveCount,final int startIndex) {
        this.primitiveCount = primitiveCount;
        this.startIndex = startIndex;
        this.topology = topology;
    }
}


