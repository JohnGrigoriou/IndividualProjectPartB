package menusfunctionalities;

import createdata.Create;
import dao.AssignmentDao;
import dao.CourseDao;
import dao.GradeDao;
import dao.StudentDao;
import dao.TrainerDao;
import entity.Assignment;
import entity.Course;
import entity.Student;
import entity.Trainer;
import java.util.List;
import utils.Color;
import utils.GetIds;
import static utils.GetIds.getStudentsIds;
import utils.Validation;

public class Update extends GetIds{

    public static void updateStudent(){
        StudentDao sdao = new StudentDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE STUDENT YOU WANT TO UPDATE\n");
        Show.showAllStudents();
        int student_id = Validation.positiveIntInput();
        if (getStudentsIds().contains(student_id)){
            Student student = Create.createStudent();
            student.setId(student_id);
            sdao.update(student);
        } else {
            Color.println(Color.RED, "STUDENT WITH ID = "+student_id+" COULD NOT BE FOUND!!!");
        }   
    }
    
    public static void updateTrainer(){
        TrainerDao tdao = new TrainerDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE TRAINER YOU WANT TO UPDATE\n");
        Show.showAllTrainers();
        int trainer_id = Validation.positiveIntInput();
        if (getTrainersIds().contains(trainer_id)){
            Trainer trainer = Create.createTrainer();
            trainer.setId(trainer_id);
            tdao.update(trainer);
        } else {
            Color.println(Color.RED, "TRAINER WITH ID = "+trainer_id+" COULD NOT BE FOUND!!!");
        } 
    }
    
    public static void updateAssignment(){
        AssignmentDao adao = new AssignmentDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE ASSIGNMENT YOU WANT TO UPDATE\n");
        Show.showAllAssignments();
        int assignment_id = Validation.positiveIntInput();
        if (getAssignmentsIds().contains(assignment_id)){
            Assignment assignment = Create.createAssignment();
            assignment.setId(assignment_id);
            adao.update(assignment);
        } else {
            Color.println(Color.RED, "ASSIGNMENT WITH ID = "+assignment_id+" COULD NOT BE FOUND!!!");
        } 
    }
    
    public static void updateCourse(){
        CourseDao cdao = new CourseDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE COURSE YOU WANT TO UPDATE\n");
        Show.showAllCourses();
        int course_id = Validation.positiveIntInput();
        if (getCoursesIds().contains(course_id)){
            Course course = Create.createCourse();
            course.setId(course_id);
            cdao.update(course);
        } else {
            Color.println(Color.RED, "COURSE WITH ID = "+course_id+" COULD NOT BE FOUND!!!");
        } 
    }
    
    public static void updateGrade(){
        AssignmentDao adao = new AssignmentDao();
        StudentDao sdao = new StudentDao();
        GradeDao gdao = new GradeDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE STUDENT YOU WANT TO UPDATE GRADE\n");
        Show.showAllStudents();
        int student_id = Validation.positiveIntInput();
        if (getStudentsIds().contains(student_id)){
            Student student = sdao.findById(student_id);
            Student newStudent = adao.findByStudentId(student);
            List<Assignment> list = newStudent.getAssignments();
            if (!list.isEmpty()){
                System.out.println("PLEASE CHOOSE THE ID OF THE ASSIGNMENT YOU WANT TO UPDATE GRADE\n");
                Color.println(Color.PURPLE, "ID__TITLE____________DESCRIPTION_________SUB-DATE-TIME______ORAL-MARK______TOTAL-MARK");
                for (Assignment assignment : list) {
                    System.out.println(assignment);
                }
                Color.line();
                int assignment_id = Validation.positiveIntInput();
                boolean flag = false;
                for (Assignment assignment : list) {
                    if (assignment_id == assignment.getId()){
                        flag = true;
                    }
                }
                if (flag == true){
                    Assignment assignmentToGrade = adao.findByIdWithoutCourse(assignment_id);
                    int maxOral = assignmentToGrade.getOralMark();
                    int maxTotal = assignmentToGrade.getTotalMark();
                    System.out.println("PLEASE GIVE THE ORAL MARK FOR THE ASSIGNMENT...");
                    int studentsOral = Validation.giveOnlyInteger(0, maxOral);
                    System.out.println("PLEASE GIVE THE TOTAL MARK FOR THE ASSIGNMENT...");
                    int studentsTotal = Validation.giveOnlyInteger(0, maxTotal);
                    if (studentsOral <= studentsTotal){
                        gdao.update(student, assignmentToGrade, studentsOral, studentsTotal);
                    } else {
                        Color.println(Color.RED, "TOTAL MARK MUST BE GREATER OR EQUAL THAN ORAL!!!");
                    }    
                } else {
                    Color.println(Color.RED, "ASSIGNMENT WITH ID = "+assignment_id+" NOT FOUND!!");
                }
            } else {
                Color.println(Color.RED, "STUDENT IS NOT ENROLLED IN A COURSE AT THE MOMENT");
                System.out.println("GO BACK AND ADD STUDENT TO A COURSE IF YOU MAY...\n");
            }      
        } else {
            Color.println(Color.RED, "STUDENT WITH ID = "+student_id+" COULD NOT BE FOUND!!!");
        } 
    }
}
