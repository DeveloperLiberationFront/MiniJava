package syntax;

import compiler.Position;
import compiler.RichDiagnostic;

public class CyclicInheritanceDiagnostic extends RichDiagnostic {

	private ClassType classType;

	public CyclicInheritanceDiagnostic(ClassType classType) {
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
