package notifications.thrownerrors;

import java.util.ArrayList;

import notifications.IsDeclaration;
import notifications.implication.Consequent;
import notifications.implication.HasType;
import notifications.implication.MustHaveSubtype;
import notifications.implication.MustHaveType;
import notifications.implication.OrConsequent;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import syntax.Type;
import checker.Env;
import checker.VarEnv;

public class TooManyArgumentsError extends CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public TooManyArgumentsError(VarEnv env, IsDeclaration formalsDeclaration, Type formalType) {
		ArrayList<Consequent> consequents = new ArrayList<Consequent>();
		consequents.add(new MustHaveType(env, formalType));
		consequents.add(new MustHaveSubtype(env, formalType));
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new HasType((Env) formalsDeclaration, formalType),
				new OrConsequent(consequents));
	}

}
