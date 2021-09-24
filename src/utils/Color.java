package utils;

public enum Color {
    
    RED("\033[0;31m"),
    GREEN("\033[0;32m"),
    YELLOW("\033[0;33m"),
    PURPLE("\033[0;35m"),
    CYAN("\033[0;36m"),
    PURPLE_BACK("\033[45m");    

    private final String code;

    private Color(String code) {
        this.code = code;
    }
    
    public static void println(Color color, String txt){
        System.out.println(color+txt+"\033[0m");
    }  
    
    public static void print(Color color, String txt){
        System.out.print(color+txt+"\033[0m");
    }  
    
    public static void line(){
        System.out.println("-------------------------------------------------------------------------");
    }
    
    @Override
    public String toString() {
        return code;
    }

}
