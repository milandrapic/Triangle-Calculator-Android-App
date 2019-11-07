package com.drapic.milan.trianglecalculator;

/**
 * Created by gdrapic on 2019-11-07.
 */

public class Tester {

    public static void main(String[] args) {
//		Triangle t = new Triangle();
//		char a = 'a';
//		char b = 'b';
//		char c = 'c';
//
//		t.addVertex(a);
//		t.addVertex(b);
//		t.addVertex(c);
//
//		t.addEdge(4, a, b);
//		t.addEdge(5, a, c);
//		t.addEdge(6, b, c);
//
//		t.printVE();

        Triangle t = TriangleFinder.calculate(5, 3, 4, 0, 0, 0);

        System.out.println(t.printVE());



    }

}
