package info.hccis.babyapp.utility;

import com.google.gson.Gson;
import info.hccis.babyapp.entity.VrBooker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * This method will call the rest web service and give back the json
 * @author bjmaclean
 * @since Nov 17, 2017
 * 
 * @REST Services are setup to provide json data and 
 * allow PUT, DELETE, GET and POST services as per Sprint 4 requirements
 * @by OB 
 * @Since 20200624
 */
public class UtilityRest {

    /**
     * This method will call the rest web service and give back the json
     *
     * @since 20171117
     * @author BJM
     *  
     * @Modified as per Sprint 4 requirements
     * @by OB
     * @Since 20200624
     */
    
    public static String getJsonFromRest(String urlString) {

        String content = "";
        try {
            //Setting up connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            
            //error troubleshooting
            if (conn.getResponseCode() == 204) {
                System.out.println("No data found");
            } else if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;

            while ((output = br.readLine()) != null) {
                content += output;
            }

            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * This method will access the CamperService to get all of the campers from
     * the campers web application service.
     *
     * 
     * @Modified to allow VR Arcade Booker to access attributes from web application
     * @by OB
     * @Since 20200624
     */
    public static void addUsingRest(String urlIn, VrBooker vrbooker) {
        //**********************************
        //Create a vr booker 
        //**********************************

        Gson gson = new Gson();
        String temp = "";

        //************************************
        //convert the camper to a json string
        //************************************
        temp = gson.toJson(vrbooker);

        //*********************************************
        // Access the rest web service
        // https://www.tutorialspoint.com/restful/restful_quick_guide.htm
        //*********************************************
        
        try {
            //Setting up connection
            URL url = new URL(urlIn);
            //System.out.println(urlIn);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            //System.out.println("json=" + temp);
            String input = temp;

            //Write the bytes of json to the output stream for the connection.
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            
            //error troubleshooting
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                System.out.println("Failed : HTTP error code : "
                        + conn.getResponseCode()
                        + conn.getResponseMessage());
            } else {

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                String content = "";
                while ((output = br.readLine()) != null) {
                    content += output;
                }
                VrBooker vrBookerReturned = gson.fromJson(content, VrBooker.class);
                System.out.println("Success : You have added a bookings (" + vrBookerReturned.getId() + ")\n");
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*
     * Allows VR Arcade Booker to access attributes from web application
     *
     * @by OB
     * @Since 20200624
     */

    public static void deleteUsingRest(String urlIn, int id) {
        //*********************************************
        // Access the rest web service
        //https://www.tutorialspoint.com/restful/restful_quick_guide.htm
        //*********************************************
        try {
            //Setting up connection
            URL url = new URL(urlIn + id);
            //System.out.println(urlIn);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            //Write the bytes of json to the output stream for the connection.
            OutputStream os = conn.getOutputStream();

            //error troubleshooting
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                System.out.println("Failed : HTTP error code : "
                        + conn.getResponseCode()
                        + conn.getResponseMessage());
            } else {

                System.out.println("Success : deleted booking (" + id + ")\n");
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    
    /**
     * This method will access the CamperService to get all of the campers from
     * the campers web application service.
     *
     * @since 20161115
     * @author BJM
     * 
     * @Modified to allow VR Arcade Booker to access attributes from web application
     * @by OB
     * @Since 20200624
     */
    public static void updateUsingRest(String urlIn, VrBooker vrbooker) {

        Gson gson = new Gson();
        String temp = "";

        //************************************
        //convert the camper to a json string
        //************************************
        temp = gson.toJson(vrbooker);

        //*********************************************
        // Access the rest web service
        //https://www.tutorialspoint.com/restful/restful_quick_guide.htm
        //*********************************************
        try {

            URL url = new URL(urlIn);
            //System.out.println(urlIn);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            //System.out.println("json=" + temp);
            String input = temp;

            //Write the bytes of json to the output stream for the connection.
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            
            //error troubleshooting
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                System.out.println("Failed : HTTP error code : "
                        + conn.getResponseCode()
                        + conn.getResponseMessage());
            } else {

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                String content = "";
                while ((output = br.readLine()) != null) {
                    content += output;
                }
                VrBooker vrBookerReturned = gson.fromJson(content, VrBooker.class);
                System.out.println("Success : You have updated a bookings (" + vrBookerReturned.getId() + ")\n");
                
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
