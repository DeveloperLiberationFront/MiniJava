package notifications.thrownerrors;

import notifications.diagnostics.UnboundSuperCallDiagnostic;
import syntax.SuperAccess;

import compiler.Declaration;

public class UndeclaredSuperclassError extends CompilerDiagnosticBuilder {

	private UnboundSuperCallDiagnostic undeclaredSuperclassDiagnostic;

	public UndeclaredSuperclassError(SuperAccess superAccess,
			Declaration declaration) {
    	this.undeclaredSuperclassDiagnostic = new UnboundSuperCallDiagnostic(superAccess, declaration); // needs representation of 'extends' modifier
	}

}
