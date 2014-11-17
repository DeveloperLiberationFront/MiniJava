package notifications.diagnostics;

import notifications.RichDiagnostic;
import syntax.SuperAccess;
import compiler.Declaration;
import compiler.Position;

public class UnboundSuperCallDiagnostic extends RichDiagnostic implements UnboundDiagnosticInterface {

	private Declaration subclassDeclaration;
	private SuperAccess superAccess;

	public UnboundSuperCallDiagnostic(SuperAccess superAccess,
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
