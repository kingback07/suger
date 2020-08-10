package org.kingback.suger.explorer.clsloader;

public class ClassLoaderTest {

    public static void main(String[] args){
//        System.out.println(B.value);

//        A[] arrays=new A[10];
//        System.out.println(A.desc);
//        B b=new B();
    }
}

class A{
    static {
        System.out.println("init A");
    }
    static int value=100;
    static final String desc="testA";
}

class B extends A{
    static {
        System.out.println("init B");
    }
}
