package checker;

import syntax.Expression;
import syntax.Return;
import syntax.Statement;
import compiler.Position;
import compiler.RichDiagnostic;

public class MissingLanguageRequiredStatementFailure extends RichDiagnostic {

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
	
	public MissingLanguageRequiredStatementFailure(Position pos, Expression exp) {
		this.pos = pos;
		this.exp = exp;
	}

	public MissingLanguageRequiredStatementFailure(Statement stat) {
	}

}
