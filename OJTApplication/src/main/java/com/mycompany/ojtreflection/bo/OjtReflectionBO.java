package com.mycompany.ojtreflection.bo;

import com.google.gson.Gson;
import com.mycompany.ojtreflection.Controller;
import com.mycompany.ojtreflection.Controller;
import com.mycompany.ojtreflection.dao.OjtReflectionDAO;
import com.mycompany.ojtreflection.entity.OjtReflection;
import com.mycompany.ojtreflection.util.CisUtility;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Business Object to reflect Assignment 2 Requirements
 *
 * @author OB
 * @since 20200524
 */
public class OjtReflectionBO {
    
    //File Path details  (c:\cis2232\reflection.json). 
    public static final String PATH = "c:\\cis2232\\";
    public static final String FILE_NAME = "reflection.json";

    
    //entries are added to an ArrayList
    private static ArrayList<OjtReflection> ojtreflections = new ArrayList();
    
  
    
/**
 * When the user selects ‘A’ (for Add) - allow the user to enter 
 * the student id, name, and reflection text.  No validation is 
 * required.  
 *
 * @author OB
 * @since 20200524
 * 
 * Modified 20200524 to implement Assignment 2 requirements by OB
 * 
 */
    public static void addAStudent() {
  
//        BufferedWriter writer = null;
//        try {
//            //Creating OjtReflection object
//            OjtReflection ojtreflection = new OjtReflection();
//            ojtreflection.getInformation();
//
//
//            //Write to file
//            writer = new BufferedWriter(new FileWriter(PATH + FILE_NAME, true));
//
//            //Use GSON to get a json string encoding for a Reflection
//            Gson gson = new Gson();
//            String ojtJson = gson.toJson(ojtreflection);
//
//            //Using a line per reflection
//            writer.write(ojtJson + System.lineSeparator());
//            writer.close();
//
//        } catch (IOException ex) {
//            CisUtility.display("Error with file access");
//        } finally {
//            try {
//                writer.close();
//            } catch (IOException ex) {
//                CisUtility.display("Error closing file");
//            }
//        }
                        
            OjtReflection ojtreflection = new OjtReflection();
            ojtreflection.getInformation();
            
            //Creates DAO object that passes ojtReflection 
            OjtReflectionDAO ojtReflectionDao = new OjtReflectionDAO();
            ojtReflectionDao.insert(ojtreflection);
    }
    
    
    
 /**
 * Method used to write updated reflections
 *
 * @author OB
 * @since 20200519
 * Modified 20200524 to implement Assignment 2 requirements by OB
 * 
 */
    
    public static void writeUpdatedReflections() {

        OjtReflection ojtreflection = new OjtReflection();
        ojtreflection.getInformation();

        //Creates DAO object that passes ojtReflection to update
        OjtReflectionDAO ojtReflectionDao = new OjtReflectionDAO();
        ojtReflectionDao.RowUpdate(ojtreflection);
        
//        //Create a new writer which will override the file containing the reflection.
//        BufferedWriter writer = null;H
//        try {
//            writer = new BufferedWriter(new FileWriter(PATH + FILE_NAME, false));
//        } catch (IOException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        for (OjtReflection current : ojtreflections) {
//            try {
//                //Use GSON to get a json string encoding for a reflection
//                Gson gson = new Gson();
//                String ojtreflections = gson.toJson(current);
//
//                writer.write(ojtreflections + System.lineSeparator());
//            } catch (IOException ex) {
//                CisUtility.display("Error with file access");
//            }
//
//        }
//        try {
//            writer.close();
//        } catch (IOException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
   
    /**
 * When the program starts, if the file created above exists it 
 * should be used to load the ArrayList<OjtReflection>.  
 * This will eliminate the need for the program to prompt for the 
 * attributes each time the program is run.  
 * @author OB
 * @since 20200519
 * 
 * Modified 20200524 to implement Assignment 2 requirements by OB
 */
    public static void loadFile(){


        ojtreflections.clear();
        OjtReflectionDAO ojtReflectionDao = new OjtReflectionDAO();
        ojtReflectionDao.selectAll();
        
//        try {
//   
//            Path ojtPath = Paths.get(PATH + FILE_NAME);
//            List<String> lines = Files.readAllLines(ojtPath);
//            Gson gson = new Gson();
////
//            for (String current : lines) {
//                OjtReflection ojtreflection = gson.fromJson(current, OjtReflection.class);
//                    //Add to arrayList
//                    ojtreflections.add(ojtreflection);
//            }
//        } catch (IOException ex) {
//            CisUtility.display("Exception reading reflection from file");
//        }

    }
    
    public static void setupFile(){
        File myFile;
        try {
            Path path = Paths.get(PATH);
            try {
                Files.createDirectories(path);
            } catch (IOException ex) {
                CisUtility.display("Error creating directory");
            }

            myFile = new File(PATH + FILE_NAME);
            if (myFile.createNewFile()) {                
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException ex) {
            CisUtility.display("Error creating new file");
        }
    }
    
     /**
 * Show all of the reflections details to the console.  
 * As well at the top (before the list of reflections), 
 * it should show the total number of reflections 
 * (a count of all of the reflections)
 * @author OB
 * @since 20200519
 * 
 * Modified 20200524 to implement Assignment 2 requirements by OB
 */   
    
    public static void showAllReflections() {

        ojtreflections.clear();
        loadFile();
        
        
//Counts the number of reflections in the arraylist
// int numberOfReflections = 0;        
     
         //Shows all reflections
//        for (OjtReflection current : ojtreflections) {
//            //int numberOfReflections = ojtreflections.size();
//            System.out.println("The Number of reflections are " + "numberOfReflections");
//            CisUtility.display(current.toString());
//        }


    }

    /**
 * Allow the user to update the reflection for a student in the 
 * ArrayList created above.  
 * After updating, show all the reflections again to the user.
 * @author OB
 * @since 20200519
 * 
 * Modified 20200524 to implement Assignment 2 requirements by OB
 * 
 */
     public static void updateReflections() {
        //Loads Information
        loadFile();

        OjtReflection ojtreflection = new OjtReflection();
        OjtReflectionDAO ojtReflectionDao = new OjtReflectionDAO();

        //Calling Row Update to assign new values
        ojtReflectionDao.RowUpdate(ojtreflection);


//Identify which one to update...
//        for (OjtReflection current : ojtreflections) {
//            if (current.getStudentId()==(id)) {
//                 CisUtility.display("Student to update:");
//                 CisUtility.display(current.toString());
//
////found it!
//                 CisUtility.display("Student to update:");
//                 CisUtility.display(current.toString());
//                 CisUtility.display("Please provide new details");
//                 //asks for new information
//                 current.getInformation();
//                 break;
//
//             }
//
//        }

        //Write updated reflections 
        writeUpdatedReflections();
        System.out.println("Here are the updated Reflections");
        //show updated reflections
        showAllReflections();

    }
}

        
        
   