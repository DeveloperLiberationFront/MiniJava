package notifications;

import syntax.Tokens;

import compiler.Position;
import compiler.SourcePosition;

public class RichTokens implements Tokens {

	private String tokenChars;
	private Position pos;

	public RichTokens(Position pos, String tokenChars) {
		this.pos = pos;
		this.tokenChars = tokenChars;
	}
	
}
