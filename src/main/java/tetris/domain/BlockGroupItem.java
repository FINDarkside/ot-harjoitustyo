package tetris.domain;

public class BlockGroupItem {

    private final Block block;
    private int relativeX;
    private int relativeY;

    /**
     * Creates new {@code BlockGroupItem} of the given block.
     * @param block Block this object contains
     * @param relativeX Relative x coordinate, used when rotating the group around (0,0)
     * @param relativeY Relative y coordinate, used when rotating the group around (0,0)
     */
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
