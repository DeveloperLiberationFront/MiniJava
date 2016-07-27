class Y{}

class X extends Y{
    static void m() {
    	super.foo = 1;
    }
}
//not a problem for Java 

//Concepts: fields, field references
//References: unknown reference
//Link: unbound
//Hint: set of fields in superclass/50%, set of methods in superclass/10%