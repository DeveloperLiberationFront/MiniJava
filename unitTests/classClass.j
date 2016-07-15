class TestObjOne {
    int x;
    int z;
}

class TestObjTwo extends TestObjOne {
    int p;
    int q;
}

class Main {
    static void main() {
        TestObjOne oo;
        TestObjTwo pp;
        oo = new TestObjOne();
        pp = new TestObjTwo();
    }
}

