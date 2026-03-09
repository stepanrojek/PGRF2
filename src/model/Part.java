package model;

public class Part {
    private final PartType type;
    private final int startIndex;
    private final int count;

    public Part(PartType type, int startIndex, int count) {
        this.type = type;
        this.startIndex = startIndex;
        this.count = count;
    }

    public PartType getType() {
        return type;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getCount() {
        return count;
    }
}
