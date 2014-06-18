package oop.ex7.main;
import java.util.regex.Pattern;


public class RegexConstants {

    public static int RETURN_CODE_CORRECT = 0;
    public static int RETURN_CODE_INCORRECT = 1;
    public static int RETURN_CODE_IOERROR = 2;

    public static String NEWLINE_CHAR = "\n";
    
    public static String BRACKET_OPEN_CHAR = "{";
    public static String BRACKET_CLOSE_CHAR = "}";



    // regular expressions
    public static class RegexPatterns {
        // comments patterns
        public Pattern LINE_END_COMMENTS = Pattern.compile("//.+?\\n");
        public Pattern LINE_COMMENTS = Pattern.compile("/\\*\\*?[^\\n(\\*/)]*?\\*/");
        
        // tabs and spaces patterns
        public Pattern LEADING_SPACES = Pattern.compile("(\\n\\s+)|(^\\s+)");
        public Pattern TRAILING_SPACES = Pattern.compile("(\\s+\\n)|(\\s+$)");
        
        // empty lines
        public Pattern EMPTY_LINES = Pattern.compile("[\\n]+");
        
        // value types
        public Pattern VALUE_BOOLEAN = Pattern.compile("\\s*(true|false)\\s*");
        public static Pattern VALUE_INT = Pattern.compile("\\s*-?\\d+\\s*");
        public Pattern VALUE_DOUBLE = Pattern.compile("\\s*-?\\d+(\\.\\d+)?\\s*");
        public Pattern VALUE_STRING = Pattern.compile("\\s*\\\".+?\\\"\\s*");
        public Pattern VALUE_CHAR = Pattern.compile("\\s*'.'\\s*");
        
        // commands patterns
        static public Pattern VARIABLE_DECLARATION = Pattern.compile(
                "([A-Za-z0-9_]+);|([A-Za-z][A-Za-z0-9_]*)(\\s*=\\s*([^;]+))?;");

       static public Pattern METHOD_DECLARATION = Pattern.compile(
                "([A-Za-z][A-Za-z0-9_]*)\\s*\\((.*?)\\)\\s*\\{(.*)",
                Pattern.DOTALL);

        public Pattern METHOD_ARGUMENTS = Pattern.compile(
                "\\s*(_|[A-Za-z][A-Za-z0-9_]*)\\s*");

        public static Pattern METHOD_CALL = Pattern.compile(
                "\\s*([A-Za-z][A-Za-z0-9_]*)\\s*\\(([^\\n]*)\\)\\s*;?\\s*");

        public static Pattern CONDITION_COMMAND = Pattern.compile(
                "(if|while)\\s*\\(([^{]+)\\)\\s*\\{(.*)", Pattern.DOTALL);

        public Pattern RETURN_COMMAND = Pattern.compile(
                "\\s*(return)(\\s+(.+))?;");

        public Pattern ASSIGNMENT_COMMAND = Pattern.compile(
                "((_|[A-Za-z])[A-Za-z0-9_]*)\\s+=\\s+(.+);");
    }
}