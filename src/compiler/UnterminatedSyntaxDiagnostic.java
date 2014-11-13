package compiler;

import lexer.RichTokens;
import syntax.Tokens;
import checker.UnterminatedProgramElementDiagnostic;

public class UnterminatedSyntaxDiagnostic extends RichDiagnostic implements
		UnterminatedProgramElementDiagnostic {

	private Position pos;
	private RichTokens end;
	private RichTokens begin;
	// things we want: antecedant, expected consequent 
	public UnterminatedSyntaxDiagnostic(Position startPos, Position endPos, Tokens symbol) {
		this.pos = pos;
	}

	public UnterminatedSyntaxDiagnostic(RichTokens begin,
			RichTokens end) {
		this.begin = begin;
		this.end = end;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getPos() {
		// TODO Auto-generated method stub
		return null;
	}

}
