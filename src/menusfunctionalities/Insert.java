package menusfunctionalities;

import createdata.Create;
import dao.AssignmentDao;
import dao.CourseDao;
import dao.StudentDao;
import dao.TrainerDao;

public class Insert {
    
    public static void insertStudent(){
        StudentDao sdao = new StudentDao();
        sdao.create(Create.createStudent());
    }
    
    public static void insertTrainer(){
        TrainerDao tdao = new TrainerDao();
        tdao.create(Create.createTrainer());
    }
        
    public static void insertAssignment(){
        AssignmentDao adao = new AssignmentDao();
        adao.create(Create.createAssignment());
    }
    
    public static void insertCourse(){
        CourseDao cdao = new CourseDao();
        cdao.create(Create.createCourse());
    }
    
    public static void insertStudentToCourse(){
        Create.addStudentToCourse();
    }
    
    public static void insertTrainerToCourse(){
        Create.addTrainerToCourse();
    }
    
    public static void insertAssignmentToCourse(){
        Create.addAssignmentToCourse();
    }
    
    public static void insertGradeToStudentsAssignment(){
        Create.addGradeToAssignment();
    }
    
    
}
