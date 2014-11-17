package notifications.thrownerrors;

import notifications.RichDiagnostic;
import notifications.diagnostics.AccessibilityContractDiagnostic;
import notifications.diagnostics.AccessibilityDiagnostic;
import checker.MethEnv;
import compiler.Position;

public class MainMethodAccessibilityModifierError extends CompilerDiagnosticBuilder {
	
	private AccessibilityDiagnostic accessibilityContractDiagnostic;
	
	public MainMethodAccessibilityModifierError(MethEnv method, int missingModifierMask) {
		accessibilityContractDiagnostic = new AccessibilityDiagnostic(null, method, null, null);  
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
