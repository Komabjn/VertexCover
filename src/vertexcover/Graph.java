package vertexcover;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author komabjn
 */
public interface Graph {
    
    /**
     * 
     * @param vertexNumber
     * @return 
     */
    public boolean checkIfVertexExists(int vertexNumber);
    
    /**
     * 
     * @return 
     */
    public int getVertex();
    
    /**
     *
     * @param vertexNumber
     * @return
     */
    public List<Integer> getAdjacentVertices(int vertexNumber);
    
    /**
     *
     * @param filePath
     * @throws IOException
     */
    public boolean loadFromFile(String filePath) throws IOException;
    
}
