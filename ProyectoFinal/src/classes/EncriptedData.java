/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import tdas.BinaryTree;

/**
 *
 * @author Bryan
 */
public class EncriptedData {
    
    FrecuenceTable ft;
    BinaryTree<Character> tree;

    public EncriptedData(FrecuenceTable ft) {
        this.ft = ft;
        tree = fillTree();
    }
    
    private BinaryTree<Character> fillTree(){ //Llena el arbol binario con el contenido de la tabla de frecuecia 
        BinaryTree<Character> t = new BinaryTree<>();
        
        return t;
    }
    

    public static void main(String[] args) {

        String text = "fsafb sajfsabksbb dskvbs,kjds ";

        FrecuenceTable ft = new FrecuenceTable(false, text);

        while (!ft.getPriority().isEmpty()) { //Imprime los valores ordenados de PRIORITY
            System.out.println(ft.getPriority().remove());
        }
    }
}
