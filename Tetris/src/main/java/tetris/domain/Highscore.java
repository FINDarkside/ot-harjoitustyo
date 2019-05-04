package tetris.domain;

public class Highscore {

    private final int score;
    private final String nickname;
    private final String date;

    public Highscore(int score, String username, String date) {
        this.score = score;
        this.nickname = username;
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
