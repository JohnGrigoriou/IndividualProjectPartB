package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.Color;

public class SyntheticDao extends GenericDao{
    
    private static final String DELETEALLDATA = "CALL deleteAll()";
    private static final String ADDSYNTHETICDATA = "CALL addSynthetic()";
    
    public void addSyntheticData() {
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(ADDSYNTHETICDATA);    
        } catch (SQLException ex) {
            Color.println(Color.RED, "FAILED TO LOAD SYNTHETIC DATA!!!");
        } finally {
            closeConnections(rs, stmt, conn);
        }    
    }
    
    public void deleteAllData() {
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(DELETEALLDATA);    
        } catch (SQLException ex) {
            Color.println(Color.RED, "FAILED TO RESET ALL TABLES!!!");
        } finally {
            closeConnections(rs, stmt, conn);
        }  
    }
}
