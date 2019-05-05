package tetris.dao;

import java.util.List;
import tetris.domain.Highscore;

public interface HighscoreDao {

    /**
     * Creates new highscore.
     *
     * @param highscore Score to add
     * @throws Exception if exception occurs when adding highscore
     */
    public void save(Highscore highscore) throws Exception;

    /**
     * Returns all highscores sorted descending by score.
     *
     * @return List of all highscores, sorted descending by score.
     * @throws Exception if exception occurs when getting all scores
     */
    public List<Highscore> getAll() throws Exception;

}
