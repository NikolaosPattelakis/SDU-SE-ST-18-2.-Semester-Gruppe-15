package Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Class that handles the execution of various types of statements that will interact with a database.
 */
public class StatementController {

    /**
     * Executes a statement with no query input.
     * @param connection
     * @param query
     * @return ResultSet 
     */
    public ResultSet executeStatementWithNoInput(Connection connection, String query) {

        try {
            CallableStatement statement = connection.prepareCall(query);
            statement.execute();
            return statement.getResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(StatementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Executes a statement with a single query input.
     * @param connection
     * @param query
     * @param input
     * @return 
     */
    public ResultSet executeStatementWithSingleInput(Connection connection, String query, String input) {
        try {
            CallableStatement statement = connection.prepareCall(query);
            statement.setObject(1, input);
            statement.execute();
            return statement.getResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(StatementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    /**
     * Executes a statement with multiple query inputs.
     * @param connection
     * @param query
     * @param input
     * @return 
     */
    public ResultSet executeStatementWithMultipleInputs(Connection connection, String query, List<String> input) {
        try {
            CallableStatement statement = connection.prepareCall(query);

            for (int i = 0; i < input.size(); i++) {
                statement.setObject(i+1, input.get(i));
            }
            statement.execute();
            return statement.getResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(StatementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
