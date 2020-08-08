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
public class Symbol {
    private char sign;
    private int amount;

    public Symbol(char sign, int amount) {
        this.sign = sign;
        this.amount = amount;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Code{" + "caracter=" + sign + ", rep=" + amount + '}';
    }
    
    
    
    
}
