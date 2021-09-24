package menusfunctionalities;

import utils.GetIds;
import dao.AssignmentDao;
import dao.CourseDao;
import dao.StudentDao;
import dao.TrainerDao;
import java.util.Scanner;
import utils.Color;
import utils.Validation;

public class Delete extends GetIds{
    
    public static void deleteStudent(){
        StudentDao sdao = new StudentDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE STUDENT YOU WANT TO DELETE");
        Show.showAllStudents();
        int student_id = Validation.positiveIntInput();
        if (getStudentsIds().contains(student_id)){
            sdao.delete(student_id);    
        } else {
            Color.println(Color.RED, "STUDENT WITH ID "+student_id+" COULD NOT BE FOUND!!!");
        }        
    }
    
    public static void deleteTrainer(){
        TrainerDao tdao = new TrainerDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE TRAINER YOU WANT TO DELETE");
        Show.showAllTrainers();
        int trainer_id = Validation.positiveIntInput();
        if (getTrainersIds().contains(trainer_id)){
            tdao.delete(trainer_id);    
        } else {
            Color.println(Color.RED, "TRAINER WITH ID "+trainer_id+" COULD NOT BE FOUND!!!");
        }  
    }
    
    public static void deleteAssignment(){
        AssignmentDao adao = new AssignmentDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE ASSIGNMENT YOU WANT TO DELETE");
        Show.showAllAssignments();
        int assignment_id = Validation.positiveIntInput();
        if (getAssignmentsIds().contains(assignment_id)){
            adao.delete(assignment_id);    
        } else {
            Color.println(Color.RED, "ASSIGNMENT WITH ID "+assignment_id+" COULD NOT BE FOUND!!!");
        }
    }
    
    public static void deleteCourse(){
        CourseDao cdao = new CourseDao();
        Scanner sc = new Scanner(System.in);
        System.out.println("TYPE:");
        Color.print(Color.GREEN, "\"YES\"");System.out.print(" OR ");Color.print(Color.RED, "\"NO\"\n");
        String str = sc.nextLine().trim().toLowerCase();
        if (str.equals("yes")){
            Show.showAllCourses();
            int course_id = Validation.positiveIntInput();
            if (getCoursesIds().contains(course_id)){
                cdao.delete(course_id);    
            } else {
                Color.println(Color.RED, "COURSE WITH ID "+course_id+" COULD NOT BE FOUND!!!");
            }
        } else if (str.equals("no")) {
            System.out.println("NO COURSE DELETED!");
        } else {
            Color.print(Color.RED,"WRONG INPUT!!!\n");
            deleteCourse();
        }
    }
}
