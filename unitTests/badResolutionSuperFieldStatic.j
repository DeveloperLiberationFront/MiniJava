class Y{int i;}

class X extends Y{
    static void m() {
    	super.i = 1;
    }
}
///CantResolveLocationArgs.java, kinda NonStaticCantBeRef.java

//Concepts: fields, static methods
//References: i reference in static context, i field decl
//Link: unbound
//Hint: cantCallFromContext/99% 