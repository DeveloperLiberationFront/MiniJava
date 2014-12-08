package notifications.implication;

import syntax.Expression;
import syntax.Return;
import syntax.Syntax;
import syntax.Type;
import checker.Env;
import checker.VarEnv;

public class MustHaveType extends Consequent {

	private Object programElement;
	private Type type;

	public MustHaveType(Syntax synt, Type type) {
		this.programElement = synt;
		this.type = type;
	}

	public MustHaveType(Env env, Type type) {
		this.programElement = env;
		this.type = type;
	}

	@Override
	String getText() {
		return this.programElement.toString() + " must be of type " + this.type.toString();
	}

}
