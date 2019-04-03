/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vertexcover;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author komabjn
 */
public class VertexCover {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph g = new AdjacencyMatrix();
        
        try {
            boolean result = g.loadFromFile(".\\res\\input.txt");
        } catch (IOException ex) {
            Logger.getLogger(VertexCover.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
