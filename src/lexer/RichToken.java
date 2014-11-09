package lexer;

import compiler.Token;

public class RichToken extends Token {

	private int col;
	private String tokenChars;

	public RichToken(int line, int col, String tokenChars) {
		super(line);
		this.col = col;
		this.tokenChars = tokenChars;
	}
	
}
