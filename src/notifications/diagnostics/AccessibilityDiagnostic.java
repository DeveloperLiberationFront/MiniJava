package notifications.diagnostics;

import syntax.ClassType;
import syntax.Modifiers;
import checker.MemberEnv;
import compiler.Position;
import notifications.RichDiagnostic;

public class AccessibilityDiagnostic extends RichDiagnostic {

	private ClassType owner;
	private MemberEnv memberEnv;
	private Modifiers mods;
	private ClassType cls;

	public AccessibilityDiagnostic(ClassType owner, MemberEnv memberEnv,
			Modifiers mods, ClassType cls) {
				this.owner = owner;
				this.memberEnv = memberEnv;
				this.mods = mods;
				this.cls = cls;
	}

	@Override
	public String getText() {
		return this.memberEnv.describe() + " (declared in " + owner + ")" +
				" is inaccessible from " + cls + " because it is " + mods.describe();
	}

	@Override
	public Position getPos() {
		// TODO Auto-generated method stub
		return null;
	}

}
