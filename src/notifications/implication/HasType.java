package notifications.implication;

import syntax.Expression;
import syntax.Type;
import checker.Env;
import checker.MethEnv;
import checker.VarEnv;

public class HasType extends Antecedent {

	private Object programElement;
	private Type type;

	public HasType(Env env, Type type) {
		this.programElement = env;
		this.type = type;
	}

	public HasType(Expression expression, Type type) {
		this.programElement = expression;
		this.type = type;
	}

	@Override
	String getText() {
		return this.programElement.toString() + " is of type " + this.type.toString() + " (declared at xxx)";
	}

}
