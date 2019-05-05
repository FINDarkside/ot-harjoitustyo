package tetris.domain;

public class Highscore {

    private final int score;
    private final String nickname;
    private final String date;

    /**
     * Highscore constructor.
     *
     * @param score score
     * @param name nickname of player
     * @param date date of when the score was set
     */
    public Highscore(int score, String name, String date) {
        this.score = score;
        this.nickname = name;
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public String getNickname() {
        return nickname;
    }

}
