package compiler;

import checker.UnterminatedProgramElementDiagnostic;

public class UnterminatedSyntaxDiagnostic extends RichDiagnostic implements
		UnterminatedProgramElementDiagnostic {

	private Position pos;
	// things we want: antecedant, expected consequent 
	public UnterminatedSyntaxDiagnostic(Position pos, Position pos, Token symbol) {
		this.pos = pos;
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
