package com.drapic.milan.trianglecalculator;

/**
 * Created by gdrapic on 2019-11-04.
 */

public class Vertex {
    char name;
    Edge[] edgeList;
    int e;
    double angle;

    public Vertex(char a) {
        this.name = a;
        e = 0;
        this.edgeList = new Edge[2];
    }

    public void addEdge(Edge edge) {
        if (e < 2) {
            edgeList[e] = edge;
            e++;
        }
    }

    public void replaceEdge(Edge edge, String ab) {
        for (int i = 0; i < e; i++) {
            if ((ab.equals(edgeList[i].name[0])) || (ab.equals(edgeList[i].name[1]))) {
                edgeList[i] = edge;
            }
        }
    }

    public boolean containsEdge(Edge ed) {
        for (int i = 0; i < e; i++) {
            if (edgeList[i].equals(ed)) {
                return true;
            }
        }
        return false;
    }
}