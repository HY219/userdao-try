package ac.kr.jejunu.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) throws SQLException {
        StatementStrategy statementStrategy = new GetStatementStrategy(id);
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public void insert(User user) throws SQLException {
        StatementStrategy statementStrategy = new InsertStatementStrategy(user);
        jdbcContext.jdbcContextForInsert(user, statementStrategy);

        //결과리턴
        //return user;
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);

        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Integer id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);

        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    /*
    private User jdbeContextForGet(StatementStrategy statementStrategy) throws SQLException {


        //결과리턴
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }



    private void jdbcContextForInsert(User user, StatementStrategy statementStrategy) throws SQLException {


        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }



    private void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        //ResultSet resultSet = null;

        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

//    private PreparedStatement makeStatement(Integer id, Connection connection) throws SQLException{
////        PreparedStatement preparedStatement;
////        preparedStatement = connection.prepareStatement("delete from userdao2 where id = ?");
////        preparedStatement.setInt(1, id);
////        return preparedStatement;
//    }

     */
}
