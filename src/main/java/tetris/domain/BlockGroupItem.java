package tetris.domain;

public class BlockGroupItem {

    private final Block block;
    private int relativeX;
    private int relativeY;

    public BlockGroupItem(Block block, int relativeX, int relativeY) {
        this.block = block;
        this.relativeX = relativeX;
        this.relativeY = relativeY;
    }

    public Block getBlock() {
        return block;
    }

    public int getRelativeX() {
        return relativeX;
    }

    public int getRelativeY() {
        return relativeY;
    }

    public void setRelativeX(int relativeX) {
        this.relativeX = relativeX;
    }

    public void setRelativeY(int relativeY) {
        this.relativeY = relativeY;
    }

}
