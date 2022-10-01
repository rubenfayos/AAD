/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t1_aev2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fayos
 */
public class Model {
    
    public String ReadFile(File f) throws IOException{
        
        String readed = "";
        
        if(f.exists()){
            
             
        
            try {
                
                BufferedReader bfReader = new BufferedReader(new FileReader(f));
                
                int r;
                
                while ((r = bfReader.read()) != -1){
                    
                    readed += (char) r;
                    
                }  
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        readed = readed.replaceAll( "[^\\x00-\\x7F]", "");
        
        return readed;
        
    }
    
    public int findWord(String readFile, String word){
        
        int totalWords=0;
        //Split para caracteres no numericos
        String words[] = readFile.split("\\W+");
        for (int i = 0; i < words.length; i++) {
        
            if (word.equals(words[i]))
                totalWords++;
        }
        
        return totalWords;
    }
    
    public String replaceWord(String readFile, String word, String newWord){
        
        String replacedText = "";
        
        //Para evitar que el salto de linea junte 2 caracteres
        ArrayList<String> finalWords = new ArrayList<>();
        String words[] = readFile.split(" ");
        
        //Iteramos el array en busca de saltos de linea para añadirlo de manera independiente al array
        for(String w : words){
            String lineWords[] = w.split("\\r\\n");
            if(lineWords.length > 1){
                finalWords.add(lineWords[0]);
                finalWords.add("\n\r");
                finalWords.add(lineWords[1]);
  
            }else
                finalWords.add(lineWords[0]);
        }

        
        for (int i = 0; i < finalWords.size(); i++) {
            
            //Comprobamos el salto de línea para evitar el espacio
            if(finalWords.get(i).equals("\n\r")){
                replacedText += "\n\r";
                continue;
            }
            
            
            if (word.equals(finalWords.get(i)))
                replacedText += newWord + " ";
            else
                replacedText += finalWords.get(i) + " ";
        }         
        
        return replacedText;
    }
    
    public void CreateFile(String text, String newFile) throws IOException{
        
        File f = new File(newFile);
        FileWriter fWriter = new FileWriter(f);
        
        fWriter.write(text);
        fWriter.flush();
        fWriter.close();
        
    }
    
        
}
