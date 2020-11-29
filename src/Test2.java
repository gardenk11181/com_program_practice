import java.io.*;
import java.util.*;

public class Test2 {
    static void printOX(String msg, boolean condition) {
        String isRight = condition ? "O" : "X";
        System.out.println(msg + " : " + isRight);
    }
    public static void main(String[] args) {
        lecture7();
        lecture8();
        lecture9();
        lecture10();
    }

    static void lecture7() {
        System.out.println("-------------------------\nlecture7\n-------------------------");

        Object o = "hi";
        printOX("equal usage", o.equals("hi"));

        MyInt i1 = new MyInt(1);
        MyInt i2 = new MyInt(2);
        printOX("implement Comparable<T>", i1.compareTo(i2)==1);

        List<MyInt> myInts = new ArrayList<>();
        myInts.add(i1);
        myInts.add(i2);

        Collections.sort(myInts);
        printOX("Collection.sort use Comparable<T>",myInts.get(0)==i2);

        // abstract class : can contain implemented method
        // need to declare abstract on method
    }

    static void lecture8() {
        System.out.println("-------------------------\nlecture8\n-------------------------");

        // Polymorphism
        // signature : parameter type, name

        // Overloading : different parameter set
        Point p = new Point();
        p.move(1,1);
        printOX("move(int,int)",p.toString().equals("(1.0, 1.0)"));
        p.move(1f,1f);
        printOX("move(float,float)",p.toString().equals("(0.0, 0.0)"));

        printOX("ambiguous Overloading", Point.test("hi","hi")==1);

        // Hiding : static method or variables

        RealPoint rp = new RealPoint();
        Point2 p2 = rp;
        rp.move(1.7f,4.1f);
        p.move(-1,1);
        Point2.show(p2.x,p2.y);
        Point2.show(rp.x,rp.y);
        Point2.show(p2.getX(),p2.getY());
        Point2.show(rp.getX(),rp.getY());
    }

    static void lecture9() {
        System.out.println("-------------------------\nlecture9\n-------------------------");

        List<Integer> lists = new ArrayList<>();
        lists.add(0);
        lists.add(1);
        lists.add(2);
        lists.add(0,3);
        printOX("List.get(i)", lists.get(0)==3);
        lists.remove(0);
        printOX("remove by index", lists.get(0)==0);
        lists.remove(Integer.valueOf(1));
        printOX("remove by Object", lists.get(1)==2);

        // better performance using iterator
        ArrayList<String> al = new ArrayList<>();
        al.add("Hello"); al.add("World");
        al.add("Nice"); al.add("to");
        Iterator<String> iterator = al.iterator();

        // safe deletion
        while(iterator.hasNext()) {
            String str = iterator.next();
            if(str.equals("World")) iterator.remove();
        }
        iterator = al.iterator();
        printOX("Safe Deletion using Iterator", al.size()==3);

        // Comparator
        Collections.sort(al,new ReverseComparator());
        printOX("Use Comparator",al.get(0).equals("to"));

        // Queue
        Queue<String> queue = new LinkedList<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.peek();
        printOX("peek does not remove head",queue.peek()=="a");
        queue.poll();
        printOX("poll remove head", queue.peek()=="b");

        // Map
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"hi");
        printOX("Map.get", map.get(1).equals("hi"));
        printOX("Map.containsKey", map.containsKey(1));
        printOX("Map.containsValue", map.containsValue("hi"));
        printOX("Map.isEmpty", !map.isEmpty());
        printOX("Map.keySet", map.keySet().size()==1);
        printOX("Map.size", map.size()==1);

        // Collections
        // sort, search, shuffle, reverse, copy, min, max

    }

    static void lecture10() {
        System.out.println("-------------------------\nlecture10\n-------------------------");
        // file IO

        byte[] buf = { 35, 36 };
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buf);
        char ch = (char)(inputStream.read());
        printOX("ByteArrayInputStream success", ch=='#');
        inputStream.read();
        int end = inputStream.read();
        printOX("ByteArrayInputStream fail", end == -1);
        try {
            inputStream.close();
        } catch (IOException e){}

        try {
            FileInputStream fileInputStream = new FileInputStream("/Users/gardenmini/Library/Mobile Documents/com~apple~CloudDocs/2020 Fall/Computer Programming/final_practice/data/say_hello.txt");
            char c = (char)fileInputStream.read();
            printOX("FileInputStream read",c=='H');
            fileInputStream.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // ByteArrayOutputStream
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/gardenmini/Library/Mobile Documents/com~apple~CloudDocs/2020 Fall/Computer Programming/final_practice/data/file.txt");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(65);
            byteArrayOutputStream.writeTo(fileOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            String s = "\nWelcome!!!";
            bufferedOutputStream.write(s.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            fileOutputStream.close();

            printOX("ByteArrayOutputStream, BufferdOutPutStream success",true);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // FileWriter
        try {
            FileWriter fileWriter = new FileWriter("/Users/gardenmini/Library/Mobile Documents/com~apple~CloudDocs/2020 Fall/Computer Programming/final_practice/data/file_writer.txt");
            fileWriter.write("fileWriter first line");
            fileWriter.close();
            printOX("FileWriter success",true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // FileReader
        try {
            FileReader fileReader = new FileReader("/Users/gardenmini/Library/Mobile Documents/com~apple~CloudDocs/2020 Fall/Computer Programming/final_practice/data/file_writer.txt");
            int c = fileReader.read();
            while(c != -1) {
                c = fileReader.read();
            }
            printOX("FileReader success",true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Scanner
        try {
            Scanner input = new Scanner(new File("/Users/gardenmini/Library/Mobile Documents/com~apple~CloudDocs/2020 Fall/Computer Programming/final_practice/data/file_writer.txt"));
            while(input.hasNext()) {
                printOX("Scanner File Input success", input.nextLine().equals("fileWriter first line"));
            }
        } catch (Exception e) {}





    }
}
class ReverseComparator implements Comparator<String> {
    @Override
    public int compare(String s, String t1) {
        return t1.compareTo(s);
    }
}
class Point2 {
    int x = 0, y = 0;
    int color;
    void move(int dx, int dy) { x += dx; y += dy; }
    int getX() { return x; }
    int getY() { return y; }
    static void show(int x, int y) {
        System.out.println("(" + x + ", " + y + ")");
    }
    static void show(float x, float y) {
        System.out.println("(" + x + ", " + y + ")");
    }
}

class RealPoint extends Point2 {
    float x = 0.0f, y = 0.0f;
    void move(int dx, int dy) { move((float)dx, (float)dy); }
    void move(float dx, float dy) { x += dx; y += dy; }
    int getX() { return (int)Math.floor(x); }
    int getY() { return (int)Math.floor(y); }
}

class Point {
    float x=0,y=0;
    static int test(String s, String s1) {
        return 1;
    }
    static int test(String s, Object o) {
        return 2;
    }
    void move(int dx, int dy) { x += dx; y += dy;}
    void move(float dx, float dy) { x -= dx; y -= dy;}
    public String toString() {return "("+x+", "+y+")";}
}

class MyInt implements Comparable<MyInt> {
    int i;
    MyInt(int i) {
        this.i = i;
    }
    @Override
    public int compareTo(MyInt other) {
        return Integer.compare(other.i,i);
    }
}
