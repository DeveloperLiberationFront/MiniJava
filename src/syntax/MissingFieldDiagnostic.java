package syntax;

import compiler.Position;
import compiler.RichDiagnostic;

public class MissingFieldDiagnostic extends RichDiagnostic {

	private ClassType classType;
	private Invocation invocation;

	public MissingFieldDiagnostic(Invocation invocation, ClassType classType) {
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
