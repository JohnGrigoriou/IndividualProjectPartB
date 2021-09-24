package utils;

import java.util.Scanner;

public class Validation {
        
    public static int positiveIntInput() {
        boolean flag;
        Scanner sc = new Scanner(System.in);
        String input;
        int number = -1;
        do{
            do {
                System.out.println("\nPLEASE GIVE AN ID NUMBER...\n");
                try {
                    input = sc.nextLine().trim();
                    number = Integer.parseInt(input);
                    flag = true;
                } catch (NumberFormatException e) {
                    Color.println(Color.RED, "WRONG INPUT!!!\n");
                    flag = false;
                }
            } while (!flag);
        }while(number<0);
        return number;
    }
    
    public static boolean isDouble(String input){
       boolean flag = true;
        try {
            Double.parseDouble(input);
        }catch(NumberFormatException e) {
            flag = false;
        }
        return flag;
   }
    
    public static int giveOnlyInteger(){
        Scanner sc = new Scanner(System.in);
        int result;
        while(true){
            try{
                String inp = sc.nextLine().trim();
                int number = Integer.parseInt(inp);             
                result=number;
                break;}
            catch(NumberFormatException e){                                       
                Color.println(Color.RED, "MARK MUST BE AN INTEGER");
            }
        }
        return result;
    }
    
    public static int giveOnlyInteger(int min, int max){
        int number = giveOnlyInteger();
        while(number < min || number > max){
            Color.println(Color.RED, "MARK MUST BE BETWEEN "+min+" AND "+max);
            number = giveOnlyInteger();
        }
        return number;
    }
}