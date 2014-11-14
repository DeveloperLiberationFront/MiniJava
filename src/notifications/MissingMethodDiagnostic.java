package notifications;

import syntax.ClassType;
import syntax.Invocation;

import compiler.Position;
import compiler.RichDiagnostic;

public class MissingMethodDiagnostic extends RichDiagnostic {

	private Invocation invocation;
	private ClassType classType;

	public MissingMethodDiagnostic(Invocation invocation,
			ClassType classType) {
		this.invocation = invocation;
		this.classType = classType;
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
