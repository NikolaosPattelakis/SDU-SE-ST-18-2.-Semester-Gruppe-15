/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Lupo
 */
public class StatementController {

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
