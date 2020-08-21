/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import tdas.BinaryNode;
import tdas.BinaryTree;

/**
 *
 * @author Bryan
 */
public class EncriptedData {

    FrecuenceTable ft;
    BinaryTree<Symbol> tree;
    Map<String,String> map;

    public EncriptedData(FrecuenceTable ft) {
        this.ft = ft;
        tree = fillTree();
        map = fillMap();
    }

    private BinaryTree<Symbol> fillTree() { //Llena el arbol binario con el contenido de la tabla de frecuecia 
        BinaryTree<Symbol> t = new BinaryTree<>();
        PriorityQueue<BinaryNode<Symbol>> nodes = new PriorityQueue<>((n1, n2) -> {
            if (n1.getContent().getAmount() != n2.getContent().getAmount()) {
                return n1.getContent().getAmount() - n2.getContent().getAmount(); //Ordena los elementos con respecto a su cantidad de manera asccendente
            } else {
                return n1.getContent().getSign().compareTo(n2.getContent().getSign());  //Si tienen igual cantidad de repeticiones, se considera el caracter
            }
        });

        while (ft.getPriority().size() > 1) {
            Symbol left = this.ft.getPriority().poll(); //Elimina el primer elemento de la frecuencyTable y lo convierte en un nodo
            Symbol right = this.ft.getPriority().poll(); //Eliminia el segundo elemento de la fT y lo convierte en otro nodo
            String sym = left.getSign() + "-" + right.getSign();//Crea un caracter basandose en los caracteres antereriores
            int frec = left.getAmount() + right.getAmount();//Su frecuencia será la sua de ambas
            Symbol s = new Symbol(sym, frec); //Crea una oinstancia symbol con la informacion previa
            BinaryNode<Symbol> sNode = new BinaryNode(s, new BinaryTree(left), new BinaryTree(right)); //Crea un nuevo nodo que contiene a los caracteres previos como hijos
            nodes.offer(sNode);
        }

        if (!this.ft.getPriority().isEmpty()) {
            nodes.offer(new BinaryNode(this.ft.getPriority().poll()));
        }

        while (nodes.size() > 1) {
            BinaryNode<Symbol> nLeft = nodes.poll();
            BinaryNode<Symbol> nRight = nodes.poll();
            String sym = nLeft.getContent().getSign() + "-" + nRight.getContent().getSign();//Crea un caracter basandose en los caracteres antereriores
            int frec = nLeft.getContent().getAmount() + nRight.getContent().getAmount();//Su frecuencia será la suma de ambas
            Symbol s = new Symbol(sym, frec); //Crea una instancia symbol con la informacion previa
            BinaryNode<Symbol> sNode = new BinaryNode(s, new BinaryTree(nLeft), new BinaryTree(nRight));
            nodes.offer(sNode);
        }
  
        t.setRoot(nodes.poll());
        return t;
    }

    public static void printCode(BinaryTree<Symbol> tree, String s) {
        s += s;
        if (tree.isLeaf()) {
            System.out.println(tree. getRoot().getContent().getSign() + ":" + s);
            
            return;
        }
        printCode(tree.getLeft(), s + "0");
        printCode(tree.getRight(), s + "1");
    }
    
    public Map<String,String> fillMap(){
        Map<String,String> m = new HashMap<>();
        String code = "";
        while(!this.tree.getLeft().isLeaf()){
            code += "0";
        }
            
        
        return m;
    }

    public static void main(String[] args) {

        String text = "fsafbsajfsabksbbdskvbskjds";

        FrecuenceTable ft = new FrecuenceTable(true, text);

        EncriptedData ed = new EncriptedData(ft);

        printCode(ed.tree, "");

    }
}
