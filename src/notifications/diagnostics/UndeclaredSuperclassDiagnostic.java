package notifications.diagnostics;

import notifications.RichDiagnostic;
import syntax.SuperAccess;
import compiler.Declaration;
import compiler.Position;

public class UndeclaredSuperclassDiagnostic extends RichDiagnostic {

	private Declaration subclassDeclaration;
	private SuperAccess superAccess;

	public UndeclaredSuperclassDiagnostic(SuperAccess superAccess,
			Declaration subclassDeclaration) {
		// instantiate "missing expression" and "scope accessibility" diagnostics
		this.superAccess = superAccess;
		this.subclassDeclaration = subclassDeclaration;
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
