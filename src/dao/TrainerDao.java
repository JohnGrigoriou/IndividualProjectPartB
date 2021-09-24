package dao;

import entity.Course;
import entity.Trainer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Color;

public class TrainerDao extends GenericDao implements CrudInterface<Trainer>{
    
    private static final String FINDALL = "SELECT * FROM trainers";
    private static final String FINDBYID = "SELECT * FROM trainers WHERE trainer_id = ?";
    private static final String INSERT = "INSERT INTO trainers (fname, lname, subject) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE trainers SET fname = ?, lname = ?, subject = ? WHERE trainer_id = ?";
    private static final String DELETE = "DELETE FROM trainers WHERE trainer_id = ?";
    private static final String FINDBYCOURSEID = "SELECT trainer_id, fname, lname, subject FROM trainers "
                                               + "INNER JOIN trainerspercourse ON trainers.trainer_id = trainerspercourse.id_trainer "
                                               + "INNER JOIN courses ON courses.course_id = trainerspercourse.id_course "
                                               + "WHERE course_id = ?";

    @Override
    public List<Trainer> findAll() {
        List<Trainer> list = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()){
                int trainer_id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String subject = rs.getString(4);
                Trainer trainer = new Trainer(trainer_id, fname, lname, subject);
                list.add(trainer);   
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "THERE ARE NO TRAINERS AT THE MOMENT\nGO BACK AND ADD SOME IF YOU MAY");
        } finally {
            closeConnections(rs, stmt, conn);
        }
        return list;
    }

    @Override
    public Trainer findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Trainer trainer = null;
        try { 
            pstm = conn.prepareStatement(FINDBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int trainer_id = rs.getInt(1);
            String fname = rs.getString(2);
            String lname = rs.getString(3);
            String subject = rs.getString(4);
            trainer = new Trainer(trainer_id, fname, lname, subject);
        } catch (SQLException ex) {
            Color.println(Color.RED, "TRAINER WITH ID "+id+" COULD NOT BE FOUND!!!");
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return trainer;
    }

    @Override
    public void create(Trainer trainer) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {            
            pstm = conn.prepareStatement(INSERT);
            pstm.setString(1, trainer.getFirstName());
            pstm.setString(2, trainer.getLastName());
            pstm.setString(3, trainer.getSubject());
            int result = pstm.executeUpdate();
            if (result == 1){
                Color.println(Color.GREEN, "TRAINER SUCCESSFULLY CREATED!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nTRAINER NOT CREATED");
        } finally {
            closeConnections(pstm, conn);
        }  
    }

    @Override
    public void update(Trainer trainer) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;       
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setString(1, trainer.getFirstName());
            pstm.setString(2, trainer.getLastName());
            pstm.setString(3, trainer.getSubject());
            pstm.setInt(4, trainer.getId());
            int result = pstm.executeUpdate();
            if (result == 1){
                Color.println(Color.GREEN, "TRAINER SUCCESSFULLY UPDATED!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nTRAINER NOT UPDATED");
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
                Color.println(Color.GREEN, "TRAINER SUCCESSFULLY DELETED!!!");
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "SOMETHING WENT WRONG!!!\nTRAINER NOT DELETED");
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
                int trainer_id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String subject = rs.getString(4);
                Trainer trainer = new Trainer(trainer_id, fname, lname, subject);
                course.addTrainer(trainer);
            }
        } catch (SQLException ex) {
            Color.println(Color.RED, "COURSE WITH ID: "+course.getId()+" COULD NOT BE FOUND!!!");
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return course;
    }

}
