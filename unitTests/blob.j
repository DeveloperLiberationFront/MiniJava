class Blob {
    int y;
}

class TestObj {
    static Blob b;
}

class Main {
    static void main() {
        TestObj.b = new Blob();
        TestObj.b.y = 3;
    }
}

