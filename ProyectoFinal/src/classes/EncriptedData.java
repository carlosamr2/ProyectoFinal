/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.HashMap;
import java.util.Map;
import tdas.BinaryNode;
import tdas.BinaryTree;

/**
 *
 * @author Bryan
 */
public class EncriptedData {

    FrequencyTable ft; //Tabla de frecuencia en la que se basará para crear los árboles de codificacion
    BinaryTree<Symbol> tree; //Árbol de Huffman que contiene los caracteres de la tabla de frecuencua
    Map<Character, String> mapEnc; //Mapa donde clave = caracter y valor = código
    Map<String, Character> mapDec; //Mapa donde clave = código y valor = caracter

    public EncriptedData(FrequencyTable ft) {
        this.ft = ft;
        tree = fillTree();
        mapEnc = fillMapEnc();
        mapDec = fillMapDec();
    }

    private BinaryTree<Symbol> fillTree() { //Llena el arbol binario con el contenido de la tabla de frecuecia 
        BinaryTree<Symbol> t = new BinaryTree<>();
        while (this.ft.getNodes().size() > 1) { //Reorganiza los nodos hasta que quede un solo nodo que contenga a los demás
            BinaryNode<Symbol> nLeft = this.ft.getNodes().poll();
            BinaryNode<Symbol> nRight = this.ft.getNodes().poll();
            String sym = nLeft.getContent().getSign() + "-" + nRight.getContent().getSign();//Crea un caracter basandose en los caracteres antereriores
            int frec = nLeft.getContent().getAmount() + nRight.getContent().getAmount();//Su frecuencia será la suma de ambas
            Symbol s = new Symbol(sym, frec); //Crea una instancia symbol con la informacion previa
            BinaryNode<Symbol> sNode = new BinaryNode(s, new BinaryTree(nLeft), new BinaryTree(nRight));
            this.ft.getNodes().offer(sNode);
        }
        t.setRoot(this.ft.getNodes().poll());//Setea el root con el nodo principal obtenido del queue nodes
        return t;
    }

    private void fillCodes(BinaryTree<Symbol> b, String co) {//Asigna un código a cada caracter dependiendo de su ubicación dentro del árbol
        if (!b.isLeaf()) {
            if (b.getLeft() != null) {
                co += "0"; //Se le aumentará el número cero cada vez que se baje a su hijo izquierdo
                fillCodes(b.getLeft(), co);
                co = co.substring(0, co.length() - 1);//Se eliminará el último dígito al salir del método recursivo
            }
            if (b.getRight() != null) {
                co += "1"; //Se le aumentará el número uno cada vez que se baje a su hijo derecho
                fillCodes(b.getRight(), co);
            }
        } else {
            b.getRoot().getContent().setCode(co);
        }
    }

    private Map<Character, String> fillMapEnc() { //Llena el mapa que será utilizado para codificar un String
        Map<Character, String> me = new HashMap<>();
        this.fillCodes(this.tree, "");
        tree.getLeaves().forEach((s) -> { //Llama al método getLeaves() que retorna un stack con las hojas del mapa
            me.put(s.getSign().charAt(0), s.getCode());
        });
        return me;
    }

    private Map<String, Character> fillMapDec() { //Llena el mapa que será usado para decodificar un String
        Map<String, Character> md = new HashMap<>();
        mapEnc.keySet().forEach((s) -> {
            md.put(mapEnc.get(s), s); //Se invierten las posiciones de las claves y los valores del mapa codificado
        });
        return md;
    }

    public FrequencyTable getFt() {
        return ft;
    }

    public void setFt(FrequencyTable ft) {
        this.ft = ft;
    }

    public Map<Character, String> getMapEnc() {
        return mapEnc;
    }

    public void setMapEnc(Map<Character, String> mapEnc) {
        this.mapEnc = mapEnc;
    }

    public Map<String, Character> getMapDec() {
        return mapDec;
    }

    public void setMapDec(Map<String, Character> mapDec) {
        this.mapDec = mapDec;
    }

}
