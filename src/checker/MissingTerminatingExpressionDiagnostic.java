package checker;

import syntax.Expression;
import syntax.Return;
import syntax.Statement;
import compiler.Position;
import compiler.RichDiagnostic;

public class MissingTerminatingExpressionDiagnostic extends RichDiagnostic
	implements UnterminatedProgramElementDiagnostic {

	private Position pos;
	private Expression exp;

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
	
//	public MissingTerminatingExpressionDiagnostic(Position pos, Expression exp) {
//		this.pos = pos;
//		this.exp = exp;
//	}
	
	// pass in context information?
	// the two things here are the return type and the missing thing
	public MissingTerminatingExpressionDiagnostic(Statement stat) {
	}

}
