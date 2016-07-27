class X {
    boolean m() {
    	return 0 & true;
    }
}
//CompressedDiags.java

//Concepts: bitwise operators, types
//References: bitop, left expression, right expression, phantom bitop type signature, (phantom) left expression signature, (phantom) right expression signature
//Link: triple-mismatch(bitop type, lhs type, rhs type)
//Hint: (none)
//Note: Type error on (overloaded) primitives is complicated!