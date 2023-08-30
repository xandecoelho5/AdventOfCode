package src;

import java.util.HashSet;
import java.util.Set;

public class Node {
  public String name = "";
  public Node parent = null;
  public boolean isDirectory = true;
  public int size = 0;
  public Set<Node> children = new HashSet<>();

  public boolean containsChild(String name) {
    for (Node child : children) {
      if (child.name.equals(name)) {
        return true;
      }
    }
    return false;
  }

  public Node getChild(String name) {
    for (Node child : children) {
      if (child.name.equals(name)) {
        return child;
      }
    }
    return null;
  }

  public void addChild(Node child) {
    children.add(child);
  }
}


