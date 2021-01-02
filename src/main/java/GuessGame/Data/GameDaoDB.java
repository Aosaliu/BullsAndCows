package GuessGame.Data;

import GuessGame.Models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
public class GameDaoDB implements GameDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT * FROM Game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game getGameById(int gameId) {
        final String sql = "SELECT * FROM Game WHERE gameID = ?;";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), gameId);
    }

    @Override
    public Game addGame(Game game) { //The add method uses a JdbcTemplate.update overload to execute a SQL insert and grab the generated keys.
        //From the code along. A secure way of inserting data
        final String sql = "INSERT INTO Game(answer) VALUES (?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn ) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getAnswer());
            return statement;

        }, keyHolder);
        game.setGameId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public void updateGame(Game game) {
        final String sql = "UPDATE Game SET finished = ? WHERE gameID = ?;";
        jdbcTemplate.update(sql,game.isFinished(), game.getGameId());
    }

    @Override
    public void deleteGameByID(int gameId) {
        final String sql = "DELETE FROM Game WHERE gameID = ?;";
        jdbcTemplate.update(sql, gameId);

    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameID"));
            game.setAnswer(rs.getString("answer"));
            game.setFinished(rs.getBoolean("finished"));
            return game;
        }
    }
}
