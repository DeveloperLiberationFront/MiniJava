package notifications.thrownerrors;

import notifications.diagnostics.SyntaxRequiresTypeDiagnostic;
import syntax.ArrayAccess;
import syntax.ArrayType;

public class ArrayAccessOnNonArrayError extends CompilerDiagnosticBuilder {

	private SyntaxRequiresTypeDiagnostic typeError;

	public ArrayAccessOnNonArrayError(ArrayAccess arrayAccess,
			ArrayType arrayType) {
		this.typeError = new SyntaxRequiresTypeDiagnostic(arrayAccess, arrayType);
	}

}
