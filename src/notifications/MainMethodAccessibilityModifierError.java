package notifications;

import checker.MethEnv;

import compiler.Position;
import compiler.RichDiagnostic;

public class MainMethodAccessibilityModifierError extends RichDiagnostic {
	
	private AccessibilityContractDiagnostic accessibilityContractDiagnostic;
	
	public MainMethodAccessibilityModifierError(MethEnv method, int missingModifierMask) {
		accessibilityContractDiagnostic = new AccessibilityContractDiagnostic();  
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
