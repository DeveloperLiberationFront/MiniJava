package notifications.thrownerrors;

import notifications.diagnostics.UnboundNameDiagnostic;
import notifications.implication.Exists;
import notifications.implication.MustExist;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import syntax.ClassType;
import syntax.Id;
import syntax.Name;
import syntax.NewExpr;
import checker.Context;

public class UnboundClassNameInExpressionDiagnostic extends
		CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public UnboundClassNameInExpressionDiagnostic(Name name, Context ctxt,
			NewExpr classReference) {
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new Exists(classReference), 
				new MustExist(new ClassType(null, new Id(null, name.toString()), null, null, null)));
	}
}
