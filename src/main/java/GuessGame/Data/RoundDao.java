package GuessGame.Data;

import GuessGame.Models.Round;
import java.util.List;

public interface RoundDao {

    List<Round> getAllRoundsByGameId(int gameId);
    Round getRoundById(int roundId);
    Round addRound(Round round);

    void deleteRoundsByGameID(int gameId);

}
