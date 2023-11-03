package cadastro.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    public int getValue(String sequenceName) {
        int nextValue = 0;
        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = ConectorBD.getPrepared("SELECT NEXT VALUE FOR " + sequenceName);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                nextValue = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextValue;
    }
}
