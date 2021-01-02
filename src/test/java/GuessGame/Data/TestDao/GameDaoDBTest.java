package GuessGame.Data.TestDao;

import GuessGame.Data.GameDao;
import GuessGame.Data.RoundDao;
import GuessGame.Models.Game;
import GuessGame.Models.Round;
import GuessGame.TestApplicationConfiguration;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class GameDaoDBTest {

    @Autowired
    GameDao gameDao;
    
    @Autowired
    RoundDao roundDao;

    @BeforeEach
    public void setUp() { // resets the database to original state everytime we want to run a test. CURRENTLY NOT WORKING
        List<Game> games = gameDao.getAllGames();
        
        for(Game game : games) {

            roundDao.deleteRoundsByGameID(game.getGameId());
            gameDao.deleteGameByID(game.getGameId());
        }
    }

    @Test
    void testGetAllGames() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setFinished(false);
        gameDao.addGame(game);

        Game game2 = new Game();
        game2.setAnswer("5678");
        game2.setFinished(false);
        gameDao.addGame(game2);

        List<Game> games = gameDao.getAllGames();

        assertEquals(2, games.size()); 
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    @Test
    void testGetGameById() { //this is essentially already tested when testing update game
//        Game game = new Game();
//        game.setAnswer("1234");
//        game.setFinished(false);
//        gameDao.addGame(game);
//
//        Game fromGame = gameDao.getGameById(game.getGameId());
//
//        assertEquals(game.getGameId(), fromGame.getGameId());
    }

    @Test
    void testAddGame() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setFinished(false);
        game.setGameId(1);


        List<Game> games = new ArrayList<>();
        games.add(gameDao.getGameById(1));


        assertEquals(1, games.size());
    }

    @Test
    void testUpdateGame() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setFinished(false);
        game = gameDao.addGame(game);

        Game fromDao = gameDao.getGameById(game.getGameId());

        assertEquals(game, fromDao);

        game.setFinished(true);

        gameDao.updateGame(game);

        assertNotEquals(game, fromDao);

        fromDao = gameDao.getGameById(game.getGameId());

        assertEquals(game, fromDao);
    }
}