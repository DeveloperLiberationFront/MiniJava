package notifications.thrownerrors;

import notifications.RichDiagnostic;
import syntax.Statement;
import compiler.Position;

public class DeadCodeError extends RichDiagnostic {

	private Statement deadStatement;

	public DeadCodeError(Statement deadStatement) {
		this.deadStatement = deadStatement;
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
