package ac.kr.jejunu.user;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcContext {
    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    User jdbcContextForGet(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;


        try {
            //mysql
            //driver 로딩
            connection = dataSource.getConnection();
            //query

            preparedStatement = statementStrategy.makeStatement(connection);
//            preparedStatement = connection.prepareStatement("select id, name, password from userdao2 where id = ?");
//            preparedStatement.setInt(1, id);
            //실행
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //결과매핑
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            //자원해지
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //결과리턴
        return user;
    }

    void jdbcContextForInsert(User user, StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            connection = dataSource.getConnection();
            //query

            preparedStatement = statementStrategy.makeStatement(connection);
//            preparedStatement = connection.prepareStatement("insert into userdao2 (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            //실행
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            //결과매핑
            //User user = new User();
            user.setId(resultSet.getInt(1));
        } finally {
            //자원해지
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            //query

            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();
            //실행
            //resultSet = preparedStatement.getGeneratedKeys();
            //resultSet.next();
            //결과매핑
            //User user = new User();
            //user.setId(resultSet.getInt(1));
        } finally {
            //자원해지
            //resultSet.close();
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}