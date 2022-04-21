package ac.kr.jejunu.user;

import java.sql.*;

public class UserDao {
    public User get(Integer id) throws ClassNotFoundException, SQLException {
        //mysql
        //driver 로딩
        Connection connection = getConnection();
        //query
        PreparedStatement preparedStatement = connection.prepareStatement("select id, name, password from userdao2 where id = ?");
        preparedStatement.setInt(1, id);
        //실행
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        //결과매핑
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //결과리턴
        return user;
    }

    public void insert(User user) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        //query
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userdao2 (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();
        //실행
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        //결과매핑
        //User user = new User();
        user.setId(resultSet.getInt(1));
        //자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //결과리턴
        //return user;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        //mysql
        //driver 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        //connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/userdao2?serverTimezone=UTC", "1234", "1234");
        return connection;
    }
}
