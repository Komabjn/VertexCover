package vertexcover;

import java.util.List;

/**
 *
 * @author komabjn
 */
public interface Graph {

    public static final int ADJACENCY_MATRIX = 1;

    /**
     *
     * @return
     */
    public int getNumberOfVertices();

    /**
     *
     * @param vertexNumber
     * @return
     */
    public List<Integer> getAdjacentVertices(int vertexNumber);

    /**
     *
     * @param filePath
     * @return
     */
    public int loadFromTextFile(String filePath);

    /**
     *
     * @param filePath
     * @return
     */
    public int writeToBinaryFile(String filePath);

    /**
     *
     * @param filePath
     * @return
     */
    public int loadFromBinaryFile(String filePath);

}
