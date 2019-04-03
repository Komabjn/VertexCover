package vertexcover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author komabjn
 */
public class AdjacencyMatrix implements Graph {

    public AdjacencyMatrix() {
        this.matrix = null;
        this.matrixSize = -1;
    }

    @Override
    public int getNumberOfVertices() {
        return matrixSize;
    }

    @Override
    public List<Integer> getAdjacentVertices(int vertexNumber) {
        List<Integer> adjacentVertices = new LinkedList<>();
        for (int i = 0; i < matrixSize; i++) {
            if (matrix[vertexNumber][i] > 0) {
                adjacentVertices.add(i);
            }
        }
        return adjacentVertices;
    }

    @Override
    public int loadFromTextFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (!line.startsWith("AM")) {
                return -1;
            }
            matrixSize = Integer.parseInt(line.substring(3));
            matrix = new int[matrixSize][matrixSize];
            for (int i = 0; i < matrixSize; i++) {
                line = br.readLine();
                String[] values = line.split(" ");
                for (int j = 0; j < matrixSize; j++) {
                    matrix[i][j] = Integer.parseInt(values[j]);
                }
            }
        } catch (IOException ex) {
            return -1;
        }
        return 0;
    }

    @Override
    public int loadFromBinaryFile(String fileName) {
        RandomAccessFile in = null;
        try {
            in = new RandomAccessFile(fileName, "rw");
            FileChannel file = in.getChannel();
            ByteBuffer buf = file.map(FileChannel.MapMode.READ_WRITE, 0, 4 * matrixSize * matrixSize + 8);
            if(buf.getInt() != Graph.ADJACENCY_MATRIX) return -1;
            matrixSize = buf.getInt();
            matrix = new int[matrixSize][matrixSize];
            for (int[] i : matrix) {
                for (int j : i) {
                    j = buf.getInt();
                }
            }
            file.close();
        } catch (IOException ex) {
            return -1;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                //ignore
            }
        }
        return 0;
    }

    @Override
    public int writeToBinaryFile(String fileName) {
        RandomAccessFile out = null;
        try {
            out = new RandomAccessFile(fileName, "rw");
            FileChannel file = out.getChannel();
            ByteBuffer buf = file.map(FileChannel.MapMode.READ_WRITE, 0, 4 * matrixSize * matrixSize + 8);
            buf.putInt(Graph.ADJACENCY_MATRIX);
            buf.putInt(matrixSize);
            for (int[] i : matrix) {
                for (int j : i) {
                    buf.putInt(j);
                }
            }
            file.close();
        } catch (IOException ex) {
            return -1;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                //ignore
            }
        }
        return 0;
    }

    private int[][] matrix;

    private int matrixSize;

}
