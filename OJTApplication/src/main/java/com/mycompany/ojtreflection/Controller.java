
package com.mycompany.ojtreflection;

import com.mycompany.ojtreflection.entity.OjtReflection;
import com.google.gson.Gson;
import com.mycompany.ojtreflection.bo.OjtReflectionBO;
import com.mycompany.ojtreflection.util.CisUtility;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A project to create an OJT reflection application
 *
 * @author OB
 * @since 20200519
 * 
 * 
 * Modified 20200524 by OB 
 * Created OjtReflection business object outside of controller class
 */

public class Controller {

    public static final String EXIT = "X";
    

    private static final String MENU
            = "-------------------------\n"
            + "- OJT Reflection\n"
            + "- A-Add Students\n"
            + "- S-Show reflections\n"
            + "- U-Update reflections\n"
            + "- X-eXit\n"
            + "-------------------------\n"
            + "Option-->";
    
    private static OjtReflectionBO ojtReflectionBO = new OjtReflectionBO();
  
    public static void main(String[] args) {
        
        ojtReflectionBO.loadFile();
        ojtReflectionBO.setupFile();
        //Add a loop below to continuously promput the user for their choice 
        //until they choose to exit.
        String option = "";

        CisUtility.display("OJT Reflections"
                + "\nToday is: " + CisUtility.getCurrentDate(null));
        do {
            option = CisUtility.getInputString(MENU);
            processMenuOption(option);
        } while (!option.equalsIgnoreCase(EXIT));

    }

    public static void processMenuOption(String option) {
        //Add a switch to process the option
        switch (option.toUpperCase()) {
            case "A":
                CisUtility.display("Add a Student");
                ojtReflectionBO.addAStudent();
                break;
            case "S":
                CisUtility.display("Here are the reflections");
                ojtReflectionBO.showAllReflections();
                break;
            case "U":
                CisUtility.display("Update Reflections");
                ojtReflectionBO.updateReflections();
                break;
            case "X":
                CisUtility.display("User picked x");
                break;
            default:
                CisUtility.display("Invalid entry");
        }
    }

  
}