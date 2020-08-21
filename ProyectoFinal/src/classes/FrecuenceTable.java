/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FrecuenceTable {

    private boolean sense; //Sentido de la codificación - Normal => true, Inversa => false
    private String text; //Texto de referencia para generar la tabla de frecuencia
    private Map<String, Integer> frecuency;//Contenedor donde se almacenarán los caracteres del texto con su respectiva cantidad de repeticiones
    private PriorityQueue<Symbol> priority;//Contenedor que almacenará las instancias ordenadas de la clase Symbol

    public FrecuenceTable(boolean sense, String text) {
        this.sense = sense;
        this.text = text;
        frecuency = fillFrecuency();
        priority = fillPriority();
    }

    private Map<String, Integer> fillFrecuency() { //Llena el mapa frecuency
        Map<String, Integer> m = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            if (m.containsKey(String.valueOf(text.charAt(i)))) {
                m.put(String.valueOf(text.charAt(i)), m.get(String.valueOf(text.charAt(i))) + 1);
            } else {
                m.put(String.valueOf(text.charAt(i)), 1);
            }
        }
        return m;
    }

    private PriorityQueue<Symbol> fillPriority() { //Llena la cola priority
        PriorityQueue<Symbol> p = new PriorityQueue<>((c1, c2) -> {
            if (sense == true ) {
                if (c2.getAmount() != c1.getAmount()) {
                    return c1.getAmount() - c2.getAmount(); //Ordena los elementos con respecto a su cantidad de manera asccendente
                } else {
                    return c1.getSign().compareTo(c2.getSign());  //Si tienen igual cantidad de repeticiones, se considera el caracter
                }
            } else {
                if (c2.getAmount() != c1.getAmount()) {
                    return c2.getAmount() - c1.getAmount(); //Ordena los elementos con respecto a su cantidad de manera descendente
                } else {
                    return c1.getSign().compareTo(c2.getSign()); //Si tienen igual cantidad de repeticiones, se considera el caracter
                }
            }
        });
        frecuency.keySet().forEach((c) -> { //Crea instancias de tipo Symbol a base del contenido del mapa frecuency
            Symbol s = new Symbol(c, frecuency.get(c));
            p.add(s);
        });
        return p;
    }

    public boolean isSense() {
        return sense;
    }

    public void setSense(boolean sense) {
        this.sense = sense;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Integer> getFrecuency() {
        return frecuency;
    }

    public void setFrecuency(Map<String, Integer> FRECUENCY) {
        this.frecuency = FRECUENCY;
    }

    public PriorityQueue<Symbol> getPriority() {
        return priority;
    }

    public void setPriority(PriorityQueue<Symbol> PRIORITY) {
        this.priority = PRIORITY;
    }

    
}
