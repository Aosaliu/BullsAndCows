package GuessGame.Models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Round {

    private int roundId;
    private int gameId;
    private String guess;
    private String result;
    private LocalDateTime guessTime;

    //create getters and setters
    public int getRoundId(){
        return roundId;
    }

    public void setRoundId(int id){
        this.roundId = id;
    }

    public int getGameId(){
        return gameId;
    }

    public void setGameId(int id){
        this.gameId = id;
    }

    public String getGuess(){
        return guess;
    }

    public void setGuess(String guess){
        this.guess = guess;
    }

    public String getResult(){
        return result;
    }

    public void setResult(String result){
        this.result = result;
    }

    public LocalDateTime getGuessTime(){
        return guessTime;
    }

    public void setGuessTime(LocalDateTime time){
        this.guessTime = time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return roundId == round.roundId && gameId == round.gameId && guess.equals(round.guess) && result.equals(round.result) && guessTime.equals(round.guessTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundId, gameId, guess, result, guessTime);
    }
}
