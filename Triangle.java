package com.drapic.milan.trianglecalculator;

/**
 * Created by gdrapic on 2019-11-04.
 */

public class Triangle {

    Edge[] edges;
    Vertex[] vertices;
    int v_count;
    int e_count;

	//triangle constructor
    public Triangle() {
        this.edges = new Edge[3];
        this.vertices = new Vertex[3];
        this.v_count = 0;
        this.e_count = 0;
    }
	//triangle constructor with vertex values
    public Triangle(char a, char b, char c) {
        this.edges = new Edge[3];
        this.vertices = new Vertex[3];
	this.v_count = 0;
        this.e_count = 0;
	addVertex(a);
	addVertex(b);
	addVertex(c);
    }
	//adds a vertex to the triangle
    public void addVertex(char c) {
        char cap = Character.toUpperCase(c);
        if((!vertexExists(cap) ) && (v_count < 3)) {
            Vertex v = new Vertex(cap);
            v.name = cap;
            vertices[v_count++] = v;
        }

    }
	
	//adds edge to triangle
    public void addEdge(double distance, char from, char to) {
        from = Character.toUpperCase(from);
        to = Character.toUpperCase(to);
	    //if one vertex does not exist then return with no changes.
        if(!vertexExists(from) || !vertexExists(to)) return;
	    
	//if the edge does not exist, and there aren't already 3 edges in the triangle then add another edge
        if((findEdge(from, to) == null) && e_count < 3) {
            //creates edge object
            Edge e = new Edge(distance, returnVertex(from), returnVertex(to));

            //adds edge to array of edges. then increments the edge count
            edges[e_count++] = e;
        }
    }


	//returns address to vertex with specific name
    public Vertex returnVertex(char c) {
        c = Character.toUpperCase(c);
        for(Vertex v : vertices) {
            if(v.name == c) {
                return v;
            }
        }
        return null;
    }
	//boolean function returning whether the vertex exists or not
    private boolean vertexExists(char c) {
        c = Character.toUpperCase(c);

        for(int i = 0; i < v_count; i++) {
            if(vertices[i].name == c) {
                return true;
            }
        }
        return false;
    }
	//given the name of 2 different vertices, find the edge that connects them
    private Edge findEdge(char from, char to) {
        from = Character.toUpperCase(from);
        to = Character.toUpperCase(to);
        Vertex f,t;
        f = returnVertex(from);
        t = returnVertex(to);
        if(f == null || t == null) return null;

        String n = Character.toString(from) + Character.toString(to);

        for(Edge e : edges) {
            if(e == null) return null;
            if(e.name[0].equals(n) || e.name[1].equals(n)) {
                return e;
            }
        }
        return null;
    }
	
	//gives edges their proper label based on the vertices they join together
    public void setNames(){
        if(edges.length == 3){
            for(Edge e : edges){
                if(e.name[0].equals("AB") || e.name[1].equals("AB")){
                    e.name[0] = "c";
                }
                if(e.name[0].equals("AC") || e.name[1].equals("AC")){
                    e.name[0] = "b";
                }
                if(e.name[0].equals("BC") || e.name[1].equals("BC")){
                    e.name[0] = "a";
                }
            }
        }
    }
	// print values of all vertices, angles, and edges
    public String printVE() {
        setNames();
        String s = "";
        for(Vertex v : vertices) {
            s += v.name + " Angle: " + String.format("%.2f", v.angle) + "\n" + printEdgesofV(v);
        }
        return s;
    }

	//prints all the edges of a specific vertex
    public String printEdgesofV(Vertex v) {
        String s = "Edges:\n";
        for(Edge e : v.edgeList) {
            s += e.name[0] +" "+ String.format("%.2f", e.distance) + ";\n";
        }

        return s;
    }

}
