package sql;

/**
 * It represents a record for database
 * @author Balkányi Lajos
 */
public class HighScore {
    
    private final String name;
    private final int score;

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name  + ", elért pontjai:" + score;
    }
    

}
