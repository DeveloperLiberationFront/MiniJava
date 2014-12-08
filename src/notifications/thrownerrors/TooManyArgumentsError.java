package notifications.thrownerrors;

import java.util.ArrayList;

import notifications.IsDeclaration;
import notifications.implication.Consequent;
import notifications.implication.HasType;
import notifications.implication.MustBeSubtype;
import notifications.implication.MustBeType;
import notifications.implication.OrConsequent;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import syntax.Type;
import checker.Env;
import checker.VarEnv;

public class TooManyArgumentsError extends CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public TooManyArgumentsError(VarEnv env, IsDeclaration formalsDeclaration, Type formalType) {
		ArrayList<Consequent> consequents = new ArrayList<Consequent>();
		consequents.add(new MustBeType(env, formalType));
		consequents.add(new MustBeSubtype(env, formalType));
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new HasType((Env) formalsDeclaration, formalType),
				new OrConsequent(consequents));
	}

}
