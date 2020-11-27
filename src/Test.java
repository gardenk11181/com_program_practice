import cart.Cart;
public class Test {
    static void printOX(String msg, boolean condition) {
        String isRight = condition ? "O" : "X";
        System.out.println(msg + " : " + isRight);
    }
    public static void main(String[] args) {
        lecture2();
        lecture3();
        lecture4();
        lecture5();
        lecture6();
    }

    static void lecture2() {
        // byte : 8, short: 16, int: 32, long: 64
        System.out.println("-------------------------\nlecture2\n-------------------------");

        double d = 3.72;
        float f = 3.72f;
        long l = 100L;
    }

    static void lecture3() {
        System.out.println("-------------------------\nlecture3\n-------------------------");

        int[] array = {1,2,3};
        int[] array1 = new int[] {1,2,3};

        int[][] array2D = {
                {1,2},
                {3,4,5}
        };

        // save in string pool
        String str1 = "hi";
        String str2 = "hi";
        printOX("str1 == str2", str1==str2);

        // save in heap
        str1 = new String("hi");
        str2 = new String("hi");

        printOX("str1 == str2", str1 == str2);

    }
    static void swap(Car c1,Car c2) {
        int tmp = c1.id;
        c1.id = c2.id;
        c2.id = tmp;
    }

    static void lecture4() {
        System.out.println("-------------------------\nlecture4\n-------------------------");

        Car car = new Car(1);
        Car car1 = car;
        car.id = 3;
        printOX("car1.id == 3", car1.id == 3);

        printOX("car == car1",car==car1);

        Car car2 = new Car(5);
        swap(car1,car2);
        printOX("swap car1.id & car2.id", car1.id==5 && car2.id == 3);
    }

    static void lecture5() {
        System.out.println("-------------------------\nlecture5\n-------------------------");

        cart.Cart cart = new cart.Cart();
        // if we import cart.Cart
        Cart cart1 = new Cart();

        OuterClass outerClass = new OuterClass();
    }

    static void lecture6() {
        System.out.println("-------------------------\nlecture6\n-------------------------");
        // is-a => inheritance
        // has-a => composition

        String str = "hi";
        printOX("String :< Object", str instanceof Object);

        Parent parent = new Child();
        Child child = new Child();
        printOX("child :< Parent", child instanceof Parent);

        // override iff subclass, different implementation & signature, public or protected method
        printOX("overriding",child.print(1)==2);
        // hiding is trivial because static member depends on type
        printOX("hiding", parent.name().equals("Parent") );

        // originally different variable even same name
        printOX("parent's var still alive",child.getParentVar()==123);

        parent = (Parent)child;
        printOX("casting not change method", parent.print(1)==2);
        printOX("casting change hidden variable",parent.var==123);

        // explicit downcasting
        Child child2 = (Child)parent;

        // super
        T3 t3 = new T3();
        t3.test();

        // default constructor call super() implicitly
        Sub sub = new Sub();

        // super.super prohibited
        // final class -> cannot be inherited
        // final method -> cannot be override
        // final variable -> cannot be reassigned
    }

}
class Super {
    Super() {
        System.out.println("Super constructor");
    }
}
class Sub extends Super{
    Sub() {
        System.out.println("Sub constructor");
    }

}

class T1{ String s() { return "1";} }
class T2 extends T1 { String s() { return "2"; }}
class T3 extends T2 { String s() { return "3"; }
    void test() {
        Test.printOX("original s()", s().equals("3"));
        Test.printOX("super.s() invocate overriden", super.s().equals("2"));
        Test.printOX("upcasting cannot invocate overriden", ((T2)this).s().equals("3"));
    }
}

class Parent {
    int var = 123;
    static String name() {
        return "Parent";
    }
    int print(int i) {
        return i;
    }
}
class Child extends Parent {
    int var = 456;
    int getParentVar() {
        return super.var;
    }
    static String name() {
        return "Child";
    }
    @Override
    int print(int i) {
        return i+1;
    }
}

class OuterClass {
    InnerClass innerClass;
    private class InnerClass {
        InnerClass() {
            System.out.println("InnerClass is initiated");
        }
    }
    OuterClass() {
        System.out.println("OuterClass is initiated");
        this.innerClass = new InnerClass();
    }

}

class Car {
    int id;
    public Car(int id) {
        this.id = id;
    }
}
