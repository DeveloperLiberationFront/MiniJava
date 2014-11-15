package notifications;

import checker.MethEnv;

public class MissingReturnStatementError extends CompilerDiagnosticBuilder {

	private StatementContractDiagnostic missingStatementContractDiagnostic;

	public MissingReturnStatementError(MethEnv methEnv) {
		this.missingStatementContractDiagnostic = new StatementContractDiagnostic(methEnv, new NonVoidMethodReturnContract());
	}

}
