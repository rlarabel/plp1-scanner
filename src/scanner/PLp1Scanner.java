package scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PLp1Scanner {

    private String input;

    /**
     * Create a new PLp1Scanner
     */
    public PLp1Scanner() {}

    /**
     * Add input string for processing
     * @param input
     * @return the PLp1Scanner
     */
    public PLp1Scanner addInput(String input) {
        this.input = input;
        return this;
    }

    public String getInput() {
        return input;
    }

    /**
     * Get the next token in the input stream
     * @return the next token
     */
    public Token getNextToken() {
        Token token = new Token();
        boolean nextDotIsFloat = false, isTwoCharSymbol = false;
        String splitString  = this.getInput(), regex = "([^ ])*([^A-Za-z0-9#\\ ]+)([^ ])*", symbolRegex = "[^A-Za-z0-9#\\ ]+";
        String[] scanString = new String[2], commentString, strString, twoCharSymbols = new String[] {"->", "==", "!=", ">=", "<="};
        Pattern pat = Pattern.compile(regex), symbolPat = Pattern.compile(symbolRegex);
        Matcher mat = pat.matcher(splitString), symbolMat = symbolPat.matcher(splitString);

        splitString = splitString.replaceAll("[\n\t]", " ");
        splitString = splitString.trim();


        // Index of delimiters
        int spaceIndex = splitString.indexOf(" ");
        int tabIndex = splitString.indexOf("\t");
        int newLineIndex = splitString.indexOf("\n");
        int commentIndex = splitString.indexOf("//");
        int stringIndex = splitString.indexOf("'");
        int dotIndex = splitString.indexOf(".");

        if (dotIndex > 0) {
            nextDotIsFloat = splitString.substring(dotIndex - 1, dotIndex + 2).matches("[0-9]\\.[0-9]");
        }
        symbolMat.find();
        if(!mat.find() || (mat.start() + 1 == mat.end()) || (dotIndex <= symbolMat.start() && nextDotIsFloat) || (spaceIndex < mat.start() && spaceIndex != -1) || (tabIndex < mat.start() && tabIndex != -1) || (newLineIndex < mat.start() && newLineIndex != -1)) {
            scanString = splitString.split(" |\t|\n", 2);
        } else {
            if (symbolMat.start() == 0) {

                for(int i  = 0; i < twoCharSymbols.length; i++) {
                    if(splitString.startsWith(twoCharSymbols[i])) {
                        isTwoCharSymbol = true;
                    }
                }
                if (isTwoCharSymbol) {
                    scanString[0] = splitString.substring(0, 2);
                    scanString[1] = splitString.substring(2);
                } else {
                    scanString[0] = splitString.substring(0, 1);
                    scanString[1] = splitString.substring(1);
                }
            } else {

                scanString[0] = splitString.substring(0, symbolMat.start());
                scanString[1] = splitString.substring(symbolMat.start());
            }
        }
                
        if (scanString[0].isEmpty()) {
            token.addValue(Token.EOF);
        } else {                  
            // Adds a token
            if (commentIndex == 0) {
                commentString = splitString.split("//", 2);
                token.addValue(Token.COMMENT);
                token.addLexeme("COMMENT");

                if (newLineIndex > 0) {
                    scanString = splitString.split("\n", 2);
                } else {
                    scanString[1] = "";
                }
            } else if (stringIndex == 0) {
                strString = splitString.split("'", 2);
                stringIndex = strString[1].indexOf("'");
                if (stringIndex > -1) {
                    token.addValue(Token.STRING);
                    token.addLexeme("STRING");
                    scanString = strString[1].split("'", 2);
                } else {
                    token.addValue(Token.ERROR);
                    token.addLexeme("ERROR");
                    if (scanString.length > 1) {
                        scanString[1] = "";
                    }
                }


            } /*else if(scanString[0] == "case"){
                token.addLexeme("CASE");
                token.addValue(Token.CASE);
            } */else if(scanString[0].equals("class")){
                token.addLexeme("CLASS");
                token.addValue(Token.CLASS);
            } else if(scanString[0].equals("create")){
                token.addLexeme("CREATE");
                token.addValue(Token.CREATE);
            } else if(scanString[0].equals("default")){
                token.addLexeme("DEFAULT");
                token.addValue(Token.DEFAULT);
            } else if(scanString[0].equals("endif")){
                token.addLexeme("ENDIF");
                token.addValue(Token.ENDIF);
            } else if(scanString[0].equals("else")){
                token.addLexeme("ELSE");
                token.addValue(Token.ELSE);
            } else if(scanString[0].equals("false")){
                token.addLexeme("FALSE");
                token.addValue(Token.FALSE);
            } else if(scanString[0].equals("function")){
                token.addLexeme("FUNCTION");
                token.addValue(Token.FUNCTION);
            } else if(scanString[0].equals("if")){
                token.addLexeme("IF");
                token.addValue(Token.IF);
            } else if(scanString[0].equals("init")){
                token.addLexeme("INIT");
                token.addValue(Token.INIT);
            } else if(scanString[0].equals("lambda")){
                token.addLexeme("LAMBDA");
                token.addValue(Token.LAMBDA);
            } else if(scanString[0].equals("let")){
                token.addLexeme("LET");
                token.addValue(Token.LET);
            } else if(scanString[0].equals("method")){
                token.addLexeme("METHOD");
                token.addValue(Token.METHOD);
            } else if(scanString[0].equals("null")){
                token.addLexeme("NULL");
                token.addValue(Token.NULL);
            } else if(scanString[0].equals("switch")){
                token.addLexeme("SWITCH");
                token.addValue(Token.SWITCH);
            } else if(scanString[0].equals("then")){
                token.addLexeme("THEN");
                token.addValue(Token.THEN);
            } else if(scanString[0].equals("true")){
                token.addLexeme("TRUE");
                token.addValue(Token.TRUE);
            } else if(scanString[0].equals("&")){
                token.addLexeme("AND");
                token.addValue(Token.AND);
            } else if(scanString[0].equals("=")){
                token.addLexeme("ASSIGN");
                token.addValue(Token.ASSIGN);
            } else if(scanString[0].equals(",")){
                token.addLexeme("COMMA");
                token.addValue(Token.COMMA);
            } else if(scanString[0].equals(":")){
                token.addLexeme("COLON");
                token.addValue(Token.COLON);
            } else if(scanString[0].equals("/")){
                token.addLexeme("DIVIDE");
                token.addValue(Token.DIV);
            } else if(scanString[0].equals(".")){
                token.addLexeme("DOT");
                token.addValue(Token.DOT);
            } else if(scanString[0].equals("==")){
                token.addLexeme("EQUAL");
                token.addValue(Token.EQ);
            } else if(scanString[0].equals(">")){
                token.addLexeme("GT");
                token.addValue(Token.GT);
            } else if(scanString[0].equals(">=")){
                token.addLexeme("GE");
                token.addValue(Token.GE);
            } else if(scanString[0].equals("->")){
                token.addLexeme("CALL");
                token.addValue(Token.CALL);
            } else if(scanString[0].equals("{")){
                token.addLexeme("LB");
                token.addValue(Token.LB);
            } else if(scanString[0].equals("[")){
                token.addLexeme("LBK");
                token.addValue(Token.LBK);
            } else if(scanString[0].equals("<")){
                token.addLexeme("LT");
                token.addValue(Token.LT);
            } else if(scanString[0].equals("<=")){
                token.addLexeme("LE");
                token.addValue(Token.LE);
            } else if(scanString[0].equals("(")){
                token.addLexeme("LP");
                token.addValue(Token.LP);
            } else if(scanString[0].equals("-")){
                token.addLexeme("MINUS");
                token.addValue(Token.MINUS);
            } else if(scanString[0].equals("*")){
                token.addLexeme("MULTIPLY");
                token.addValue(Token.MULT);
            } else if(scanString[0].equals("!")){
                token.addLexeme("NOT");
                token.addValue(Token.NOT);
            } else if(scanString[0].equals("!=")){
                token.addLexeme("NE");
                token.addValue(Token.NE);
            } else if(scanString[0].equals("|")){
                token.addLexeme("OR");
                token.addValue(Token.OR);
            } else if(scanString[0].equals("+")){
                token.addLexeme("PLUS");
                token.addValue(Token.PLUS);
            } else if(scanString[0].equals("}")){
                token.addLexeme("RB");
                token.addValue(Token.RB);
            } else if(scanString[0].equals("]")){
                token.addLexeme("RBK");
                token.addValue(Token.RBK);
            } else if(scanString[0].equals(")")){
                token.addLexeme("RP");
                token.addValue(Token.RP);
            } else {
                int idFlag = 0, intNumFlag = 0, floatNumFlag = 0;
                String[] checkStr;
                if (dotIndex > 0) {
                    checkStr = scanString[0].split("\\.", 2);
                    intNumFlag = 1;  
                } else {
                    checkStr = new String[]{scanString[0], ""};
                    floatNumFlag = 1;
                }

                // Checking first char 
                if (!Character.isLetter(checkStr[0].charAt(0))) {
                    idFlag = 1;
                }
                if (checkStr[0].length() == 1 && !Character.isDigit(checkStr[0].charAt(0))) {
                    intNumFlag = 1;
                    floatNumFlag = 1;
                } else if (checkStr[0].length() > 1 && (!Character.isDigit(checkStr[0].charAt(0)) || checkStr[0].charAt(0) == '0')) {
                    intNumFlag = 1;
                    floatNumFlag = 1;
                }
                
                // Checking rest of string
                for(int i = 1; i < checkStr[0].length(); ++i){
                    if (!Character.isLetter(checkStr[0].charAt(i)) && !Character.isDigit(checkStr[0].charAt(i))) {
                        idFlag = 1;
                    }
                    if(!Character.isDigit(checkStr[0].charAt(i))) {
                        intNumFlag = 1;
                        floatNumFlag = 1;
                    }
                }

                if (floatNumFlag == 0) {
                    for (int i = 0; i < checkStr[1].length(); ++i) {
                        if (!Character.isDigit(checkStr[1].charAt(i))) {
                            floatNumFlag = 1;
                        } 
                    }
                }                

                if (idFlag == 0) {
                    token.addValue(Token.IDENTIFIER);
                    token.addLexeme("IDENTIFIER");
                } else if (floatNumFlag == 0) {
                    token.addValue(Token.FLOATNUM);
                    token.addLexeme("FLOATNUM");
                }else if (intNumFlag == 0) {
                    token.addValue(Token.INTNUM);
                    token.addLexeme("INTNUM");
                } else {
                    token.addValue(Token.ERROR);
                    token.addLexeme("ERROR");
                    if(scanString.length > 1) {
                        scanString[1] = "";
                    }    
                }
            }
            // Updates the string w/o the next token
            if (scanString.length > 1) {
                this.addInput(scanString[1]);            
            } else {
                this.addInput("");
            } 
        }

        
        return token;
    }

    public static void main(String[] args) {
        String input = String.join(" ", args);
        PLp1Scanner scanner = (new PLp1Scanner()).addInput(input);
        for (Token token = scanner.getNextToken(); token.getValue() != Token.EOF; token = scanner.getNextToken())
            System.out.println("Token value = " + token.getValue() + ", lexeme = " + token.getLexeme());
    }
}
