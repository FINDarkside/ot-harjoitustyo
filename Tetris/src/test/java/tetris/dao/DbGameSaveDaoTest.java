package tetris.dao;

import java.sql.*;
import java.util.*;
import org.junit.*;
import tetris.dao.DbGameSaveDao;
import static org.junit.Assert.*;
import tetris.domain.Game;
import tetris.domain.Game;
import tetris.domain.GameSaveData;
import tetris.domain.GameSaveData;

public class DbGameSaveDaoTest {

    private Connection connection;
    private DbGameSaveDao gameSaveDao;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        gameSaveDao = new DbGameSaveDao(connection);
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void canAddSaves() throws SQLException {
        gameSaveDao.save(new GameSaveData(new Game(20, 10, new Random()), "test", "2018-02-01"));
        assertEquals(1, gameSaveDao.getAll().size());
    }

    @Test
    public void canDeleteSaves() throws SQLException {
        long id = gameSaveDao.save(new GameSaveData(new Game(20, 10, new Random()), "test", "2018-02-01"));
        gameSaveDao.delete(id);
        assertEquals(0, gameSaveDao.getAll().size());
    }

    @Test
    public void getAllReturnsAllSaves() throws SQLException {
        for (int i = 0; i < 10; i++) {
            gameSaveDao.save(new GameSaveData(new Game(20, 10, new Random()), "test", "2018-02-01"));
        }
        assertEquals(10, gameSaveDao.getAll().size());
    }
}
