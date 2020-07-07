package com.mycompany.vrarcadebooker.services;

import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.mycompany.vrarcadebooker.entity.VrBooker;
import org.springframework.stereotype.Service;

/**
 * Saves court bookings to a file
 * @author AmroM
 * 
 * @Modified to allow user to save Vr Bookings to JSon fil@e
 * @by O#B
 * @Since 20200624 
 */


@Service
public class VRSaveService {

   public static void saveVrBookingsToFile(ArrayList<VrBooker> vrbooker) throws IOException {

        String fileName = "C:\\Users\\Omars\\Desktop\\savedvrbookings.json";
        Path path = Paths.get(fileName);
        Files.createDirectories(path.getParent());
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {

            fw = new FileWriter(fileName, true);
            bw = new BufferedWriter(fw);
            Gson gson = new Gson();
            String stringJson = "";
            for (VrBooker current : vrbooker) {
                stringJson += gson.toJson(current) + System.lineSeparator();
            }
            fw.write(stringJson);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }

                if (fw != null) {
                    fw.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
    }
}