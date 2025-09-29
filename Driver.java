import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
public class Driver {
public static void main(String [] args) {
Polynomial p = new Polynomial();
System.out.println(p.evaluate(3));
double [] c1 = {6,5};
int[] e1 = {0,3};
Polynomial p1 = new Polynomial(c1,e1);
double [] c2 = {-2,-9};
int[] e2 = {1,4};
Polynomial p2 = new Polynomial(c2,e2);
Polynomial s = p1.add(p2);
Polynomial s1=p1.multiply(p2);
double[] c3={2,3};
int[] e3={1,3};
double[] c4={6,-5};
int[] e4={0,3};
Polynomial p4=new Polynomial(c4,e4);
Polynomial p3=new Polynomial(c3,e3);
Polynomial s2=p2.add(p3);
Polynomial s3=p1.multiply(p4);


s.saveToFile("Polynomial s");
s1.saveToFile("Polynomial s1");
s2.saveToFile("Polynomial s2");
s3.saveToFile("Polynomial s3");
File file=new File("Polynomial s.txt");
Polynomial p5=new Polynomial(file);
System.out.println("p5(0.1) = " + p5.evaluate(0.1));
if(s.hasRoot(1))
System.out.println("1 is a root of s");
else
System.out.println("1 is not a root of s");
}

}