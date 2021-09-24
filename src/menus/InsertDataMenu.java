package menus;

import java.util.Scanner;
import menusfunctionalities.Insert;
import utils.Color;
import utils.Program;

public class InsertDataMenu {
    
    public static void menu(){
    Scanner sc = new Scanner(System.in);
        Color.line();
        Color.print(Color.YELLOW, "I N S E R T   D A T A   M E N U");
        Color.print(Color.YELLOW,"\n1) ");System.out.println("CREATE A STUDENT");
        Color.print(Color.YELLOW,"2) ");System.out.println("CREATE A TRAINER");
        Color.print(Color.YELLOW,"3) ");System.out.println("CREATE AN ASSIGNMENT"); 
        Color.print(Color.YELLOW,"4) ");System.out.println("CREATE A COURSE"); 
        Color.print(Color.YELLOW,"5) ");System.out.println("ADD STUDENT TO COURSE"); 
        Color.print(Color.YELLOW,"6) ");System.out.println("ADD TRAINER TO COURSE");  
        Color.print(Color.YELLOW,"7) ");System.out.println("ADD ASSIGNMENT TO COURSE");  
        Color.print(Color.YELLOW,"8) ");System.out.println("GRADE A STUDENT'S ASSIGNMENT\n"); 
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
                    Insert.insertStudent();
                    break;
                case "2":
                    Insert.insertTrainer();
                    break;
                case "3":
                    Insert.insertAssignment();
                    break;
                case "4":
                    Insert.insertCourse();
                    break;
                case "5":
                    Insert.insertStudentToCourse();
                    break;
                case "6":
                    Insert.insertTrainerToCourse();
                    break;
                case "7":
                    Insert.insertAssignmentToCourse();
                    break;
                case "8":
                    Insert.insertGradeToStudentsAssignment();
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
