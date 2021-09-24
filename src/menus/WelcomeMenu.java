package menus;

import java.util.Scanner;
import utils.Color;
import utils.Program;

public class WelcomeMenu {
    
    public static void menu() {
        Scanner sc = new Scanner(System.in);        
        Color.line();
        Color.print(Color.YELLOW, "W E L C O M E   M E N U");
        Color.print(Color.YELLOW,"\n1) ");System.out.println("INSERT DATA");
        Color.print(Color.YELLOW,"2) ");System.out.println("SHOW DATA");
        Color.print(Color.YELLOW,"3) ");System.out.println("UPDATE DATA"); 
        Color.print(Color.YELLOW,"4) ");System.out.println("DELETE DATA\n"); 
        Color.println(Color.RED,"TO LEAVE APP TYPE \"EXIT\"");
        Color.line();
        while (true) {
            //System.out.println("PLEASE TYPE A NUMBER TO CONTINUE...");
            Color.println(Color.YELLOW, "TYPE ONE OF THE MENU'S CHOICES TO CONTINUE...");
            String str = sc.nextLine();
            String choice = str.trim().toUpperCase();
            switch (choice) {
                case "1":
                    InsertDataMenu.menu();
                case "2":
                    ShowDataMenu.menu();
                case "3":
                    UpdateDataMenu.menu();
                case "4":
                    DeleteDataMenu.menu();
                case "EXIT":
                    Program.exit();
                default:
                    Color.println(Color.RED,"WRONG INPUT!!!\n");
                    break;
            }
        }
    }
}
