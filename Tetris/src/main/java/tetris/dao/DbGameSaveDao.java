package tetris.dao;

import com.google.gson.Gson;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tetris.domain.Game;
import tetris.domain.GameSaveData;

/**
 * Handles reading and writing game savesd to SQLite database.
 */
public class DbGameSaveDao implements GameSaveDao {

    private Connection connection;
    private Gson gson = new Gson();

    /**
     * Creates new DbGameSaveDao.
     *
     * @param connectionUrl Url used to connect to SQLite
     * @throws SQLException if connecting to database fails
     */
    public DbGameSaveDao(String connectionUrl) throws SQLException {
        this.connection = DriverManager.getConnection(connectionUrl);
        createDb();
    }

    /**
     * {@inheritDoc}
     *
     * @throws SQLException if there's error executing sql query
     */
    @Override
    public long save(GameSaveData gameSave) throws SQLException {
        String gameJson = gson.toJson(gameSave.getGame());
        System.out.println(gameJson);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO gamesave VALUES(?, ?, ?)");
        statement.setString(1, gameSave.getName());
        statement.setString(2, gameJson);
        statement.setString(3, gameSave.getDate());
        statement.execute();
        ResultSet rs = statement.getGeneratedKeys();
        rs.next();
        return rs.getLong(1);
    }

    /**
     * {@inheritDoc}
     *
     * @throws SQLException if there's error executing sql query
     */
    @Override
    public List<GameSaveData> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT rowid, * FROM gamesave");
        List<GameSaveData> saves = new ArrayList<>();
        while (rs.next()) {
            Game game = gson.fromJson(rs.getString("save"), Game.class);
            saves.add(new GameSaveData(rs.getLong("rowid"), game, rs.getString("name"), rs.getString("date")));
        }
        statement.close();
        return saves;
    }

    /**
     * {@inheritDoc}
     *
     * @throws SQLException if there's error executing sql query
     */
    @Override
    public void delete(long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM gamesave WHERE rowid=?");
        statement.setLong(1, id);
        statement.execute();
        statement.close();
    }

    private void createDb() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS gamesave (name STRING, save STRING, date STRING)");
        statement.close();
    }

}
