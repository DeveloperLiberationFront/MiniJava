package notifications.implication;

import checker.Env;
import checker.VarEnv;
import syntax.Syntax;
import syntax.Type;

public class MustHaveSubtype extends Consequent {

	private Object programElement;
	private Type type;

	public MustHaveSubtype(Syntax synt, Type type) {
		this.programElement = synt;
		this.type = type;
	}

	public MustHaveSubtype(Env env, Type type) {
		this.programElement = env;
		this.type = type;
	}

	@Override
	String getText() {
		// TODO Auto-generated method stub
		return this.programElement.toString() + " must have a type that is a subtype of " + this.type.toString();
	}

}
