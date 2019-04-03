package vertexcover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author komabjn
 */
public class AdjacencyMatrix implements Graph{

    public AdjacencyMatrix() {
        this.matrix = null;
        this.matrixSize = -1;
    }

    @Override
    public boolean checkIfVertexExists(int vertexNumber) {
        return (matrixSize > vertexNumber);
    }

    @Override
    public int getVertex() {
        return (matrixSize - 1);
    }

    @Override
    public List<Integer> getAdjacentVertices(int vertexNumber) {
        List<Integer> adjacentVertices = new LinkedList<>();
        for(int i = 0; i < matrixSize; i++){
            if(matrix[vertexNumber][i] > 0) adjacentVertices.add(i);
        }
        return adjacentVertices;
    }

    @Override
    public boolean loadFromFile(String filePath) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line = br.readLine();
            if(!line.startsWith("AM")) return false;
            matrixSize = Integer.parseInt(line.substring(3));
            matrix = new int[matrixSize][matrixSize];
            for(int i = 0; i < matrixSize; i++){
                line = br.readLine();
                String[] values = line.split(" ");
                for(int j = 0; j < matrixSize; j++){
                    matrix[i][j] = Integer.parseInt(values[j]);
                }
            }
        }
        return true;
    }
    
    private int[][] matrix;
    
    private int matrixSize;
    
}
