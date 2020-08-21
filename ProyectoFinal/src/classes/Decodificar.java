/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bryan
 */
public class Decodificar {

    public String codificar(String s) {

        return "";

    }

    public static void main(String[] args) {

        String texto = "mi mama me mima";

        Map<String, String> mapa = new HashMap<>();
        mapa.put("a", "101");
        mapa.put("m", "010");
        mapa.put("e", "011");
        mapa.put("i", "110");
        mapa.put(" ", "001");

        Map<String, String> mapa2 = new HashMap<>();
        System.out.println("Codificacion");
        for (String s : mapa.keySet()) {
            System.out.println(s + ": " + mapa.get(s));
            mapa2.put(mapa.get(s), s);
        }

        String cod = "";
        for (int i = 0; i < texto.length(); i++) {
            cod += mapa.get(String.valueOf(texto.charAt(i)));
        }

        System.out.println("");
        System.out.println("Decodificacion");
        for (String s : mapa2.keySet()) {
            System.out.println(s + ": " + mapa2.get(s));
        }

        System.out.println("");
        System.out.println("Texto codificado: " + cod);

        String cod2 = "";
        String lastCode = "";
        String codigo = "";
        
        for (int i = 0; i < cod.length(); i++) {

            codigo += cod.charAt(i);
            if (mapa2.keySet().contains(codigo)) {
                lastCode = mapa2.get(codigo);
                if(i == cod.length()-1)
                    cod2 += lastCode;
            } else {
                if (!lastCode.equals("")) {
                    cod2 += lastCode;
                    lastCode = "";
                    codigo = codigo.substring(codigo.length()-1);
                }
            }
            
        }
        System.out.println("");
        System.out.println("Codigo decodificado: "+cod2);
    }

}
