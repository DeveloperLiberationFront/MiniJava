package notifications.thrownerrors;

import java.util.ArrayList;

import notifications.implication.Consequent;
import notifications.implication.HasType;
import notifications.implication.MustBeSubtype;
import notifications.implication.MustBeType;
import notifications.implication.OrConsequent;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import syntax.Return;
import syntax.Type;
import checker.VarEnv;

public class MethodMustReturnValueError extends CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public MethodMustReturnValueError(Return returnStatement, VarEnv env) {
		Type envType = env.getType();
		ArrayList<Consequent> consequents = new ArrayList<Consequent>();
		consequents.add(new MustBeType(returnStatement, envType));
		consequents.add(new MustBeSubtype(returnStatement, envType));
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new HasType(env, envType),
				new OrConsequent(consequents));
	}

}
