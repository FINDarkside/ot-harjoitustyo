package tetris.dao;

import java.util.List;
import tetris.domain.Highscore;

public interface HighscoreDao {

    public void create(Highscore highscore) throws Exception;

    /**
     * Returns all highscores sorted descending by score.
     * 
     * @return List of all highscores, sorted descending by score.
     * @throws Exception 
     */
    public List<Highscore> getAll() throws Exception;
}
