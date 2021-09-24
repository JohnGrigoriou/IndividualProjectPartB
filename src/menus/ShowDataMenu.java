package menus;

import java.util.Scanner;
import menusfunctionalities.Show;
import utils.Color;
import utils.Program;

public class ShowDataMenu {

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        Color.line();
        Color.print(Color.YELLOW, "S H O W   D A T A   M E N U");
        Color.print(Color.YELLOW,"\n1) ");System.out.println("SHOW LIST OF ALL STUDENTS");
        Color.print(Color.YELLOW,"2) ");System.out.println("SHOW LIST OF ALL TRAINERS");
        Color.print(Color.YELLOW,"3) ");System.out.println("SHOW LIST OF ALL ASSIGNMENTS"); 
        Color.print(Color.YELLOW,"4) ");System.out.println("SHOW LIST OF ALL COURSES"); 
        Color.print(Color.YELLOW,"5) ");System.out.println("SHOW LIST OF ALL STUDENTS    PER COURSE"); 
        Color.print(Color.YELLOW,"6) ");System.out.println("SHOW LIST OF ALL TRAINERS    PER COURSE"); 
        Color.print(Color.YELLOW,"7) ");System.out.println("SHOW LIST OF ALL ASSIGNMENTS PER COURSE"); 
        Color.print(Color.YELLOW,"8) ");System.out.println("SHOW LIST OF ALL ASSIGNMENTS PER STUDENT PER COURSE"); 
        Color.print(Color.YELLOW,"9) ");System.out.println("SHOW GRADES FOR A STUDENT'S ASSIGNMENTS"); 
        Color.print(Color.YELLOW,"10)");System.out.println("SHOW LIST OF ALL STUDENTS THAT BELONG TO MORE THAN ONE COURSE\n"); 
        System.out.print("TO DISPLAY MENU TYPE ");Color.println(Color.YELLOW, "\"MENU\"");
        System.out.print("TO GO TO THE STARTING MENU TYPE ");Color.println(Color.YELLOW, "\"BACK\"");
        Color.println(Color.RED,"TO LEAVE APP TYPE \"EXIT\"");  
        Color.line();
        while (true) {
            Color.println(Color.YELLOW, "TYPE ONE OF THE MENU'S CHOICES TO CONTINUE...");
            String str = sc.nextLine();
            String choice = str.trim().toUpperCase();
            switch (choice) {
                case "1":
                    Show.showAllStudents();
                    break;
                case "2":
                    Show.showAllTrainers();
                    break;
                case "3":
                    Show.showAllAssignments();
                    break;
                case "4":
                    Show.showAllCourses();
                    break;
                case "5":
                    Show.showStudentsPerCourse();
                    break;
                case "6":
                    Show.showTrainersPerCourse();
                    break;
                case "7":
                    Show.showAssignmentsPerCourse();
                    break;
                case "8":
                    Show.showAssignmentsPerStudent();
                    break;
                case "9":
                    Show.showGradesForAssignments();
                    break;
                case "10":
                    Show.showStudentsWithMultipleCourses();
                    break;
                case "MENU":
                    menu();    
                case "BACK":
                    WelcomeMenu.menu();
                case "EXIT":
                    Program.exit();
                default:
                    Color.println(Color.RED,"WRONG INPUT!!!\n");
                    break;
            }
        }
    }
}
