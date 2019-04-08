package tetris.domain;

public class BlockGroup {

    private BlockGroupItem[] blocks;
    private float x;
    private float y;

    public BlockGroup(BlockGroupItem[] blocks, float x, float y) {
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

}
