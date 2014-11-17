package notifications.thrownerrors;

import notifications.diagnostics.UndeclaredSuperclassDiagnostic;
import syntax.SuperAccess;

import compiler.Declaration;

public class UndeclaredSuperclassError extends CompilerDiagnosticBuilder {

	private UndeclaredSuperclassDiagnostic undeclaredSuperclassDiagnostic;

	public UndeclaredSuperclassError(SuperAccess superAccess,
			Declaration declaration) {
    	this.undeclaredSuperclassDiagnostic = new UndeclaredSuperclassDiagnostic(superAccess, declaration); // needs representation of 'extends' modifier
	}

}
