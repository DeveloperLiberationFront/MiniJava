package notifications.thrownerrors;

import java.util.ArrayList;

import notifications.diagnostics.ExplicitTypeContractDiagnostic;
import notifications.implication.Consequent;
import notifications.implication.IsType;
import notifications.implication.MustBeSubType;
import notifications.implication.MustBeType;
import notifications.implication.OrConsequent;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import syntax.Expression;
import syntax.Type;
import checker.VarEnv;

public class ArgumentTypeError extends CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public ArgumentTypeError(Expression arg, Type argt, VarEnv formal, Type formalType) {
		ArrayList<Consequent> consequents = new ArrayList<Consequent>();
		consequents.add(new MustBeType(arg, formalType));
		consequents.add(new MustBeSubType(arg, formalType));
		
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new IsType(formal, formalType),
				new OrConsequent(consequents));
	}

}
