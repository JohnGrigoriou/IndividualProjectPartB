package dao;

import entity.Assignment;
import entity.Course;
import entity.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericDao {

    private final String url = "jdbc:mysql://localhost:3306/school";
    private final String user = "root";
    private final String password = "root";
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
    protected void closeConnections(ResultSet rs, Statement stmt, Connection conn){
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    protected void closeConnections(PreparedStatement pstm, Connection conn){
        try {
            pstm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    protected Course getCourseById(int id){
        CourseDao courseDao = new CourseDao();
        return courseDao.findById(id);
    }
    
    protected Student getStudentById(int id){
        StudentDao studentDao = new StudentDao();
        return studentDao.findById(id);
    }
    
    protected Assignment getAssignmentById(int id){
        AssignmentDao assignmentDao = new AssignmentDao();
        return assignmentDao.findById(id);
    }
}
