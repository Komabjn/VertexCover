package vertexcover;

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
        //TODO logic of finding vertex cover
    }
}
