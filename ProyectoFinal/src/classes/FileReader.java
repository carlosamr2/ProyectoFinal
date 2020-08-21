/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class FileReader {
    String texto;
    
    public FileReader(File myObj) {
        this.texto = this.read(myObj);
    }
    
    private String read(final File myObj) {  
        Map<Integer,String> a = new HashMap<>();
        
        
        String data = "";
        try {     
            final Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                final String line = myReader.nextLine();
                data += line;
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }
    

    
}
