package vertexcover;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author komabjn
 */
public class VertexCover {

    private static final String GRAPH_FILEPATH = ".\\graph.g";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph g = null;
        for (int i = 0; i < args.length - 1; i++) {
            // -l means load matrix from text file and put it into binary file to use later
            if (args[i].equals("-l")) {
                g = new AdjacencyMatrix();
                // load matrix from text file
                if (g.loadFromTextFile(args[i + 1]) != 0) {
                    System.out.println("failed to load matrix from text file at: " + args[i + 1]);
                    return;
                }
                // write matrix to binary file
                if (g.writeToBinaryFile(GRAPH_FILEPATH) != 0) {
                    System.out.println("failed to write matrix to binary file at: " + GRAPH_FILEPATH);
                    return;
                }
                return;
            }
        }
        g = new AdjacencyMatrix();

        if (g.loadFromBinaryFile(GRAPH_FILEPATH) != 0) {
            System.out.println("failed to load matrix from binary file at: " + GRAPH_FILEPATH);
            return;
        }

        boolean visitedVertices[] = new boolean[g.getNumberOfVertices()];
        LinkedList<Integer> adjacentVertices[] = (LinkedList<Integer>[]) new LinkedList[g.getNumberOfVertices()];
        Deque<Integer> lastVisitedVertices = new LinkedList<>();

        int lastStartingVertex = -1;
        int currentVertex = 0;
        boolean didFindNotVisitedVertexInCurrentVertex;

        Set<Integer> vertexCover = new HashSet<>();
        for (int i = 0; i < g.getNumberOfVertices(); i++) {
            vertexCover.add(i);
        }

        while (true) {
            //find starting point in graph (from vertices which werent yet visited)
            didFindNotVisitedVertexInCurrentVertex = false;
            for (int i = lastStartingVertex + 1; i < g.getNumberOfVertices(); i++) {
                if (!visitedVertices[i]) {
                    lastStartingVertex = i;
                    currentVertex = i;
                    visitedVertices[i] = true;
                    adjacentVertices[i] = g.getAdjacentVertices(i);
                    lastVisitedVertices.addFirst(i);
                    didFindNotVisitedVertexInCurrentVertex = true;
                    break;
                }
            }
            if (!didFindNotVisitedVertexInCurrentVertex) {
                break;
            }

            //perform DFS
            boolean didMoveToDeeperVertex = false;

            while (!lastVisitedVertices.isEmpty()) {
                currentVertex = lastVisitedVertices.getFirst();
                for (int i = 0; i < adjacentVertices[currentVertex].size(); i++) {
                    if (visitedVertices[adjacentVertices[currentVertex].getFirst()]) {//if vertex visited
                        adjacentVertices[currentVertex].removeFirst();
                    } else {
                        currentVertex = adjacentVertices[currentVertex].getFirst();
                        lastVisitedVertices.addFirst(currentVertex);
                        visitedVertices[currentVertex] = true;
                        adjacentVertices[currentVertex] = g.getAdjacentVertices(currentVertex);
                        didMoveToDeeperVertex = true;
                        break;
                    }
                }
                if (adjacentVertices[currentVertex].isEmpty()) { //no more vertices
                    if (didMoveToDeeperVertex) { //no vertices to visit and moved deeper, we are in the leaf
                        vertexCover.remove(currentVertex);
                    }
                    lastVisitedVertices.removeFirst();
                    adjacentVertices[currentVertex] = null;

                    didMoveToDeeperVertex = false;
                    //break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Found vertex cover: \n");
        for (Integer i : vertexCover) {
            sb.append(i.toString());
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
