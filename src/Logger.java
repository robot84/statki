class Logger{
    //DEBUG < INFO < WARN < ERROR < FATAL
        static boolean debugEnabled=false; // Main -d
        static boolean deepDebugEnabled=false; // Main -dd
    private static final int START_FROM_PARENT_OF_MY_PARENT_CALL=2;

    private static void printMessage(String level, String message){
        System.out.println(level+" "+message);
    }
    private static void printMessage(String currentMethod, String level, String message){
        System.out.println(currentMethod+":: "+level+" "+message);
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

static void stackTrace(){

    StackTraceElement[] myTrace=Thread.currentThread().getStackTrace();
    System.out.println("Thread.currentThread().getStackTrace() said:");
    for(int i=START_FROM_PARENT_OF_MY_PARENT_CALL;(i<myTrace.length) &&
            (! myTrace[i].toString().substring(0,5).equals("javax"));i++)
        System.out.println(myTrace[i]);

}

}
