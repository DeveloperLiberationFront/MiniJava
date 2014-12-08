package notifications.thrownerrors;

import notifications.implication.Exists;
import notifications.implication.MustNotExist;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import syntax.ClassAccess;
import syntax.ClassType;

public class NonStaticClassAccessError extends CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public NonStaticClassAccessError(ClassAccess classAccess, ClassType owner) {
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new Exists(classAccess), 
				new MustNotExist(owner.getDeclaration())); // point this to modifier at the declaration
	}

}
