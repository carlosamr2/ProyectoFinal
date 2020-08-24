/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Carlos
 */
public class FileWritter {
        String fileName;
        boolean exist;
    
    public FileWritter(final String fileName) {
        this.fileName = fileName;
        created(); 
    }
    
    private void created() {
        try {
            
            final File myObj = new File(this.fileName);
            this.exist = !myObj.createNewFile();
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
            else {
                System.out.println("File already exists.");
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void write(final String informacion) {
        try {
            final FileWriter myWriter = new FileWriter(this.fileName);
            myWriter.write(informacion);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean getExist() {
        return exist;
    }
    
    
    
}
