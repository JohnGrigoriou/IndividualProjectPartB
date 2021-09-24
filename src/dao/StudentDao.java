package dao;

import entity.Course;
import entity.Student;
import java.math.BigDecimal;
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

public class StudentDao extends GenericDao implements CrudInterface<Student>{
    
    private static final String FINDALL = "SELECT * FROM students";
    private static final String FINDBYID = "SELECT * FROM students WHERE student_id = ?";
    private static final String INSERT = "INSERT INTO students (fname, lname, dob, tuitionfees) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE students SET fname = ?, lname = ?, dob = ?, tuitionfees = ? WHERE student_id = ?";
    private static final String DELETE = "DELETE FROM students WHERE student_id = ?";
    private static final String FINDBYCOURSEID = "SELECT student_id, fname, lname, dob, tuitionfees FROM students "
                                               + "INNER JOIN studentspercourse ON students.student_id = studentspercourse.id_student "
                                               + "INNER JOIN courses ON courses.course_id = studentspercourse.id_course "
                                               + "WHERE course_id = ?";
    private static final String FINDSTUDENTSWITHMULTIPLECOURSES = "SELECT student_id, fname, lname, dob, tuitionfees FROM students "
                                                                + "INNER JOIN studentspercourse ON students.student_id = studentspercourse.id_student "
                                                                + "INNER JOIN courses ON courses.course_id = studentspercourse.id_course "
                                                                + "GROUP BY students.student_id "
                                                                + "HAVING count(*) > 1";
    
    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()){
                int student_id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                LocalDate dob = rs.getDate(4).toLocalDate();
                Double tuitionfees = rs.getBigDecimal(5).doubleValue();
                Student student = new Student(student_id, fname, lname, Date.localDateToString(dob), tuitionfees);
                list.add(student);   
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "THERE ARE NO STUDENTS AT THE MOMENT\nGO BACK AND ADD SOME IF YOU MAY");
        } finally {
            closeConnections(rs, stmt, conn);
        }
        return list;
    }

    @Override
    public Student findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Student student = null;
        try { 
            pstm = conn.prepareStatement(FINDBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int student_id = rs.getInt(1);
            String fname = rs.getString(2);
            String lname = rs.getString(3);
            LocalDate dob = rs.getDate(4).toLocalDate();
            Double tuitionfees = rs.getBigDecimal(5).doubleValue();
            student = new Student(student_id, fname, lname, Date.localDateToString(dob), tuitionfees);
        } catch (SQLException ex) {
            Color.println(Color.RED, "STUDENT WITH ID "+id+" COULD NOT BE FOUND!!!");
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return student;
    }

    @Override
    public void create(Student student) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {            
            pstm = conn.prepareStatement(INSERT);
            pstm.setString(1, student.getFirstName());
            pstm.setString(2, student.getLastName());
            pstm.setDate(3, Date.StringToSqlDate(student.getDateOfBirth()));
            pstm.setBigDecimal(4, BigDecimal.valueOf(student.getTuitionFees()));
            int result = pstm.executeUpdate();
            if (result == 1){
                Color.println(Color.GREEN, "STUDENT SUCCESSFULLY CREATED!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nSTUDENT NOT CREATED");
        } finally {
            closeConnections(pstm, conn);
        }  
    }

    @Override
    public void update(Student student) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;       
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setString(1, student.getFirstName());
            pstm.setString(2, student.getLastName());
            pstm.setDate(3, Date.StringToSqlDate(student.getDateOfBirth()));
            pstm.setBigDecimal(4, BigDecimal.valueOf(student.getTuitionFees()));
            pstm.setInt(5, student.getId());
            int result = pstm.executeUpdate();
            if (result == 1){
                Color.println(Color.GREEN, "STUDENT SUCCESSFULLY UPDATED!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nSTUDENT NOT UPDATED");
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
                Color.println(Color.GREEN, "STUDENT SUCCESSFULLY DELETED!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nSTUDENT NOT DELETED");
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
                int student_id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                LocalDate dob = rs.getDate(4).toLocalDate();
                Double tuitionfees = rs.getBigDecimal(5).doubleValue();
                Student student = new Student(student_id, fname, lname, Date.localDateToString(dob), tuitionfees);
                course.addStudent(student);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Color.println(Color.RED, "COURSE WITH ID: "+course.getId()+" COULD NOT BE FOUND!!!");
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return course;
    }
    
    public List<Student> studentsWithMultipleCourses(){
        List<Student> list = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(FINDSTUDENTSWITHMULTIPLECOURSES);
            while (rs.next()){
                int student_id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                LocalDate dob = rs.getDate(4).toLocalDate();
                Double tuitionfees = rs.getBigDecimal(5).doubleValue();
                Student student = new Student(student_id, fname, lname, Date.localDateToString(dob), tuitionfees);
                list.add(student);   
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "THERE ARE NO STUDENTS WITH MULTIPLE COURSES AT THE MOMENT\nGO BACK AND ADD SOME IF YOU MAY");
        } finally {
            closeConnections(rs, stmt, conn);
        }
        return list;
    }
    
}
