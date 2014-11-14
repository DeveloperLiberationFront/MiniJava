package notifications;

import syntax.Expression;
import syntax.Type;

import compiler.Position;
import compiler.RichDiagnostic;

public class MissingFieldDiagnostic extends RichDiagnostic {

	private Type classType;
	private Expression invocation;

	public MissingFieldDiagnostic(Expression invocation, Type type) {
		this.invocation = invocation;
		this.classType = type;
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
