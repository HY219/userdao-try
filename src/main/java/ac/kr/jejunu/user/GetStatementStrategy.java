package ac.kr.jejunu.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetStatementStrategy implements StatementStrategy {
    private Integer id;
    public GetStatementStrategy(Integer id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        //Integer id = (Integer) object;
        PreparedStatement preparedStatement = connection.prepareStatement("select id, name, password from userdao2 where id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement;
    }
}
