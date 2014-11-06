package checker;

import syntax.AccessibilityDiagnostic;
import syntax.ClassType;
import compiler.Position;
import compiler.RichDiagnostic;

public class PermissionAccessibilityDiagnostic extends RichDiagnostic implements AccessibilityDiagnostic {
	private ClassType cls;
	private MemberEnv member;

	public PermissionAccessibilityDiagnostic(MemberEnv member, ClassType cls) {
		this.member = member;
		this.cls = cls;
	}

	@Override
	public String getText() {
		String modsDescription = this.member.getMods().describeAccessibilityModifiers();
		if (modsDescription.isEmpty()) {
			modsDescription = "no modifiers";
		} else {
			modsDescription = "'" + modsDescription + "'";
			if (modsDescription.contains(" ")) {
				modsDescription = "modifiers " + modsDescription; 
			} else {
				modsDescription = "modifier " + modsDescription; 			
			}
		}

		return "Permission Accessibilty error: " + this.cls.toString() +
				" cannot access " + this.member.describe() + 
				" at " + this.cls.getPos().describe() + 
				"\n\t(" + this.member.describe() + " declared with " + modsDescription +
				" at " + this.member.getPos().describe() + ")";
	}

	@Override
	public Position getPos() {
		return this.cls.getPos();
	}
	
	

}
