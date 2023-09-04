package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day7 {
  public static void main(String[] args) throws IOException {
    var lines = Files.readAllLines(Paths.get("src/resources/day7.txt"));
    solve("part1", lines);
    solve("part2", lines);
  }

  private static void solve(String part, List<String> commands) {
    var rootNode = new Node();
    rootNode.name = "/";
    rootNode.isDirectory = true;

    var currentPosition = rootNode;

    for (var command : commands) {
      if (command.charAt(0) == '$') {
        currentPosition = processCommand(command, rootNode, currentPosition);
      } else {
        processFile(command, currentPosition);
      }
    }
    var totalSize = updateDirectorySize(rootNode);

    if ("part1".equals(part)) {
      System.out.println(sumOfDirectorySizeLessThanThreshold(rootNode, 100000, 0));
    } else {
      final int totalSpace = 70000000;
      final int freeSpaceNeeded = 30000000;
      final int currentlyFreeSpace = totalSpace - totalSize;
      final int moreSpaceNeeded = freeSpaceNeeded - currentlyFreeSpace;
      System.out.println(findSmallestDirectoryGreaterThanSize(rootNode, null, moreSpaceNeeded).size);
    }
  }

  private static Node processCommand(String command, Node root, Node currentPosition) {
    Node newCurrentPosition = currentPosition;

    var splitCommand = command.split(" ");
    if ("cd".equals(splitCommand[1])) {
      String param = splitCommand[2];
      if ("..".equals(param)) {
        newCurrentPosition = currentPosition.parent;
      } else if ("/".equals(param)) {
        newCurrentPosition = root;
      } else {
        if (currentPosition.containsChild(param)) {
          newCurrentPosition = currentPosition.getChild(param);
        }
      }
    }

    return newCurrentPosition;
  }

  private static void processFile(String command, Node currentPosition) {
    var splitCommand = command.split(" ");
    if (!currentPosition.containsChild(splitCommand[1])) {
      var newNode = new Node();
      newNode.name = splitCommand[1];
      newNode.parent = currentPosition;
      currentPosition.addChild(newNode);

      if ("dir".equals(splitCommand[0])) {
        newNode.isDirectory = true;
      } else {
        newNode.isDirectory = false;
        newNode.size = Integer.parseInt(splitCommand[0]);
      }
    }
  }

  private static int updateDirectorySize(Node node) {
    if (!node.isDirectory) {
      return node.size;
    }

    var subSize = 0;
    for (var child : node.children) {
      subSize += updateDirectorySize(child);
    }
    node.size = subSize;
    return subSize;
  }

  private static int sumOfDirectorySizeLessThanThreshold(Node node, int threshold, int totalSize) {
    if (!node.isDirectory) {
      return totalSize;
    }

    for (var child : node.children) {
      totalSize = sumOfDirectorySizeLessThanThreshold(child, threshold, totalSize);
    }

    if (node.size <= threshold) {
      return node.size + totalSize;
    }

    return totalSize;
  }

  private static Node findSmallestDirectoryGreaterThanSize(Node node, Node candidateNode, int targetSize) {
    if (!node.isDirectory) {
      return candidateNode;
    }

    for (var child : node.children) {
      candidateNode = findSmallestDirectoryGreaterThanSize(child, candidateNode, targetSize);
    }

    if (node.size < targetSize) {
      return candidateNode;
    }

    if (candidateNode == null || node.size <= candidateNode.size) {
      return node;
    }

    return candidateNode;
  }
}

class Node {
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
