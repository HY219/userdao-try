package ac.kr.jejunu.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertStatementStrategy implements StatementStrategy {
    private User user;
    public InsertStatementStrategy(User user) {
        this.user=user;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        //User user = (User) object;
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userdao2 (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
        return preparedStatement;
    }
}
