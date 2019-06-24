package com.generalassembly.oop.intro;

public class VisibilityExamples {

    public class SomePublic {
        public int pubField;
    }
    private class SomePrivate {
        public int pubField;
    }
    class SomeDefault {
    }
    protected class SomeProtected {

        public int pubField;
    }
    VisibilityExamples.SomePublic sspub;
    VisibilityExamples.SomeProtected ssprot;
    VisibilityExamples.SomeDefault ssdefault;
    VisibilityExamples.SomePrivate ssprivate;
}

class SomeDefaultClass {
    VisibilityExamples.SomePublic sspub;
    VisibilityExamples.SomeProtected ssprot;
    VisibilityExamples.SomeDefault ssdefault;
}
