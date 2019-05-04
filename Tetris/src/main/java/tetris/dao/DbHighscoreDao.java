package tetris.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tetris.domain.Highscore;

public class DbHighscoreDao implements HighscoreDao {

    private final String connectionUrl;

    public DbHighscoreDao(String connectionUrl) throws SQLException {
        this.connectionUrl = connectionUrl;
        createDb();
    }

    @Override
    public void create(Highscore highscore) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionUrl);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO highscore VALUES(?, ?, ?)");
        statement.setInt(0, highscore.getScore());
        statement.setString(1, highscore.getNickname());
        statement.setString(2, highscore.getDate());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public List<Highscore> getAll() throws SQLException {
        Connection connection = DriverManager.getConnection(connectionUrl);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM highscore ORDER BY score");
        List<Highscore> highscores = new ArrayList<>();
        while (rs.next()) {
            highscores.add(new Highscore(rs.getInt("score"), rs.getString("nickname"), rs.getString("date")));
        }
        return highscores;
    }

    private void createDb() throws SQLException {
        Connection connection = DriverManager.getConnection(connectionUrl);
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS highscore (score INTEGER, name STRING, date TEXT)");
        statement.close();
        connection.close();
    }

}
