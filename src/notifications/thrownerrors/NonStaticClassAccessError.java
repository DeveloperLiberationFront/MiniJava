package notifications.thrownerrors;

import notifications.diagnostics.AccessibilityContractDiagnostic;
import syntax.ClassAccess;
import syntax.ClassType;

public class NonStaticClassAccessError extends CompilerDiagnosticBuilder {

	private AccessibilityContractDiagnostic accessibilityContractDiagnostic;

	public NonStaticClassAccessError(ClassAccess classAccess, ClassType owner) {
		this.accessibilityContractDiagnostic = new AccessibilityContractDiagnostic();
	}

}
