package ac.kr.jejunu.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatementStrategy implements StatementStrategy {
    private Integer id;
    public DeleteStatementStrategy(Integer id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        //Integer id = (Integer) object;
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("delete from userdao2 where id = ?");
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }
}
