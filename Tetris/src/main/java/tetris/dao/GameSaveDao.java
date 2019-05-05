package tetris.dao;

import java.util.List;
import tetris.domain.GameSaveData;

public interface GameSaveDao {

    /**
     * Saves game.
     *
     * @param game Game to save.
     * @return id for the created save
     * @throws java.lang.Exception 
     */
    public long save(GameSaveData game) throws Exception;

    /**
     * Returns all saved games.
     *
     * @return List of all saved games
     * @throws java.lang.Exception 
     */
    public List<GameSaveData> getAll() throws Exception;

    /**
     * Removes save with given id.
     *
     * @param id id of save to delete
     * @throws java.lang.Exception 
     */
    public void delete(long id) throws Exception;
}
