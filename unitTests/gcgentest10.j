// Small program for testing gen garbage collector:
//   Test two objects with gc.
// Create one object and also assign it to a 2nd object

class TestObjOne {
    int x;
    int z;
}

class Main {
    static void main() {
        int x;
        int loops;
        TestObjOne oo;

        oo = new TestObjOne();
        oo.x = 42;


        x = 0;
        loops = 1000000;
        while (x < loops) {
            oo = new TestObjOne();
            oo.x = x;
            TestObjOne ocopy;
            ocopy = oo;
            x = x + 1;
        }
    }
}

