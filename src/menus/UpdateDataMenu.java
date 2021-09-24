package menus;

import java.util.Scanner;
import menusfunctionalities.Update;
import utils.Color;
import utils.Program;

public class UpdateDataMenu {
    
    public static void menu(){
        Scanner sc = new Scanner(System.in);
        Color.line();
        Color.print(Color.YELLOW, "U P D A T E   D A T A   M E N U");
        Color.print(Color.YELLOW,"\n1) ");System.out.println("EDIT A STUDENT");
        Color.print(Color.YELLOW,"2) ");System.out.println("EDIT A TRAINER");
        Color.print(Color.YELLOW,"3) ");System.out.println("EDIT AN ASSIGNMENT"); 
        Color.print(Color.YELLOW,"4) ");System.out.println("EDIT A COURSE"); 
        Color.print(Color.YELLOW,"5) ");System.out.println("EDIT A STUDENT'S GRADE\n"); 
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
                    Update.updateStudent();
                    break;
                case "2":
                    Update.updateTrainer();
                    break;
                case "3":
                    Update.updateAssignment();
                    break;
                case "4":
                    Update.updateCourse();
                    break;
                case "5":
                    Update.updateGrade();
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

