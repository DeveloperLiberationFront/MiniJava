package notifications.thrownerrors;

import notifications.diagnostics.AccessibilityDiagnostic;
import syntax.ClassAccess;
import syntax.ClassType;

public class NonStaticClassAccessError extends CompilerDiagnosticBuilder {

	private AccessibilityDiagnostic accessibilityContractDiagnostic;

	public NonStaticClassAccessError(ClassAccess classAccess, ClassType owner) {
		this.accessibilityContractDiagnostic = new AccessibilityDiagnostic(null, null, null, owner);
	}

}
