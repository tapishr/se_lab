package com.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


class DijkstraAlgorithm {

  private final List<Vertex> nodes;
  private final List<Edge> edges;
  private Set<Vertex> settledNodes;
  private Set<Vertex> unSettledNodes;
  private Map<Vertex, Vertex> predecessors;
  private Map<Vertex, Integer> distance;

  public DijkstraAlgorithm(Graph graph) {
    // Create a copy of the array so that we can operate on this array
    this.nodes = new ArrayList<Vertex>(graph.Nodes);
    this.edges = new ArrayList<Edge>(graph.Links);
  }

  public void execute(Vertex source) {
    settledNodes = new HashSet<Vertex>();
    unSettledNodes = new HashSet<Vertex>();
    distance = new HashMap<Vertex, Integer>();
    predecessors = new HashMap<Vertex, Vertex>();
    distance.put(source, 0);
    unSettledNodes.add(source);
    while (unSettledNodes.size() > 0) {
      Vertex node = getMinimum(unSettledNodes);
      settledNodes.add(node);
      unSettledNodes.remove(node);
      findMinimalDistances(node);
    }
  }

  private void findMinimalDistances(Vertex node) {
    List<Vertex> adjacentNodes = getNeighbors(node);
    for (Vertex target : adjacentNodes) {
      if (getShortestDistance(target) > getShortestDistance(node)
          + getDistance(node, target)) {
        distance.put(target, getShortestDistance(node)
            + getDistance(node, target));
        predecessors.put(target, node);
        unSettledNodes.add(target);
      }
    }

  }

  private int getDistance(Vertex node, Vertex target) {
    for (Edge edge : edges) {
      if (edge.src.equals(node)
          && edge.dst.equals(target)) {
        return edge.length;
      }
    }
    throw new RuntimeException("Should not happen");
  }

  private List<Vertex> getNeighbors(Vertex node) {
    List<Vertex> neighbors = new ArrayList<Vertex>();
    for (Edge edge : edges) {
      if (edge.src.equals(node)
          && !isSettled(edge.dst)) {
        neighbors.add(edge.dst);
      }
    }
    return neighbors;
  }

  private Vertex getMinimum(Set<Vertex> vertexes) {
    Vertex minimum = null;
    for (Vertex vertex : vertexes) {
      if (minimum == null) {
        minimum = vertex;
      } else {
        if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
          minimum = vertex;
        }
      }
    }
    return minimum;
  }

  private boolean isSettled(Vertex vertex) {
    return settledNodes.contains(vertex);
  }

  private int getShortestDistance(Vertex destination) {
    Integer d = distance.get(destination);
    if (d == null) {
      return Integer.MAX_VALUE;
    } else {
      return d;
    }
  }

  /*
   * This method returns the path from the source to the selected target and
   * NULL if no path exists
   */
  public LinkedList<String> getPath(Vertex target) {
    LinkedList<String> path = new LinkedList<String>();
    Vertex step = target;
    // Check if a path exists
    if (predecessors.get(step) == null) {
      return null;
    }
    path.add(step.id);
    while (predecessors.get(step) != null) {
      step = predecessors.get(step);
      path.add(step.id);
    }
    // Put it into the correct order
    Collections.reverse(path);
    return path;
  }

}
public class Graph {
    public ArrayList<Vertex> Nodes;
    public ArrayList<Edge> Links;
    
    public Graph(){
        Nodes = new ArrayList();
        Links = new ArrayList();
    }
    
    public Vertex addNode(String id, int x, int y){ Vertex v=new Vertex(id, x, y); Nodes.add(v); return v; }
    public void addLink(String src, String dst, int length){
        Vertex s = null, d = null;
        for(Vertex n : Nodes){
            if(n.id.equals(src)){ s = n; }
            if(n.id.equals(dst)){ d = n; }
        }
        Links.add(new Edge(s, d, length));
        Links.add(new Edge(d, s, length));
    }
    
    public void deleteNode(String id){
        for(Vertex n : Nodes){
            if(n.id.equals(id)){ Nodes.remove(n); break; }
        }
    }
    public void deleteLink(String src, String dst){
        Vertex s = null, d = null;
        for(Edge l : Links){
            if(l.src.equals(src) && l.dst.equals(dst)){ Links.remove(l); }
            if(l.src.equals(dst) && l.dst.equals(src)){ Links.remove(l); }
        }
    }
    
    public Vertex getVertexById(String id){
        for(Vertex v : Nodes){
            if(id.equals(v.id)){ return v; }
        }
        return null;
    }
    
    public LinkedList<String> getPath(String src, String dst){
        DijkstraAlgorithm dij = new DijkstraAlgorithm(this);
        dij.execute(getVertexById(src));
        return dij.getPath(getVertexById(dst));
    }
    
    public void enumerate(){
        LinkedList<String> path = getPath("IITJ", "JNVU");
        for(String v : path){
            System.out.print(v+" ");
        }
    }

  }


class Vertex{
    public String id;
    public int x, y;
    
    public Vertex(String id, int x, int y){ this.id = id; this.x = x; this.y = y; }
}

class Edge{
    public Vertex src, dst;
    public int length;
    
    public Edge(Vertex src, Vertex dst, int length){ this.src = src; this.dst = dst; this.length = length; }
}