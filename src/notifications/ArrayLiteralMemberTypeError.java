package notifications;

import syntax.ArrayLiteral;
import syntax.ArrayType;
import syntax.Expression;
import syntax.Type;

public class ArrayLiteralMemberTypeError extends CompilerDiagnosticBuilder {

	private ExplicitTypeContractDiagnostic typeError;

	public ArrayLiteralMemberTypeError(Expression arrayElement, Type actualArrayElementType,
			ArrayLiteral arrayLiteral, ArrayType correctArrayType) {
		this.typeError = new ExplicitTypeContractDiagnostic(arrayElement, actualArrayElementType,
				arrayLiteral);
	}

}
