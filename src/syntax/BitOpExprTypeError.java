package syntax;

import notifications.CompilerDiagnosticBuilder;
import notifications.ImplicitTypeContractDiagnostic;

public class BitOpExprTypeError extends CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public BitOpExprTypeError(Expression leftArgument, Type leftArgumentType,
			Expression rightArgument, Type rightArgumentType, BitOpExpr bitOpExpr) {
		this.typeError = new ImplicitTypeContractDiagnostic(leftArgument, leftArgumentType,
				rightArgument, rightArgumentType, bitOpExpr);
	}

}
