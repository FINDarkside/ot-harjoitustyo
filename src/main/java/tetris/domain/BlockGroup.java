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
            if (x < 0 != y < 0) {
                x *= -1;
                x += x < 0 ? 1 : -1;
                blockItem.setRelativeX(x);
            } else {
                y *= -1;
                y += y < 0 ? 1 : -1;
                blockItem.setRelativeY(y);
            }
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
