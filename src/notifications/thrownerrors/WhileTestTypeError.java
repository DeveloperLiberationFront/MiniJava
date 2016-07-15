package notifications.thrownerrors;

import notifications.diagnostics.ImplicitTypeContractDiagnostic;
import notifications.diagnostics.TypeMismatchDiagnostic;
import notifications.thrownerrors.interfaces.TypeErrorInterface;
import syntax.Expression;
import syntax.Type;
import syntax.While;

public class WhileTestTypeError extends CompilerDiagnosticBuilder 
implements TypeErrorInterface {

	private ImplicitTypeContractDiagnostic typeError;

	public WhileTestTypeError(Expression test,
			Type testExpressionActualType, While whileStatement) {
		this.typeError = new ImplicitTypeContractDiagnostic(
				test, testExpressionActualType, whileStatement);
	}

	public TypeMismatchDiagnostic getTypeError() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getText() {
		return this.typeError.getText();
	}

}
