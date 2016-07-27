class X {
	void n(){}
    void m() {
        int i;
        i = n();
    }
}
//CompressedDiags.java

//Concepts: variables, methods, types
//References: assignment to i, n call, n decl, n return type, i type
//Link: mismatch(typeof(i), typeof(n))
//Hint: (none)
//Notes: using typeof to refer to return types should be made consistent