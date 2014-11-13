package notifications;

import syntax.Expression;
import syntax.Type;
import syntax.VarDecls;

import compiler.Position;

public class LocalVarDeclTypeError extends CompilerDiagnosticBuilder {

	private ExplicitTypeContractDiagnostic typeError;

	public LocalVarDeclTypeError(VarDecls vs, Type expressionType,
			Expression contractProvider, Type type) {
		this.typeError = new ExplicitTypeContractDiagnostic(contractProvider, expressionType,
				contractProvider);
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getPos() {
		// TODO Auto-generated method stub
		return null;
	}

}
