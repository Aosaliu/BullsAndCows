package GuessGame.Models;

import java.util.Objects;

public class Game {

    private int gameId;
    private String answer;
    private boolean finished;

    public Game(){
    }

    public Game(String answer, boolean finished) {
        this.answer = answer;
        this.finished = finished;
    }

    public Game(int gameId, String answer, boolean finished) {
        this.gameId = gameId;
        this.answer = answer;
        this.finished = finished;
    }

    //create getter and setter
    public int getGameId(){
        return gameId;
    }

    public void setGameId(int id){
        this.gameId = id;
    }

    public String getAnswer(){
        return answer;
    }

    public void setAnswer(String guess){
        this.answer = guess;
    }


    public boolean isFinished(){
        return finished;
    }

    public void setFinished(boolean finished){
        this.finished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return gameId == game.gameId && finished == game.finished && Objects.equals(answer, game.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, answer, finished);
    }
}
