package tetris.domain;

public class GameSaveData {

    private Long id;
    private final Game game;
    private final String name;
    private final String date;

    /**
     * Creates new GameSaveData.
     *
     * @param game game instance
     * @param name name of the save
     * @param date save date
     */
    public GameSaveData(Game game, String name, String date) {
        this.id = null;
        this.game = game;
        this.name = name;
        this.date = date;
    }

    /**
     * Creates new GameSaveData.
     *
     * @param id id representing the save
     * @param game game instance
     * @param name name of the save
     * @param date save date
     */
    public GameSaveData(Long id, Game game, String name, String date) {
        this.id = id;
        this.game = game;
        this.name = name;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public Game getGame() {
        return game;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
