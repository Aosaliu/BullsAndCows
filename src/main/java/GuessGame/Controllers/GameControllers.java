package GuessGame.Controllers;

import GuessGame.Service.ServiceLayer;
import GuessGame.Models.Game;
import GuessGame.Models.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameControllers {

    @Autowired
    ServiceLayer service;

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public String createGame() {
        return "New Game has begun. GameID: " + Integer.toString(service.create());
    }

    @PostMapping("/guess")
    public Round makeGuess(@RequestBody Round round) {
        return service.guess(round) ;
    }

    @GetMapping("/game")
    public List<Game> getAllGames() {
        return service.allGames();
    }

    @GetMapping("/game/{gameId}")
    public Game getGameById(@PathVariable("gameId") int gameId) {

        return service.gameByID(gameId);
    }

    @GetMapping("/rounds/{gameId}")
    public List<Round> getRoundsForGame(@PathVariable("gameId") int gameId) {
        return service.allRoundsByID(gameId);
    }
}
