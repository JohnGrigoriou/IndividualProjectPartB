package menusfunctionalities;

import utils.GetIds;
import dao.AssignmentDao;
import dao.CourseDao;
import dao.GradeDao;
import dao.StudentDao;
import dao.TrainerDao;
import entity.Assignment;
import entity.Course;
import entity.Grade;
import entity.Student;
import entity.Trainer;
import java.util.List;
import utils.Color;
import utils.Validation;

public class Show extends GetIds{
    
    public static void showAllStudents(){
        StudentDao sdao = new StudentDao();
        List<Student> list = sdao.findAll();
        Color.println(Color.PURPLE, "ALL STUDENTS:\n");        
        Color.println(Color.PURPLE, "ID__NAME_____________SURNAME________DATE-OF-BIRTH_____FEES");        
        for (Student student : list) {
            System.out.println(student);
        }
        Color.line();
    }
    
    public static void showAllTrainers(){
        TrainerDao tdao = new TrainerDao();
        List<Trainer> list = tdao.findAll();
        Color.println(Color.PURPLE, "ALL TRAINERS:\n");
        Color.println(Color.PURPLE, "ID__NAME_____________SURNAME____________SUBJECT");
        for (Trainer trainer : list) {
            System.out.println(trainer);
        }
        Color.line();
    }
    
    public static void showAllAssignments(){
        AssignmentDao adao = new AssignmentDao();
        List<Assignment> list = adao.findAll();
        Color.println(Color.PURPLE, "ALL ASSIGNMENTS:\n");
        Color.println(Color.PURPLE, "ID__TITLE____________DESCRIPTION_________SUB-DATE-TIME______ORAL-MARK______TOTAL-MARK");
        for (Assignment assignment : list) {
            System.out.println(assignment);
        }
        Color.line();
    }
    
    public static void showAllCourses(){
        CourseDao cdao = new CourseDao();
        List<Course> list = cdao.findAll();
        Color.println(Color.PURPLE, "ALL COURSES:\n");
        Color.println(Color.PURPLE, "ID__COURSE___________STREAM_________TYPE______START-DATE_____END-DATE");
        for (Course course : list) {
            System.out.println(course);            
        }
        Color.line();
    }
    
    public static void showStudentsPerCourse(){
        StudentDao sdao = new StudentDao();
        CourseDao cdao = new CourseDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE COURSE YOU WANT TO SEE IT'S STUDENTS\n");
        showAllCourses();
        int course_id = Validation.positiveIntInput();
        if (getCoursesIds().contains(course_id)){
            Course course = cdao.findById(course_id);
            Course newCourse = sdao.findByCourseId(course);
            List<Student> list = newCourse.getStudents();
            if (!list.isEmpty()){
                Color.println(Color.PURPLE, "THE STUDENTS OF THE COURSE ARE:\n");
                Color.println(Color.PURPLE, "ID__NAME_____________SURNAME________DATE-OF-BIRTH_____FEES");
                for (Student student : list) {
                    System.out.println(student);
                }
                Color.line();
            } else {
                System.out.println("COURSE WITH ID = "+course_id+" HAS NO STUDENTS YET!!!\nGO BACK AND ADD SOME IF YOU MAY");
            }
        } else {
            Color.println(Color.RED, "COURSE WITH ID = "+course_id+" COULD NOT BE FOUND!!!");
        }        
    }        
        
    public static void showTrainersPerCourse(){
        TrainerDao tdao = new TrainerDao();
        CourseDao cdao = new CourseDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE COURSE YOU WANT TO SEE IT'S TRAINERS\n");
        showAllCourses();
        int course_id = Validation.positiveIntInput();
        if (getCoursesIds().contains(course_id)){
            Course course = cdao.findById(course_id);
            Course newCourse = tdao.findByCourseId(course);
            List<Trainer> list = newCourse.getTrainers();
            if (!list.isEmpty()){
                Color.println(Color.PURPLE, "THE TRAINERS OF THE COURSE ARE:\n");
                Color.println(Color.PURPLE, "ID__NAME_____________SURNAME____________SUBJECT");
                for (Trainer trainer : list) {
                    System.out.println(trainer);
                }
                Color.line();
            } else {
                System.out.println("COURSE WITH ID = "+course_id+" HAS NO TRAINERS YET!!!\nGO BACK AND ADD SOME IF YOU MAY");
            }    
        } else {
            Color.println(Color.RED, "COURSE WITH ID = "+course_id+" COULD NOT BE FOUND!!!");
        } 
    }
    
    public static void showAssignmentsPerCourse(){
        AssignmentDao adao = new AssignmentDao();
        CourseDao cdao = new CourseDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE COURSE YOU WANT TO SEE IT'S ASSIGNMENTS\n");
        showAllCourses();
        int course_id = Validation.positiveIntInput();
        if (getCoursesIds().contains(course_id)){
            Course course = cdao.findById(course_id);
            Course newCourse = adao.findByCourseId(course);
            List<Assignment> list = newCourse.getAssignments();
            if (!list.isEmpty()){
                Color.println(Color.PURPLE, "THE ASSIGNMENTS OF THE COURSE ARE:\n");
                Color.println(Color.PURPLE, "ID__TITLE____________DESCRIPTION_________SUB-DATE-TIME______ORAL-MARK______TOTAL-MARK");
                for (Assignment assignment : list) {
                    System.out.println(assignment);
                }
                Color.line();
            } else {
                System.out.println("COURSE WITH ID = "+course_id+" HAS NO ASSIGNMENTS YET!!!\nGO BACK AND ADD SOME IF YOU MAY");
            }
        } else {
            Color.println(Color.RED, "COURSE WITH ID = "+course_id+" COULD NOT BE FOUND!!!");
        } 
    }
    
    public static void showAssignmentsPerStudent(){
        AssignmentDao adao = new AssignmentDao();
        StudentDao sdao = new StudentDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE STUDENT YOU WANT TO SEE IT'S ASSIGNMENTS\n");
        showAllStudents();
        int student_id = Validation.positiveIntInput();
        if (getStudentsIds().contains(student_id)){
            Student student = sdao.findById(student_id);
            Student newStudent = adao.findByStudentId(student);
            List<Assignment> list = newStudent.getAssignments();
            if (!list.isEmpty()){
                Color.println(Color.PURPLE, "THE ASSIGNMENTS OF THE STUDENT ARE:\n");
                Color.println(Color.PURPLE, "ID__TITLE____________DESCRIPTION_________SUB-DATE-TIME______ORAL-MARK______TOTAL-MARK");
                for (Assignment assignment : list) {
                    System.out.println(assignment);
                }
                Color.line();
            } else {
                System.out.println("STUDENT WITH ID = "+student_id+" HAS NO ASSIGNMENTS YET!!!\nGO BACK AND ADD SOME IF YOU MAY");
            }
        } else {
            Color.println(Color.RED, "STUDENT WITH ID = "+student_id+" COULD NOT BE FOUND!!!");
        } 
    }
    
    public static void showGradesForAssignments(){
        AssignmentDao adao = new AssignmentDao();
        StudentDao sdao = new StudentDao();
        GradeDao gdao = new GradeDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE STUDENT YOU WANT TO SEE IT'S GRADES\n");
        showAllStudents();
        int student_id = Validation.positiveIntInput();
        if (getStudentsIds().contains(student_id)){
            Student student = sdao.findById(student_id);
            Student newStudent = adao.findByStudentId(student);
            List<Assignment> list = newStudent.getAssignments();
            if (!list.isEmpty()){
                List<Grade> grades = gdao.findByStudentId(newStudent, list);
                if (!grades.isEmpty()){
                    Color.println(Color.PURPLE, "STUDENT'S GRADES ARE:\n");
                    for (Grade grade : grades) {
                        System.out.println(grade);
                    } 
                    Color.line();
                }
            } else {
                Color.println(Color.RED, "STUDENT IS NOT ENROLLED IN A COURSE AT THE MOMENT\nTHEREFORE HAS NO GRADES...");
            }       
        } else {
            Color.println(Color.RED, "STUDENT WITH ID = "+student_id+" COULD NOT BE FOUND!!!");
        } 
    }
    
    public static void showStudentsWithMultipleCourses(){
        StudentDao sdao = new StudentDao();
        List<Student> list = sdao.studentsWithMultipleCourses();
        Color.println(Color.PURPLE, "THE STUDENTS WITH MORE THAN ONE COURSE:\n");
        Color.println(Color.PURPLE, "ID__NAME_____________SURNAME________DATE-OF-BIRTH_____FEES");
        for (Student student : list) {        
            System.out.println(student);
        }
        Color.line();
    }
   
}
