package notifications.thrownerrors;

import notifications.diagnostics.UnboundNameDiagnostic;
import syntax.Name;
import syntax.NewExpr;
import checker.Context;

public class UnboundClassNameInExpressionDiagnostic extends
		CompilerDiagnosticBuilder {

	private UnboundNameDiagnostic unboundNameDiagnostic;

	public UnboundClassNameInExpressionDiagnostic(Name name, Context ctxt,
			NewExpr newExpr) {
		this.unboundNameDiagnostic = new UnboundNameDiagnostic(name, ctxt, newExpr);
	}
}
