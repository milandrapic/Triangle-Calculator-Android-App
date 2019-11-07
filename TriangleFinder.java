package com.drapic.milan.trianglecalculator;

/**
 * Created by gdrapic on 2019-11-04.
 */

public class TriangleFinder {
    static char a = 'a';
    static char b = 'b';
    static char c = 'c';



    public static Triangle calculate(double a, double b, double c, double a1, double a2, double a3){

        //SSS

        if(c>0 && b > 0 && a>0) return SSS(a,b,c, "abc");

        //ASA
        if(a1 > 0 && a2 > 0 && c > 0) return ASA(c,a1,a2,"cAB");
        if(a2 > 0 && a3 > 0 && a > 0) return ASA(a,a2,a3, "aBC");
        if(a1 > 0 && a3 > 0 && b > 0) return ASA(b,a1,a3, "bAC");

        //SAS
        if (c > 0 && b > 0 && a1 > 0) return SAS(b,c,a1, "bcA");
        if (c > 0 && a > 0 && a2 > 0) return SAS(a,c,a2, "acB");
        if (b > 0 && a > 0 && a3 > 0) return SAS(a,b,a3, "abC");

        //AAS
        if (a3 > 0 && a1 > 0 && c > 0) return AAS(a1,a3,c, "cAC");
        if (a3 > 0 && a2 > 0 && c > 0) return AAS(a2,a3,c, "cBC");
        if (a2 > 0 && a1 > 0 && a > 0) return AAS(a2,a1,a, "aAB");
        if (a3 > 0 && a1 > 0 && a > 0) return AAS(a3,a1,a, "aAC");
        if (a2 > 0 && a1 > 0 && b > 0) return AAS(a1,a2,b, "bAB");
        if (a2 > 0 && a3 > 0 && b > 0) return AAS(a3,a2,b, "bBC");


        //SSA
        if (c > 0 && b > 0 && a2 > 0) return SSA(c,b,a2, "bcB");
        if (c > 0 && b > 0 && a3 > 0) return SSA(b,c,a3, "bcC");
        if (c > 0 && a > 0 && a1 > 0) return SSA(c,a,a1, "acA");
        if (c > 0 && a > 0 && a3 > 0) return SSA(a,c,a3, "acC");
        if (a > 0 && b > 0 && a1 > 0) return SSA(b,a,a1, "abA");
        if (a > 0 && b > 0 && a2 > 0) return SSA(a,b,a2, "abB");


        return null;
    }

    private static char[] fromto(char v){

        if(v == 'A' || v == 'a'){
            char[] c =  {'B', 'C'};
            return c;
        }
        if(v == 'B' || v == 'b'){
            char[] c =  {'A', 'C'};
            return c;
        }
        if(v == 'C' || v == 'c'){
            char[] c =  {'A', 'B'};
            return c;
        }
        return null;
    }

    private static char fm(char x, char y){
        if((y - x)== 2) return 'B';
        else if((y - x) == 1){
            if(x == 'B') return 'A';
            if(x == 'A') return 'C';
        }
        return 0;
    }

    private static Triangle AAS(double a1, double a2, double e, String s) {
        Triangle t = new Triangle();
        t.addVertex(a);
        t.addVertex(b);
        t.addVertex(c);
        t.returnVertex(s.charAt(1)).angle = a1;
        t.returnVertex(s.charAt(2)).angle = a2;


        double a3 = findMissingAngle(a1, a2);


        t.returnVertex(fm(s.charAt(1),s.charAt(2))).angle = a3;



        double e2 = sinLawFindSide(e, a2, a1);
        double e3 = sinLawFindSide(e, a2, a3);

        char z0,z1,z2, x1,x2,x3;
        z0 = s.charAt(0);
        z1 = s.charAt(1);
        z2 = s.charAt(2);

        if(Character.toUpperCase(z0) == z1){
            x1 = z1;
            x2 = z2;
            x3 = fm(z1, z2);
            char[] y1 = fromto(x1);
            char[] y2 = fromto(x2);
            char[] y3 = fromto(x3);
            t.addEdge(e, y1[0], y1[1]);
            t.addEdge(e2, y2[0], y2[1]);
            t.addEdge(e3, y3[0], y3[1]);
            //if(z2 == 'C'){t.addEdge(e2,a,b); t.addEdge(e3,a,b);}
            //if(z2 == 'B'){t.addEdge(e2,a,c);}
        }
        else if(Character.toUpperCase(z0) == z2){
            x1 = z2;
            x2 = z1;
            x3 = fm(z1, z2);
            char[] y1 = fromto(x1);
            char[] y2 = fromto(x2);
            char[] y3 = fromto(x3);
            t.addEdge(e, y1[0], y1[1]);
            t.addEdge(e2, y2[0], y2[1]);
            t.addEdge(e3, y3[0], y3[1]);
            //if(z1 == 'A'){t.addEdge(e2,b,c);}
            //if(z1 == 'B'){t.addEdge(e2,a,c);}
        }





        return t;
    }



    private static Triangle ASA(double e1, double a1, double a2, String s) {
        Triangle t = new Triangle();
        t.addVertex(a);
        t.addVertex(b);
        t.addVertex(c);

        char ce1,ca1,ca2,ca3;
        char[] z1, z2,z3;
        ce1 = Character.toUpperCase(s.charAt(0));
        ca1 = s.charAt(1);
        ca2 = s.charAt(2);
        ca3 = fm(ca1,ca2);

        z1 = fromto(ce1);
        z2 = fromto(ca1);
        z3 = fromto(ca2);

        t.addEdge(e1, z1[0], z1[1]);

        double a3 = findMissingAngle(a1, a2);

        t.returnVertex(ca1).angle = a1;
        t.returnVertex(ca2).angle = a2;
        t.returnVertex(ca3).angle = a3;

        double e3 = sinLawFindSide(e1, a3, a1);
        double e2 = sinLawFindSide(e1, a3, a2);

        t.addEdge(e2, z2[0], z2[1]);
        t.addEdge(e3, z3[0], z3[1]);



        return t;
    }

    private static Triangle SSS(double e1, double e2, double e3, String s) {
        Triangle t = new Triangle();
        t.addVertex(a);
        t.addVertex(b);
        t.addVertex(c);
        t.addEdge(e1, b, c);
        t.addEdge(e2, a, c);
        t.addEdge(e3, a, b);
        t.returnVertex(c).angle = cosLawAngle(e1, e2, e3);
        t.returnVertex(b).angle = cosLawAngle(e1, e3, e2);
        t.returnVertex(a).angle = cosLawAngle(e3, e2, e1);


        return t;
    }

    private static Triangle SSA(double e1, double e2, double a2, String s) {
        Triangle t = new Triangle();
        t.addVertex(a);
        t.addVertex(b);
        t.addVertex(c);

        char c1,c2,c3;
        c2 = s.charAt(2);
        if(c2 != Character.toUpperCase(s.charAt(0))) c1 = Character.toUpperCase(s.charAt(0));
        else c1 = Character.toUpperCase(s.charAt(1));
        t.returnVertex(c2).angle = a2;

        c3 = fm(s.charAt(0), s.charAt(1));
        double a1 = sinLawFindAngle(e1, a2, e2);
        t.returnVertex(c1).angle = a1;

        double a3 = findMissingAngle(a2, a1);

        t.returnVertex(c3).angle = a3;

        double e3 = cosLaw(e1, e2, a3);

        char[] z1,z2,z3;
        z1 = fromto(c1);
        z2 = fromto(c2);
        z3 = fromto(c3);

        t.addEdge(e1, z1[0], z1[1]);
        t.addEdge(e2, z2[0], z2[1]);
        t.addEdge(e3, z3[0], z3[1]);


        return t;
    }

    private static Triangle SAS(double e1, double e2, double angle, String s) {
        Triangle t = new Triangle();

        t.addVertex(a);
        t.addVertex(b);
        t.addVertex(c);

        char s1,s2,s3,ang;
        s1 = s.charAt(0);
        s2 = s.charAt(1);
        s3 = s.charAt(2);

        char[] z1,z2,z3;
        z1 = fromto(s1);
        z2 = fromto(s2);
        z3 = fromto(s3);

        t.addEdge(e1, z1[0], z1[1]);
        t.addEdge(e2, z2[0], z2[1]);

        t.returnVertex(s3).angle = angle;

        double e3 = cosLaw(e1,e2,angle);
        t.addEdge(e3, z3[0], z3[1]);

        double ang2 = sinLawFindAngle(e3, angle, e2);
        t.returnVertex(s2).angle = ang2;

        double ang3 = findMissingAngle(angle, ang2);
        t.returnVertex(s1).angle = ang3;

        return t;
    }



    private static double cosLaw(double e1, double e2, double angle) {
        double x = Math.PI / 180;
        angle = x * angle;
        double e3Squared;
        e3Squared = (e1 * e1) + (e2*e2) - (2*e1*e2)*(Math.cos(angle));
        return Math.sqrt(e3Squared);
    }

    private static double cosLawAngle(double e1, double e2, double e3) {
        double cosC = ((e1*e1) + (e2*e2) - (e3*e3))/(2 * e1 * e2);
        double angC = Math.toDegrees(Math.acos(cosC));
        return angC;
    }


    private static double sinLawFindSide(double eA, double angA, double angB) {
        double aRad = Math.toRadians(angA);
        double bRad = Math.toRadians(angB);
        double eB = (eA * Math.sin(bRad))/Math.sin(aRad) ;
        return eB;
    }

    private static double sinLawFindAngle(double eA, double angA, double eB) {
        double aRad = Math.toRadians(angA);
        double sinAngB = (Math.sin(aRad) * eB) / eA;
        double angB = Math.asin(sinAngB);
        angB = Math.toDegrees(angB);

        return angB;
    }

    private static double findMissingAngle(double a, double b) {
        double c = 180 - a - b;
        return c;
    }

}