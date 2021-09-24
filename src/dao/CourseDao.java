package dao;

import entity.Course;
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

public class CourseDao extends GenericDao implements CrudInterface<Course>{

    private static final String FINDALL = "SELECT * FROM courses";
    private static final String FINDBYID = "SELECT * FROM courses WHERE course_id = ?";
    private static final String INSERT = "INSERT INTO courses (title, stream, type, startdate, enddate) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE courses SET title = ?, stream = ?, type = ?, startdate = ?, enddate = ? WHERE course_id = ?";
    private static final String DELETE = "DELETE FROM courses WHERE course_id = ?";
    private static final String INSERTSTUDENTBYID = "INSERT INTO studentspercourse VALUES (?, ?)";
    private static final String INSERTTRAINERBYID = "INSERT INTO trainerspercourse VALUES (?, ?)";
        
    @Override
    public List<Course> findAll() {
        List<Course> list = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()){
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
            Color.println(Color.RED, "THERE ARE NO COURSES AT THE MOMENT\nGO BACK AND ADD SOME IF YOU MAY");
        } finally {
            closeConnections(rs, stmt, conn);
        }
        return list;
    }

    @Override
    public Course findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Course course = null;
        try { 
            pstm = conn.prepareStatement(FINDBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int course_id = rs.getInt(1);
            String title = rs.getString(2);
            String stream = rs.getString(3);
            String type = rs.getString(4);
            LocalDate startdate = rs.getDate(5).toLocalDate();
            LocalDate enddate = rs.getDate(6).toLocalDate();
            course = new Course(course_id, title, stream, type, Date.localDateToString(startdate), Date.localDateToString(enddate));
        } catch (SQLException ex) {
            //Color.println(Color.RED, "COURSE WITH ID "+id+" COULD NOT BE FOUND!!!");
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return course;
    }

    @Override
    public void create(Course course) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {            
            pstm = conn.prepareStatement(INSERT);
            pstm.setString(1, course.getTitle());
            pstm.setString(2, course.getStream());
            pstm.setString(3, course.getType());
            pstm.setDate(4, Date.StringToSqlDate(course.getStartDate()));
            pstm.setDate(5, Date.StringToSqlDate(course.getEndDate()));
            int result = pstm.executeUpdate();
            if (result == 1){
                Color.println(Color.GREEN, "COURSE SUCCESSFULLY CREATED!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nSTUDENT NOT CREATED");
        } finally {
            closeConnections(pstm, conn);
        }
    }

    @Override
    public void update(Course course) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;       
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setString(1, course.getTitle());
            pstm.setString(2, course.getStream());
            pstm.setString(3, course.getType());
            pstm.setDate(4, Date.StringToSqlDate(course.getStartDate()));
            pstm.setDate(5, Date.StringToSqlDate(course.getEndDate()));
            pstm.setInt(6, course.getId());
            int result = pstm.executeUpdate();
            if (result == 1){
                Color.println(Color.GREEN, "COURSE SUCCESSFULLY UPDATED!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nCOURSE NOT UPDATED");
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
                Color.println(Color.GREEN, "COURSE SUCCESSFULLY DELETED!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nCOURSE NOT DELETED");
        } finally {
            closeConnections(pstm, conn);
        }
    }
    
    public void insertStudentById(int student_id, int course_id){
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {            
            pstm = conn.prepareStatement(INSERTSTUDENTBYID);
            pstm.setInt(1, student_id);
            pstm.setInt(2, course_id);
            int result = pstm.executeUpdate();
            if (result == 1){
                Color.println(Color.GREEN, "STUDENT SUCCESSFULLY ADDED TO COURSE!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nSTUDENT PROBABLY ALREADY IS ENROLLED IN THIS COURSE");
        } finally {
            closeConnections(pstm, conn);
        }
    }
    
    public void insertTrainerById(int trainer_id, int course_id){
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {            
            pstm = conn.prepareStatement(INSERTTRAINERBYID);
            pstm.setInt(1, trainer_id);
            pstm.setInt(2, course_id);
            int result = pstm.executeUpdate();
            if (result == 1){
                Color.println(Color.GREEN, "TRAINER SUCCESSFULLY ADDED TO COURSE!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nTRAINER PROBABLY ALREADY IS ENROLLED IN THIS COURSE");
        } finally {
            closeConnections(pstm, conn);
        }
    }

}
