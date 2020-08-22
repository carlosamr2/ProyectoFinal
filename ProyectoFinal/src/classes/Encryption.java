/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Bryan
 */
public class Encryption {

    private EncriptedData ed;

    public Encryption(EncriptedData ed) { //Recibe los mapas necesarios para la encriptación del string
        this.ed = ed;
    }

    public String encode(String s) { //Codifica un texto
        String text = "";
        for (int i = 0; i < s.length(); i++) {
            text += this.ed.getMapEnc().get(s.charAt(i));
        }
        return text;
    }

    public String decode(String s) { //Decodifica un texto
        String text = "";
        String lastCode = "";
        String code = "";
        for (int i = 0; i < s.length(); i++) {
            code += s.charAt(i);
            if (this.ed.getMapDec().keySet().contains(code)) {
                lastCode = String.valueOf(this.ed.getMapDec().get(code));
                if (i == s.length() - 1) {
                    text += lastCode;
                }
            } else {
                if (!lastCode.equals("")) {
                    text += lastCode;
                    lastCode = "";
                    code = code.substring(code.length() - 1);
                }
            }

        }
        return text;
    }


}
