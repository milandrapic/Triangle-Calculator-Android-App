package com.drapic.milan.trianglecalculator;

/**
 * Created by gdrapic on 2019-11-04.
 */

import java.util.List;

public class Edge {
    String[] name = new String[2];
    Vertex a;
    Vertex b;
    double distance;

    public Edge(double d ,char a, char b, List<Vertex> vers) {
        this.name[0] = Character.toString(a) + Character.toString(b);
        this.name[1] = Character.toString(b) + Character.toString(a);
        this.a = new Vertex(a);
        this.b = new Vertex(b);
        this.distance = d;
    }
    public Edge(double d ,Vertex from, Vertex to) {
        if(!from.containsEdge(this)) from.addEdge(this);
        if(!to.containsEdge(this)) to.addEdge(this);
        this.a = from;
        this.b = to;
        this.distance = d;
        this.name[0] = Character.toString(from.name) + Character.toString(to.name);
        this.name[1] = Character.toString(to.name) + Character.toString(from.name);
    }

    public boolean hasVertex(char a) {
        if(a == this.a.name || a == b.name) return true;
        return false;
    }

}