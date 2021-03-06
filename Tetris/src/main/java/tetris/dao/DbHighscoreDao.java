package tetris.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tetris.domain.Highscore;

/**
 * Handles reading/writing highscores to SQLite database.
 */
public class DbHighscoreDao implements HighscoreDao {

    private final Connection connection;

    /**
     * Creates new DbHighscoreDao.
     *
     * @param connection Connection to use in all operations
     * @throws SQLException if connecting to database fails
     */
    public DbHighscoreDao(Connection connection) throws SQLException {
        this.connection = connection;
        createDb();
    }

    /**
     * {@inheritDoc}
     *
     * @throws SQLException if there's error executing sql query
     */
    @Override
    public void save(Highscore highscore) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO highscore VALUES(?, ?, ?)");
        statement.setInt(1, highscore.getScore());
        statement.setString(2, highscore.getNickname());
        statement.setString(3, highscore.getDate());
        statement.execute();
        statement.close();
    }

    /**
     * {@inheritDoc}
     *
     * @throws SQLException if there's error executing sql query
     */
    @Override
    public List<Highscore> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM highscore ORDER BY score DESC");
        List<Highscore> highscores = new ArrayList<>();
        while (rs.next()) {
            highscores.add(new Highscore(rs.getInt("score"), rs.getString("name"), rs.getString("date")));
        }
        statement.close();
        return highscores;
    }

    private void createDb() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS highscore (score INTEGER, name STRING, date TEXT)");
        statement.close();
    }

}
