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

public class FrequencyTable {

    private boolean normalSense; //Sentido de la codificación - Normal => true, Inversa => false
    private String text; //Texto de referencia para generar la tabla de frecuencia
    private Map<Character, Integer> frequency;//Contenedor donde se almacenarán los caracteres del texto con su respectiva cantidad de repeticiones
    private PriorityQueue<BinaryNode<Symbol>> nodes;//Contenedor que almacenará las instancias ordenadas de la clase Symbol contenidas en un BinaryNode

    public FrequencyTable(boolean sense, String text) {
        this.normalSense = sense;
        this.text = text;
        frequency = fillFrecuency();
        nodes = fillNodes();
    }

    private Map<Character, Integer> fillFrecuency() { //Crea el mapa frequency
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            if (m.containsKey(text.charAt(i))) {
                m.put(text.charAt(i), m.get(text.charAt(i)) + 1);
            } else {
                m.put(text.charAt(i), 1);
            }
        }
        return m;
    }

    private PriorityQueue<BinaryNode<Symbol>> fillNodes() { //Crea la cola nodes
        PriorityQueue<BinaryNode<Symbol>> p = new PriorityQueue<>((c1, c2) -> {
            if (normalSense == true) {
                if (c2.getContent().getAmount() != c1.getContent().getAmount()) {
                    return c1.getContent().getAmount() - c2.getContent().getAmount(); //Ordena los elementos con respecto a su cantidad de manera asccendente
                } else {
                    return c1.getContent().getSign().compareTo(c2.getContent().getSign());  //Si tienen igual cantidad de repeticiones, se considera el caracter
                }
            } else {
                if (c2.getContent().getAmount() != c1.getContent().getAmount()) {
                    return c2.getContent().getAmount() - c1.getContent().getAmount(); //Ordena los elementos con respecto a su cantidad de manera descendente
                } else {
                    return c1.getContent().getSign().compareTo(c2.getContent().getSign()); //Si tienen igual cantidad de repeticiones, se considera el caracter
                }
            }
        });
        frequency.keySet().forEach((c) -> { //Crea nodos con instancias de tipo Symbol a base del contenido del mapa frequency
            Symbol s = new Symbol(String.valueOf(c), frequency.get(c));
            p.offer(new BinaryNode(s));
        });
        return p;
    }

    public boolean isNormalSense() {
        return normalSense;
    }

    public void setSense(boolean sense) {
        this.normalSense = sense;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

        public Map<Character, Integer> getFrecuency() {
        return frequency;
    }

    public void setFrecuency(Map<Character, Integer> FRECUENCY) {
        this.frequency = FRECUENCY;
    }

    public PriorityQueue<BinaryNode<Symbol>> getNodes() {
        return nodes;
    }

    public void setNodes(PriorityQueue<BinaryNode<Symbol>> nodes) {
        this.nodes = nodes;
    }

}
