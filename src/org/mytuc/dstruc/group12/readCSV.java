package org.mytuc.dstruc.group12;
/*
 * How to use:
 * 1. Create new readCSV Object
 * 		readCSV nodes = new readCSV();
 * 2. Create a 2-D-StringArray and call the function "readData" with the file of your Choice
 * 		String[][] listNodes = nodes.readData("arces.csv");
 * 3. Access the Elements with the indexes (IMPORTANT: The header line is removed! (ID, modifier) or (from, to, distance) )
 * 		listNodes[8][1] will give you the "23" which is in line 10 and column 2 of the "arces.csv" file
 * 4. If you want the Element as an Integer you have to convert it
 * 		Integer.parseInt(listNodes[8][1])
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class readCSV {

	private String line;
    private ArrayList<String[]> list = new ArrayList<String[]>();
    private String[] split = null;
       
   
    public String[][] readData(String filename) {
        try {
                BufferedReader data = new BufferedReader(new FileReader(filename));
                
                while ((line = data.readLine()) != null) {
                	
                	if(line.trim().length() == 0) line=data.readLine();
                    split = line.split(";");
                    
                    list.add(split);
                    
                }
                data.close();
                
        } catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        } catch (IOException e) {
            System.out.println("Input/Output Error");
        }
        
        list.remove(0); 														//löscht Tabellenkopf
		
		
		String[][] array = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			String[] row = list.get(i);
		    array[i] = row;
		}
		
		for (int i = 0; i < array.length; i++)
		{
			for (int j = 0; j < array[i].length; j++)
			{
				array[i][j] = array[i][j].trim();
				array[i][j] = array[i][j].replaceAll("\0", "");
			}
		}
		
		return array;
		
    }

}