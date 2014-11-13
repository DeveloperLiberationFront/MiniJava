package lexer;

import syntax.Tokens;

import compiler.Position;
import compiler.SourcePosition;

public class RichTokens implements Tokens {

	private String tokenChars;
	private Position pos;

	public RichTokens(int line, int col, String tokenChars) {
		this.pos = new SourcePosition(null, line, col);
		this.tokenChars = tokenChars;
	}
	
}
