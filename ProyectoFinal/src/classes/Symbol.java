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
public class Symbol{

    private String sign; //Caracter
    private int amount; //Cantidad de repeticiones del caracter dentro del String
    private String code; //Código que representa al caracter

    public Symbol(String sign, int amount) {
        this.sign = sign;
        this.amount = amount;
        this.code = "";
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return sign + ":     " + amount;
    }


}
