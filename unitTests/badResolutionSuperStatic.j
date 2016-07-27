class Y{void foo(){}}

class X extends Y{
    static void m() {
    	super.foo();
    }
}
//sorta NonStaticCantBeRef.java, kinda NonStaticCantBeRef.java

//Concepts: instance methods, static methods
//References: foo reference in static context, foo method decl
//Link: unbound
//Hint: cantCallFromContext/99% 