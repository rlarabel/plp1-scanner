package scanner;

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
        token.addValue(Token.EOF);
        return token;
    }

    public static void main(String[] args) {
        String input = String.join(" ", args);
        System.out.println(input);
        /*PLp1Scanner scanner = (new PLp1Scanner()).addInput(input);

        for (Token token = scanner.getNextToken(); token.getValue() != Token.EOF; token = scanner.getNextToken())
            System.out.println("Token value = " + token.getValue() + ", lexeme = " + token.getLexeme()); */
    }
}
