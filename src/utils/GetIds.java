package utils;

import dao.AssignmentDao;
import dao.CourseDao;
import dao.StudentDao;
import dao.TrainerDao;
import entity.Assignment;
import entity.Course;
import entity.Student;
import entity.Trainer;
import java.util.ArrayList;
import java.util.List;

public class GetIds {
    
    public static List getCoursesIds(){
        CourseDao cdao = new CourseDao();
        List<Course> list = cdao.findAll();
        List listWithIds = new ArrayList();
        for (Course course : list) {
            listWithIds.add(course.getId());
        }
        return listWithIds;
    }
    
    public static List getStudentsIds(){
        StudentDao sdao = new StudentDao();
        List<Student> list = sdao.findAll();
        List listWithIds = new ArrayList();
        for (Student student : list) {
            listWithIds.add(student.getId());
        }
        return listWithIds;
    }
    
    public static List getAssignmentsIds(){
        AssignmentDao adao = new AssignmentDao();
        List<Assignment> list = adao.findAll();
        List listWithIds = new ArrayList();
        for (Assignment assignment : list) {
            listWithIds.add(assignment.getId());
        }
        return listWithIds;
    }
    
    public static List getTrainersIds(){
        TrainerDao tdao = new TrainerDao();
        List<Trainer> list = tdao.findAll();
        List listWithIds = new ArrayList();
        for (Trainer trainer : list) {
            listWithIds.add(trainer.getId());
        }
        return listWithIds;
    }
    
}
