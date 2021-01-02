package GuessGame.Service;

import GuessGame.Data.GameDao;
import GuessGame.Data.RoundDao;
import GuessGame.Models.Game;
import GuessGame.Models.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service //annotates classes in the service layer
public class ServiceLayer {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public int create(){
        Game newGame = new Game();
        newGame.setAnswer(generateAnswer());
        gameDao.addGame(newGame);

        return newGame.getGameId();
    }


    public Round guess(Round round){
        String answer = gameDao.getGameById(round.getGameId()).getAnswer();
        String guess = round.getGuess();
        String result = determineResult(guess, answer);
        round.setResult(result);

        if (guess.equals(answer)) {
            Game game = gameDao.getGameById(round.getGameId());
            game.setFinished(true);
            gameDao.updateGame(game);
        }
        return roundDao.addRound(round);
    }

    public List<Game> allGames(){
        List<Game> listGame = gameDao.getAllGames();
        for(Game game : listGame)
            if (!game.isFinished()) {
                game.setAnswer("****");
            }
        return listGame;
    }

    public Game gameByID(int gameId){
        Game game = gameDao.getGameById(gameId);
        if (!game.isFinished()) {
            game.setAnswer("****");
        }

        return game;
    }

    public List<Round> allRoundsByID(int gameId){
        return  roundDao.getAllRoundsByGameId(gameId);
    }


    public String generateAnswer() { //To generate a random 4 digit answer for game
        Random random = new Random();
        int first = random.nextInt(10);

        int second = random.nextInt(10);
        while (second == first) { //checks to make sure the 2nd digit is not the same as the 1st
            second = random.nextInt(10);//change if so
        }

        int third = random.nextInt(10);
        while (third == second || third == first) { //checks to make sure the 3rd digit is not the same as 1st and 2nd
            third = random.nextInt(10);//change if so
        }

        int forth = random.nextInt(10);
        while (forth == third || forth == second || forth == first) {//check to make sure 4th digit is not the same as 1st or 2nd or 3rd
            forth = random.nextInt(10);//change if so
        }


        String answer = Integer.toString(first) + Integer.toString(second) + Integer.toString(third) + Integer.toString(forth);

        return answer;
    }

    public String determineResult(String guess, String answer) { //To determine whether the guess was correct and show partical/exact
        char[] guessChars = guess.toCharArray();
        char[] answerChars = answer.toCharArray();
        int exact = 0;
        int partial = 0;

        for (int i = 0; i < guessChars.length; i++) {
            // -1 indicates that index value of guessChars DNE in answer
            // otherwise the number must be in the string. Then check where
            if (answer.indexOf(guessChars[i]) > -1) {
                if (guessChars[i] == answerChars[i]) {
                    exact++;
                } else {
                    partial++;
                }
            }
        }

        String result = "e:" + exact + ":p:" + partial;

        return result;
    }
}
