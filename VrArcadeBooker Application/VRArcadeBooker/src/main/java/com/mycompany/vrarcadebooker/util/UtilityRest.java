package com.mycompany.vrarcadebooker.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author bjmaclean
 * @since Nov 17, 2017 * Modified for Sprint 4 requirements
 * @Author OB
 * @Since 20200619
 * 
 * Modified - for spring 4
 * @Author OB 
 * @Since 20200620
 */

public class UtilityRest {

    /**
     * This method will call the rest web service and give back the json
     *
     * @since 20171117
     * @author BJM
     *
     * Modified for Sprint 4 requirements
     * @Author OB
     * @Since 20200619
     */

    public static String getJsonFromRest(String urlString) {

        String content = "";
        try {

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 204) {
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
}
