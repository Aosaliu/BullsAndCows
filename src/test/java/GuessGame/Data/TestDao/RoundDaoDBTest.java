package GuessGame.Data.TestDao;

import GuessGame.Data.GameDao;
import GuessGame.Data.RoundDao;
import GuessGame.Models.Game;
import GuessGame.Models.Round;
import GuessGame.TestApplicationConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class RoundDaoDBTest {

    @Autowired
    RoundDao roundDao;

    @Autowired
    GameDao gameDao;

    @BeforeEach
    public void setUp() { // resets the database to original state everytime we want to run a test. CURRENTLY NOT WORKING
        List<Game> games = gameDao.getAllGames();

        for(Game game : games) {

            roundDao.deleteRoundsByGameID(game.getGameId());
            gameDao.deleteGameByID(game.getGameId());
        }
    }

    @Test
    void testGetAllRoundsByGameId() {

        Game game = new Game();
        game.setAnswer("2468");
        game.setFinished(false);
        game = gameDao.addGame(game);


        Round round = new Round();
        round.setGuess("5137");
        round.setResult("e:0:p:0");
        round.setGameId(game.getGameId());
        roundDao.addRound(round);

        Round round2 = new Round();
        round2.setGuess("2468");
        round2.setResult("e:4:p:0");
        round2.setGameId(game.getGameId());
        roundDao.addRound(round2);

        List<Round> rounds = roundDao.getAllRoundsByGameId(game.getGameId());

        assertEquals(2, rounds.size()); //this is testing add rounds
        assertNotNull(round = roundDao.getRoundById(round.getRoundId())); //this is testing that we are able to get round by id

    }

    @Test
    void testGetRoundById() { //This is actually already being tested in the previous method so DISREGARD
//        int gameID = 1;
//
//        Game game = new Game();
//        game.setAnswer("2468");
//        game.setFinished(false);
//        game = gameDao.addGame(game);
//
//        Round round = new Round();
//        round.setGuess("1234");
//        round.setResult("e:0:p:0");
//        round.setGameId(game.getGameId());
//        round.setRoundId(gameID);
//        roundDao.addRound(round);
//
//        assertEquals(1, round.getRoundId());

    }

    @Test
    void testAddRound() { //This is actually already being tested in the first method so DISREGARD
//        int ID = 1;
//
//        Game game = new Game();
//        game.setAnswer("2468");
//        game.setFinished(false);
//        game = gameDao.addGame(game);
//
//        Round round = new Round();
//        round.setGuess("1234");
//        round.setResult("e:0:p:0");
//        round.setGameId(ID);
//        roundDao.addRound(round);
//
//        List<Round> rounds = roundDao.getAllRoundsByGameId(ID);
//
//        assertEquals(1, rounds.size());

    }
}