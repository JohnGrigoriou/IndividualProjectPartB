package dao;

import entity.Assignment;
import entity.Grade;
import entity.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Color;

public class GradeDao extends GenericDao{

    private static final String FINDBYSTUDENTID = "SELECT id_student, id_assignment, assignmentsperstudent.oral, assignmentsperstudent.total FROM students "
                                                          + "INNER JOIN assignmentsperstudent ON assignmentsperstudent.id_student = students.student_id "
                                                          + "INNER JOIN assignments ON assignments.assignment_id = assignmentsperstudent.id_assignment "
                                                          + "WHERE id_student = ? AND id_assignment = ?";
    private static final String INSERTBYSTUDENTASSIGNMENTID = "INSERT INTO assignmentsperstudent VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE assignmentsperstudent SET oral = ?, total = ? WHERE id_student = ? and id_assignment = ?";
    
    public List<Grade> findByStudentId(Student student, List<Assignment> assignments){
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Grade grade = null;
        List<Grade> list = new ArrayList();
        try { 
            pstm = conn.prepareStatement(FINDBYSTUDENTID);
            for (Assignment assignment : assignments){
                pstm.setInt(1, student.getId());
                pstm.setInt(2, assignment.getId());
                rs = pstm.executeQuery();
                if (rs.next()){
                    int oral = rs.getInt(3);
                    int total = rs.getInt(4);
                    grade = new Grade(student, assignment, oral, total);
                    list.add(grade);
                }
//                rs.next();
//                int oral = rs.getInt(3);
//                int total = rs.getInt(4);
//                grade = new Grade(student, assignment, oral, total);
//                list.add(grade);
            }        
        } catch (SQLException ex) {
            System.out.println("STUDENT WITH ID = "+student.getId()+" HAS NO ASSIGNMENTS GRADED YET!!!\nGO BACK AND GRADE SOME IF YOU MAY");
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return list;
    }
    
    public void insertByStudentAssignmentId(Student student, Assignment assignment, int oralMark, int totalMark){
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {            
            pstm = conn.prepareStatement(INSERTBYSTUDENTASSIGNMENTID);
            pstm.setInt(1, student.getId());
            pstm.setInt(2, assignment.getId());
            pstm.setInt(3, oralMark);
            pstm.setInt(4, totalMark);
            int result = pstm.executeUpdate();
            if (result == 1){
                Color.println(Color.GREEN, "ASSIGNMENT SUCCESSFULLY GRADED!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nASSIGNMENT PROBABLY ALREADY BEEN GRADED");
        } finally {
            closeConnections(pstm, conn);
        }
    }
    
    public void update(Student student, Assignment assignment, int oralMark, int totalMark){
        Connection conn = getConnection();
        PreparedStatement pstm = null;       
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setInt(1, oralMark);
            pstm.setInt(2, totalMark);
            pstm.setInt(3, student.getId());
            pstm.setInt(4, assignment.getId());
            int result = pstm.executeUpdate();
            if (result == 1){
                Color.println(Color.GREEN, "GRADE SUCCESSFULLY UPDATED!!!");
            } else if (result == 0){
                Color.println(Color.RED, "YOU TRYING TO UPDATE AN ASSIGNMENT'S GRADE");
                Color.println(Color.RED, "WHICH HAS NOT BEEN GRADED YET!!!");
                System.out.println("GO BACK AND INSERT A GRADE TO THIS ASSIGNMENT IF YOU MAY\n");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nGRADE NOT UPDATED");
        } finally {
            closeConnections(pstm, conn);
        }
    }
    
    
}
