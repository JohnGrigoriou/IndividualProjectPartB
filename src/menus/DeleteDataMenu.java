package menus;

import java.util.Scanner;
import menusfunctionalities.Delete;
import utils.Color;
import utils.Program;

public class DeleteDataMenu {
    
    public static void menu(){
        Scanner sc = new Scanner(System.in);
        Color.line();
        Color.print(Color.YELLOW, "D E L E T E   D A T A   M E N U");
        Color.print(Color.YELLOW,"\n1) ");System.out.println("DELETE A STUDENT");
        Color.print(Color.YELLOW,"2) ");System.out.println("DELETE A TRAINER");
        Color.print(Color.YELLOW,"3) ");System.out.println("DELETE AN ASSIGNMENT"); 
        Color.print(Color.YELLOW,"4) ");System.out.println("DELETE A COURSE\n"); 
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
                    Delete.deleteStudent();
                    break;
                case "2":
                    Delete.deleteTrainer();
                    break;
                case "3":
                    Delete.deleteAssignment();
                    break;
                case "4":
                    System.out.println("PLEASE CHOOSE THE ID OF THE COURSE YOU WANT TO DELETE");
                    Color.println(Color.RED, "BE CAREFUL!!! BY DELETING A COURSE MAY ALSO LEAD TO IT'S ASSIGNMENTS DELETION!");
                    System.out.println("ARE YOU SURE YOU WANNA CONTINUE?");
                    Delete.deleteCourse();
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
