package tetris.domain;

public class BlockGroup {

    private BlockGroupItem[] blocks;
    private int x;
    private int y;

    public BlockGroup(BlockGroupItem[] blocks, int x, int y) {
        this.blocks = blocks;
        this.x = x;
        this.y = y;
    }

    /**
     * Rotates the block group clockwise
     */
    public void rotateClockwise() {
        for (BlockGroupItem blockItem : blocks) {
            int x = blockItem.getRelativeX();
            int y = blockItem.getRelativeY();
            blockItem.setRelativeX(y);
            blockItem.setRelativeY(-x - 1);
        }
    }

    public BlockGroupItem[] getBlocks() {
        return blocks;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
