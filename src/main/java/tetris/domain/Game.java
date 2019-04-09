package tetris.domain;

public class Game {

    // Speed the block group drops at (blocks per second)
    private static final float VERTICAL_DROP_VELOCITY = 2;

    private GameBoard board;
    private boolean paused;
    private BlockGroup blockGroup;

    /**
     * Creates new game with given width and height.
     *
     * @param height Height of the game board in blocks
     * @param width Width of the game board in blocks
     */
    public Game(int height, int width) {
        this.board = new GameBoard(width, height);
    }

    /**
     * Updates game state.
     *
     * @param dt Time since last update (in milliseconds)
     */
    public void update(float dt) {
        if (paused) {
            return;
        }

        blockGroup.setY(blockGroup.getY() - VERTICAL_DROP_VELOCITY * dt);
        if (board.collidesWithStaticBlocks(blockGroup)) {
            blockGroup.setY((float) Math.floor(blockGroup.getY()) + 1);
            board.addBlockGroup(blockGroup);
            board.clearFullRows();
        }
    }

    /**
     * Moves all block groups that are falling. Block group is considered
     * falling, if none of its blocks have another block directly below that's
     * not falling and is not from the same group.
     *
     * @return true if there was at least one falling block group, otherwise
     * false
     */
    public boolean moveFallingBlockGroups() {
        boolean blocksFalling = false;
        for (BlockGroup group : board.getBlockGroups()) {
            if (this.moveBlockGroupIfFalling(group)) {
                blocksFalling = true;
            }
        }
        return blocksFalling;
    }

    /**
     * Moves the given block group down if it's falling. Block group is
     * considered falling, if none of its blocks have another block directly
     * below that's not falling and is not from the same group.
     *
     * @param group Group to move down if it's falling
     * @return true if the block group was falling, otherwise false
     */
    private boolean moveBlockGroupIfFalling(BlockGroup group) {
        boolean[][] usedCells = board.getUsedCells();
        for (Block block : group.getBlocks()) {
            usedCells[group.getBlockCellY(block)][group.getBlockCellX(block)] = false;
        }
        for (Block block : group.getBlocks()) {
            int x = group.getBlockCellX(block);
            int y = group.getBlockCellY(block);
            if (y <= 0 || usedCells[y][x]) {
                if (group.getY() != Math.floor(group.getY())) {
                    group.setY((float) Math.floor(group.getY()) + 1);
                }
                return false;
            }
        }
        group.setY(group.getY() - 1);
        return true;
    }
}
