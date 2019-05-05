package tetris.dao;

import java.sql.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import tetris.domain.Highscore;

public class DbHighscoreDaoTest {

    private Connection connection;
    private DbHighscoreDao hsDao;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        hsDao = new DbHighscoreDao(connection);
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    // @Test
    public void canAddScore() throws SQLException {
        hsDao.save(new Highscore(0, "test", "2018-03-01"));
        assertEquals(1, hsDao.getAll().size());
    }

    // @Test
    public void canAddMultipleScores() throws SQLException {
        hsDao.save(new Highscore(0, "test", "2018-03-01"));
        hsDao.save(new Highscore(0, "test", "2018-03-01"));
        assertEquals(2, hsDao.getAll().size());
    }

    // @Test
    public void getAllReturnsScoresInCorrectOrder() throws SQLException {
        hsDao.save(new Highscore(10, "test", "2018-03-01"));
        hsDao.save(new Highscore(1, "test", "2018-03-01"));
        hsDao.save(new Highscore(60, "test", "2018-03-01"));
        List<Highscore> scores = hsDao.getAll();
        assertEquals(60, scores.get(0).getScore());
        assertEquals(10, scores.get(0).getScore());
        assertEquals(1, scores.get(0).getScore());

    }

}
