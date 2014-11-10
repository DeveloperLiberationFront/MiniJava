package syntax;

import interp.State;

import org.llvm.Value;

import checker.Context;
import checker.VarEnv;
import codegen.Assembly;
import codegen.LLVM;

import compiler.Diagnostic;
import compiler.Position;

public class ExtendsExpression extends Expression {

	@Override
	public Value llvmGen(LLVM l) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type typeOf(Context ctxt, VarEnv env) throws Diagnostic {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void compileExpr(Assembly a, int free) {
		// TODO Auto-generated method stub

	}

	@Override
	public interp.Value eval(State st) {
		// TODO Auto-generated method stub
		return null;
	}

}
