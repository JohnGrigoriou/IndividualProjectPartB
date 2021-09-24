package dao;

import entity.Assignment;
import entity.Course;
import entity.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.Color;
import utils.Date;

public class AssignmentDao extends GenericDao implements CrudInterface<Assignment>{
    
    private static final String FINDALL = "SELECT * FROM assignments";
    private static final String FINDBYID = "SELECT * FROM assignments WHERE assignment_id = ?";
    private static final String INSERT = "INSERT INTO assignments (title, description, subdatetime, oralmark, totalmark) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE assignments SET title = ?, description = ?, subdatetime = ?, oralmark = ?, totalmark = ? WHERE assignment_id = ?";
    private static final String DELETE = "DELETE FROM assignments WHERE assignment_id = ?";
    private static final String FINDBYCOURSEID = "SELECT * FROM assignments WHERE id_course = (SELECT course_id FROM courses WHERE course_id = ?)";
    private static final String FINDBYSTUDENTID = "SELECT assignment_id, assignments.title, description, subdatetime, oralmark, totalmark  FROM students "
                                                + "INNER JOIN studentspercourse ON students.student_id = studentspercourse.id_student "
                                                + "INNER JOIN courses ON courses.course_id = studentspercourse.id_course "
                                                + "INNER JOIN assignments ON assignments.id_course = courses.course_id "
                                                + "WHERE student_id = ?";
    public static final String FINDAVAILABLECOURSES = "SELECT * FROM courses WHERE ? BETWEEN startdate AND enddate";
    public static final String FINDASSIGNMENTSWITHOUTCOURSE = "SELECT assignment_id, title, description, subdatetime, oralmark, totalmark FROM assignments WHERE id_course IS NULL";
    public static final String ADDASSIGNMENTTOCOURSE = "UPDATE assignments SET id_course = ? WHERE assignment_id = ?";
    private static final String FINDBYIDWITHOUTCOURSE = "SELECT assignment_id, title, description, subdatetime, oralmark, totalmark FROM assignments WHERE assignment_id = ?";
        
    @Override
    public List<Assignment> findAll() {
        List<Assignment> list = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()){
                int assignment_id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                LocalDate subdatetime = rs.getDate(4).toLocalDate();
                int oralmark = rs.getInt(5);
                int totalmark = rs.getInt(6);
                int id_course = rs.getInt(7);
                Course course = getCourseById(id_course);
                Assignment assignment = new Assignment(assignment_id, title, description, Date.localDateToString(subdatetime), oralmark, totalmark, course);
                list.add(assignment);   
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "THERE ARE NO ASSIGNMENTS AT THE MOMENT\nGO BACK AND ADD SOME IF YOU MAY");
        } finally {
            closeConnections(rs, stmt, conn);
        }
        return list;
    }

    @Override
    public Assignment findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Assignment assignment = null;
        try { 
            pstm = conn.prepareStatement(FINDBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int assignment_id = rs.getInt(1);
            String title = rs.getString(2);
                String description = rs.getString(3);
                LocalDate subdatetime = rs.getDate(4).toLocalDate();
                int oralmark = rs.getInt(5);
                int totalmark = rs.getInt(6);
                int id_course = rs.getInt(7);
                Course course = getCourseById(id_course);
                assignment = new Assignment(assignment_id, title, description, Date.localDateToString(subdatetime), oralmark, totalmark, course);     
        } catch (SQLException ex) {
            Color.println(Color.RED, "ASSIGNMENT WITH ID "+id+" COULD NOT BE FOUND!!!");
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return assignment;
    }

    @Override
    public void create(Assignment assignment) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {            
            pstm = conn.prepareStatement(INSERT);
            pstm.setString(1, assignment.getTitle());
            pstm.setString(2, assignment.getDescription());
            pstm.setDate(3, Date.StringToSqlDate(assignment.getSubDateTime()));
            pstm.setInt(4, assignment.getOralMark());
            pstm.setInt(5, assignment.getTotalMark());
            //pstm.setInt(6, assignment.getCourse().getId());
            int result = pstm.executeUpdate();
            if (result == 1){
                Color.println(Color.GREEN, "ASSIGNMENT SUCCESSFULLY CREATED!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nASSIGNMENT NOT CREATED");
        } finally {
            closeConnections(pstm, conn);
        }  
    }

    @Override
    public void update(Assignment assignment) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;       
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setString(1, assignment.getTitle());
            pstm.setString(2, assignment.getDescription());
            pstm.setDate(3, Date.StringToSqlDate(assignment.getSubDateTime()));
            pstm.setInt(4, assignment.getOralMark());
            pstm.setInt(5, assignment.getTotalMark());
            pstm.setInt(6, assignment.getId());
            int result = pstm.executeUpdate();
            if (result == 1){
                Color.println(Color.GREEN, "ASSIGNMENT SUCCESSFULLY UPDATED!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nASSIGNMENT NOT UPDATED");
        } finally {
            closeConnections(pstm, conn);
        }
    }

    @Override
    public void delete(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
            if (result == 1){
                Color.println(Color.GREEN, "ASSIGNMENT SUCCESSFULLY DELETED!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nASSIGNMENT NOT DELETED");
        } finally {
            closeConnections(pstm, conn);
        }
    }
    
    public Course findByCourseId(Course course){
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;       
        try { 
            pstm = conn.prepareStatement(FINDBYCOURSEID);
            pstm.setInt(1, course.getId());
            rs = pstm.executeQuery();
            while(rs.next()){
                int assignment_id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                LocalDate subdatetime = rs.getDate(4).toLocalDate();
                int oralmark = rs.getInt(5);
                int totalmark = rs.getInt(6);
                Assignment assignment = new Assignment(assignment_id, title, description, Date.localDateToString(subdatetime), oralmark, totalmark, course); 
                course.addAssignment(assignment);
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "COURSE WITH ID: "+course.getId()+" COULD NOT BE FOUND!!!");
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return course;
    }
    
    public Student findByStudentId(Student student){
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;       
        try { 
            pstm = conn.prepareStatement(FINDBYSTUDENTID);
            pstm.setInt(1, student.getId());
            rs = pstm.executeQuery();
            while(rs.next()){
                int assignment_id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                LocalDate subdatetime = rs.getDate(4).toLocalDate();
                int oralmark = rs.getInt(5);
                int totalmark = rs.getInt(6);
                Assignment assignment = new Assignment(assignment_id, title, description, Date.localDateToString(subdatetime), oralmark, totalmark); 
                student.addAssignment(assignment);
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "STUDENT WITH ID: "+student.getId()+" COULD NOT BE FOUND!!!");
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return student;
    }
    
    public List<Course> findAvailableCourses(java.sql.Date subDateTime){
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null; 
        List<Course> list = new ArrayList();
        try { 
            pstm = conn.prepareStatement(FINDAVAILABLECOURSES);
            pstm.setDate(1, subDateTime);
            rs = pstm.executeQuery();
            while(rs.next()){
                int course_id = rs.getInt(1);
                String title = rs.getString(2);
                String stream = rs.getString(3);
                String type = rs.getString(4);
                LocalDate startdate = rs.getDate(5).toLocalDate();
                LocalDate enddate = rs.getDate(6).toLocalDate();
                Course course = new Course(course_id, title, stream, type, Date.localDateToString(startdate), Date.localDateToString(enddate));
                list.add(course);
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "NO AVAILABLE COURSES FOUND TO ADD THE ASSIGNMENT!!!");
            Color.println(Color.RED, "SUB DATE OF THE ASSIGNMENT MUST BE BETWEEN START AND END DATE OF THE COURSE");   
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return list;
    }
    
    public List<Assignment> findAssignmentsWithoutCourse(){
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null; 
        List<Assignment> list = new ArrayList();
        try { 
            pstm = conn.prepareStatement(FINDASSIGNMENTSWITHOUTCOURSE);
            rs = pstm.executeQuery();
            while(rs.next()){
                int assignment_id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                LocalDate subdatetime = rs.getDate(4).toLocalDate();
                int oralmark = rs.getInt(5);
                int totalmark = rs.getInt(6);
                Assignment assignment = new Assignment(assignment_id, title, description, Date.localDateToString(subdatetime), oralmark, totalmark);
                list.add(assignment);
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "THERE ARE NO ASSIGNMENTS THAT DON'T BELONG TO A COURSE AT THE MOMENT\n");
            System.out.println("GO BACK AND CREATE A NEW ASSIGNMENT IF YOU MAY");
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return list;
    }
    
    public void addAssignmentToCourse(int assignment_id, int course_id){
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {            
            pstm = conn.prepareStatement(ADDASSIGNMENTTOCOURSE); 
            pstm.setInt(1, course_id);
            pstm.setInt(2, assignment_id);
            int result = pstm.executeUpdate();
            if (result == 1){
                Color.println(Color.GREEN, "ASSIGNMENT SUCCESSFULLY ADDED TO COURSE!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nASSIGNMENT PROBABLY ALREADY EXISTS IN THIS COURSE");
        } finally {
            closeConnections(pstm, conn);
        }
    }
        
    public Assignment findByIdWithoutCourse(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Assignment assignment = null;
        try { 
            pstm = conn.prepareStatement(FINDBYIDWITHOUTCOURSE);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int assignment_id = rs.getInt(1);
            String title = rs.getString(2);
                String description = rs.getString(3);
                LocalDate subdatetime = rs.getDate(4).toLocalDate();
                int oralmark = rs.getInt(5);
                int totalmark = rs.getInt(6);
                assignment = new Assignment(assignment_id, title, description, Date.localDateToString(subdatetime), oralmark, totalmark);     
        } catch (SQLException ex) {
            Color.println(Color.RED, "ASSIGNMENT WITH ID "+id+" COULD NOT BE FOUND!!!");
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return assignment;
    }
}
