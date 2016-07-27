class A{
	private static void a(){}
}

class B {
    void x(){
    	A.a();
    }
}
//ReportAccess.java

//Concepts: static methods, instance methods
//References: a() call in instance context, a() method decl, static keyword
//Link: unbound
//Hint: cantCallFromContext/90%