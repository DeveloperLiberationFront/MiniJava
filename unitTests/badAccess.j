class A{
	private static int a(){return 1;}
}

class C {
    int x(){
    	return A.a();
    }
}