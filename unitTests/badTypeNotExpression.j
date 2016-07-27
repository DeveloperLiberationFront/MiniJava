class OperatorCantBeApplied {
    boolean m() {
        return -(new OperatorCantBeApplied());
    }
}
//OperatorCantBeApplied.java

//Concepts: negation operator, types
//References: neg-op, expression, (phantom) expression type, phantom neg-op argument type
//Link: mismatch(typeof(expression),typeof(arg(not)))
//Hint: (none)