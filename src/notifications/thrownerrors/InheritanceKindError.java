package notifications.thrownerrors;

import syntax.ClassType;
import syntax.Type;

import compiler.Position;
import compiler.RichDiagnostic;

public class InheritanceKindError extends RichDiagnostic {

	private ClassType inheritingClass;
	private Type type;
	private ClassType ancestor;

	public InheritanceKindError(ClassType inheritingClass, Type t,
			ClassType ancestor) {
		this.inheritingClass = inheritingClass;
		this.type = t;
		this.ancestor = ancestor;
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
