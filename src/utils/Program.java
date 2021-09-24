package utils;

import dao.SyntheticDao;
import java.util.Scanner;
import menus.WelcomeMenu;

public class Program {
    
    public static void run(){
        Color.print(Color.PURPLE,"____________________WELCOME TO OUR PRIVATE SCHOOL APP____________________\n\n\n");
        Color.println(Color.YELLOW,"______THIS IS AN APP THAT WILL HELP YOU ORGANIZE YOUR PRIVATE SCHOOL_____");
        Color.println(Color.YELLOW,"__________IT GIVES YOU THE ABILITY TO CREATE, DELETE, UPDATE AND_________");
        Color.println(Color.YELLOW,"______________________DISPLAY DATA THE WAY YOU WANT______________________\n\n\n");
        loadData();
        WelcomeMenu.menu();
    }
    
    public static void loadData(){
        System.out.print("TYPE ANYTHING TO LOAD DATA AND CONTINUE OR TYPE ");
        Color.print(Color.RED, "\"EXIT\"");System.out.println(" TO LEAVE THE APP");
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().trim().toLowerCase().equals("exit")){
            exit();
        } else {
            System.out.println("DATA LOADING PLEASE WAIT...\n");
            SyntheticDao synthdao = new SyntheticDao();
            synthdao.deleteAllData();
            synthdao.addSyntheticData();
            Color.println(Color.GREEN, "DATA LOADING SUCCESSFUL!!!\n\n");
        }
    }
    
    public static void exit(){
        Color.println(Color.PURPLE,"\n\n\n_______________THANK YOU FOR USING OUR PRIVATE SCHOOLS APP_______________");
        System.exit(0);
    }
}
