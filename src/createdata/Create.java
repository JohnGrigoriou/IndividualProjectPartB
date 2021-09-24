package createdata;

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
import java.util.Scanner;
import menusfunctionalities.Show;
import utils.Color;
import utils.Date;
import utils.GetIds;
import utils.Validation;

public class Create extends GetIds{

    public static Student createStudent() {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nGIVE THE DETAILS OF THE STUDENT AS IN THE EXAMPLE BELOW\n");
            System.out.println("NAME SURNAME DATE-OF-BIRTH FEES");
            System.out.println("Jack Alexiou 1/1/2000      1700\n");
            do {
                String input = sc.nextLine();
                String s = input.trim().replaceAll("\\s+"," ");
                String[] element = s.split(" ");
                if (element.length != 4) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!element[0].chars().allMatch(Character::isLetter)) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!element[1].chars().allMatch(Character::isLetter)) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!Date.dateString(element[2]) || Date.yearNum(element[2]) < 1920) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!Validation.isDouble(element[3])) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                Student student = new Student(element[0], element[1], element[2], Double.parseDouble(element[3]));
                StudentDao sdao = new StudentDao();
                List<Student> list = sdao.findAll();
                if (list.contains(student)){
                    Color.print(Color.RED,"THIS STUDENT ALREADY EXISTS!!!\n");
                    break;
                }    
                return student;
            } while (true);
        }
    }
    
    public static Trainer createTrainer(){
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nGIVE THE DETAILS OF THE TRAINER AS IN THE EXAMPLE BELOW\n");
            System.out.println("NAME SURNAME SUBJECT");
            System.out.println("John Alexiou Python\n");
            do {
                String input = sc.nextLine();
                String s = input.trim().replaceAll("\\s+"," ");
                String[] element = s.split(" ");
                if (element.length != 3) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!(element[0].chars().allMatch(Character::isLetter))) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!(element[1].chars().allMatch(Character::isLetter))) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!(element[2].chars().allMatch(Character::isLetter))) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                Trainer trainer = new Trainer(element[0], element[1], element[2]);
                TrainerDao tdao = new TrainerDao();
                List<Trainer> list = tdao.findAll();
                if (list.contains(trainer)){
                    Color.print(Color.RED,"THIS TRAINER ALREADY EXISTS!!!\n");
                    break;
                }    
                return trainer;
            } while (true);
        }
    }
    
    public static Assignment createAssignment() {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nGIVE THE DETAILS OF THE ASSIGNMENT AS IN THE EXAMPLE BELOW\n");
            System.out.println("TITLE            DESCRIPTION  SUB-DATE-TIME ORAL-MARK TOTAL-MARK");
            System.out.println("JavaAssignment1  TicTacToe    12/12/2020    50        100\n");
            do {
                String input = sc.nextLine();
                String s = input.trim().replaceAll("\\s+"," ");
                String[] element = s.split(" ");
                if (element.length != 5) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!(element[0].matches("[A-Za-z0-9]+"))) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!(element[1].matches("[A-Za-z0-9]+"))) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!(Date.dateString(element[2])) || Date.yearNum(element[2]) < 2000) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!(element[3].chars().allMatch(Character::isDigit))) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!(element[4].chars().allMatch(Character::isDigit))) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (Integer.valueOf(element[3]) >= Integer.valueOf(element[4])){
                    Color.print(Color.RED,"WRONG INPUT!!!");
                    Color.print(Color.RED,"TOTAL MARK HAS TO BE GREATER THAN ORAL MARK\n");
                    break;
                }
                Assignment assignment = new Assignment(element[0], element[1], element[2], Integer.parseInt(element[3]), Integer.parseInt(element[4]));
                AssignmentDao adao = new AssignmentDao();
                List<Assignment> list = adao.findAll();
                if (list.contains(assignment)){
                    Color.print(Color.RED,"THIS ASSIGNMENT ALREADY EXISTS!!!\n");
                    break;
                }  
                return assignment;
            } while (true);
        }
    }
    
    public static Course createCourse(){
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nGIVE THE DETAILS OF THE COURSE AS IN THE EXAMPLE BELOW\n");
            System.out.println("NAME STREAM  TYPE  START-DATE  END-DATE");
            System.out.println("CB1  Python  Part  1/1/2021  28/5/2021\n");
            do {
                String input = sc.nextLine();
                String s = input.trim().replaceAll("\\s+"," ");
                String[] element = s.split(" ");
                if (element.length != 5) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!(element[0].matches("[A-Za-z0-9]+"))) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!(element[1].matches("[A-Za-z0-9]+"))) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!(element[2].toLowerCase().equals("part") || element[2].toLowerCase().equals("full"))) {
                    Color.print(Color.RED,"TYPE MUST BE \"PART\" OR \"FULL\"\n");
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!(Date.dateString(element[3])) || Date.yearNum(element[3]) < 2000) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (!(Date.dateString(element[4])) || Date.yearNum(element[4]) < 2000) {
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                if (Date.localDate(element[3]).isAfter(Date.localDate(element[4]))){
                    Color.print(Color.RED,"WRONG INPUT!!!\n");
                    break;
                }
                Course course = new Course(element[0], element[1], element[2].toUpperCase(), element[3], element[4]);
                CourseDao cdao = new CourseDao();
                List<Course> list = cdao.findAll();
                if (list.contains(course)){
                    Color.print(Color.RED,"THIS COURSE ALREADY EXISTS!!!\n");
                    break;
                }    
                return course;
            } while (true);
        }
    }
    
    public static void addStudentToCourse(){
        StudentDao sdao = new StudentDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE STUDENT YOU WANT TO ADD TO A COURSE\n");
        Show.showAllStudents();
        int student_id = Validation.positiveIntInput();
        if (getStudentsIds().contains(student_id)){
            CourseDao cdao = new CourseDao();
            System.out.println("PLEASE CHOOSE THE ID OF THE COURSE YOU WANT TO ADD THE STUDENT\n");
            Show.showAllCourses();
            int course_id = Validation.positiveIntInput();
            if (getCoursesIds().contains(course_id)){
                cdao.insertStudentById(student_id, course_id);
            } else {
                Color.println(Color.RED, "COURSE WITH ID = "+course_id+" COULD NOT BE FOUND!!!");
            }
        } else {
            Color.println(Color.RED, "STUDENT WITH ID = "+student_id+" COULD NOT BE FOUND!!!");
        }
    }
    
    public static void addTrainerToCourse(){
        TrainerDao tdao = new TrainerDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE TRAINER YOU WANT TO ADD TO A COURSE\n");
        Show.showAllTrainers();
        int trainer_id = Validation.positiveIntInput();
        if (getTrainersIds().contains(trainer_id)){
            CourseDao cdao = new CourseDao();
            System.out.println("PLEASE CHOOSE THE ID OF THE COURSE YOU WANT TO ADD THE TRAINER\n");
            Show.showAllCourses();
            int course_id = Validation.positiveIntInput();
            if (getCoursesIds().contains(course_id)){
                cdao.insertTrainerById(trainer_id, course_id);
            } else {
                Color.println(Color.RED, "COURSE WITH ID = "+course_id+" COULD NOT BE FOUND!!!");
            }
        } else {
            Color.println(Color.RED, "TRAINER WITH ID = "+trainer_id+" COULD NOT BE FOUND!!!");
        }
    }
    
    public static void addAssignmentToCourse(){
        AssignmentDao adao = new AssignmentDao();
        List<Assignment> list = adao.findAssignmentsWithoutCourse();
        System.out.println("PLEASE CHOOSE THE ID OF THE ASSIGNMENT YOU WANT TO ADD TO A COURSE");
        System.out.println("YOU CAN ONLY ADD ASSIGNMENTS THAT DON'T BELONG TO A COURSE ALREADY!!!\n");
        if (!list.isEmpty()){
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
                boolean flag1 = false;
                Assignment assignment = adao.findByIdWithoutCourse(assignment_id);
                java.sql.Date subDateTime = Date.StringToSqlDate(assignment.getSubDateTime());
                List<Course> availableCourses = adao.findAvailableCourses(subDateTime);
                if (!availableCourses.isEmpty()){
                    System.out.println("PLEASE CHOOSE THE ID OF THE COURSE YOU WANT TO ADD THE ASSIGNMENT\n");
                    Color.println(Color.PURPLE, "ID__COURSE___________STREAM_________TYPE______START-DATE_____END-DATE");
                    for (Course availableCourse : availableCourses) {
                        System.out.println(availableCourse);
                    }
                    Color.line();
                    int course_id = Validation.positiveIntInput();
                    for (Course availableCourse : availableCourses) {
                        if (course_id == availableCourse.getId()){
                            flag1 = true;
                        }
                    }
                    if (flag1 == true){
                        adao.addAssignmentToCourse(assignment_id, course_id);
                    } else {
                        Color.println(Color.RED, "COURSE WIHT ID = "+course_id+" NOT FOUND!!");
                    }
                }
            } else {
                Color.println(Color.RED, "ASSIGNMENT WITH ID = "+assignment_id+" NOT FOUND!!");
            }
        }  
    }
    
    public static void addGradeToAssignment(){
        AssignmentDao adao = new AssignmentDao();
        StudentDao sdao = new StudentDao();
        GradeDao gdao = new GradeDao();
        System.out.println("PLEASE CHOOSE THE ID OF THE STUDENT YOU WANT TO GRADE\n");
        Show.showAllStudents();
        int student_id = Validation.positiveIntInput();
        if (getStudentsIds().contains(student_id)){
            Student student = sdao.findById(student_id);
            Student newStudent = adao.findByStudentId(student);
            List<Assignment> list = newStudent.getAssignments();
            if (!list.isEmpty()){
                System.out.println("PLEASE CHOOSE THE ID OF THE ASSIGNMENT YOU WANT TO GRADE\n");
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
                        gdao.insertByStudentAssignmentId(student, assignmentToGrade, studentsOral, studentsTotal);
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
    
    
    

