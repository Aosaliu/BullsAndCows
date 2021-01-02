package GuessGame.Data;

import GuessGame.Models.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class RoundDaoDB implements RoundDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoundDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Round> getAllRoundsByGameId(int gameId) {
        final String sql = "SELECT * FROM Round WHERE gameID = ?;";
        return jdbcTemplate.query(sql, new RoundMapper(), gameId);
    }

    @Override
    public Round getRoundById(int roundId) {
        final String sql = "SELECT * FROM Round WHERE roundID = ?;";
        return jdbcTemplate.queryForObject(sql, new RoundMapper(), roundId);
    }

    @Override
    public Round addRound(Round round) { //the guessTime is null..... FIXED: had to change how I was accessing the round.
        //from the code along. A secure way of inserting data
        final String sql = "INSERT INTO Round(gameID , guess, guessResult) VALUE (?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn ) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, Integer.toString(round.getGameId()));
            statement.setString(2, round.getGuess());
            statement.setString(3, round.getResult());

            return statement;

        }, keyHolder);
        round.setRoundId(keyHolder.getKey().intValue());

        return getRoundById(keyHolder.getKey().intValue());

    }

    @Override
    public void deleteRoundsByGameID(int gameId) {
        final String sql = "DELETE FROM Round WHERE gameID = ?;";
        jdbcTemplate.update(sql, gameId);

    }

    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundID"));
            round.setGameId(rs.getInt("gameID"));
            round.setGuess(rs.getString("guess"));

            Timestamp timestamp = rs.getTimestamp("guessTime");
            round.setGuessTime(timestamp.toLocalDateTime());

            round.setResult(rs.getString("guessResult"));
            return round;
        }
    }
}
