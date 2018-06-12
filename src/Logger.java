public class Logger{
    //DEBUG < INFO < WARN < ERROR < FATAL
        static boolean debugEnabled=false; // GameLauncher -d
        static boolean deepDebugEnabled=false; // GameLauncher -dd

    private static void printMessage(String level, String message){
        System.out.println(level+" "+message);
    }

    static boolean isDebugEnabled(){
        return debugEnabled;
    }
    static boolean isDeepDebugEnabled(){
        return deepDebugEnabled;
    }

    static void setDebugEnabled(){
        debugEnabled=true;
    }
    static void setDeepDebugEnabled(){
        deepDebugEnabled=true;
    }

    static void fatal(String message){
        printMessage("FATAL:",message);
    }
    static void info(String message){
         printMessage("Info:",message);
    }
    static void debug(String message){
        if (debugEnabled) printMessage("Debug:",message);
    }
    static void deepDebug(String message){
    if (deepDebugEnabled) printMessage("DeepDebug:",message);
}

}
