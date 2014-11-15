package notifications.thrownerrors;

import notifications.diagnostics.ExplicitTypeContractDiagnostic;
import syntax.Expression;
import syntax.Type;
import checker.FieldEnv;

public class FieldTypeError extends CompilerDiagnosticBuilder {

	private ExplicitTypeContractDiagnostic typeError;

	public FieldTypeError(FieldEnv errorField, Type errorFieldType, Expression declaration,
			Type declarationType) {
		this.typeError = new ExplicitTypeContractDiagnostic(declaration, declarationType, declaration);
	}

}
