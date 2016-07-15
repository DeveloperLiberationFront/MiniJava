class Test {
    int x;
    public Test(int test) {
        x = test;
    }
    public void test() {
    }
}

class Test2 extends Test {
    public Test2() {
        test();
        super(17); /* must be the first statement */
    }
    public void test2() {
        super.x = 5;
        super.test();
        super.x = 10;
        super.test();
    }
}

class Main {
    public static void main() {
        Test2 t;
        t = new Test2();
        t.test2();
    }
}
