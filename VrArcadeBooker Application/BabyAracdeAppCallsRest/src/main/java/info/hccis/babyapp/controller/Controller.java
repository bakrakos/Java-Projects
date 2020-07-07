package info.hccis.babyapp.controller;

import com.google.gson.Gson;
import info.hccis.babyapp.entity.VrBooker;
import info.hccis.babyapp.utility.UtilityRest;
import java.util.List;

import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Controller for Java application
 *
 * @author BJ
 * @since 20200611
 *
 * @Modified for Sprint 4 requirements
 * @by OB
 * @Since 20200624
 */
public class Controller {

    final public static String MENU = "\nMain Menu \n"
            + "A) Add\n"
            + "D) Delete\n"
            + "U) Update\n"
            + "V) View All\n"
            + "S) Show by Id\n"
            + "X) eXit";
    final static Scanner input = new Scanner(System.in);
    private static final String URL_STRING = "http://localhost:8081/vrbooker/api/VRArcadeBookerService/VrBooker/";

    private static Gson gson = new Gson();
    private static int id;

    /**
     * Switch Statement for menu to add/delete/update/view all and view one VR
     * Bookings
     *
     * @author BJ
     * @since 20200611
     *
     * @Modified for Sprint 4 requirements
     * @by OB
     * @Since 20200624
     */
    public static void main(String[] args) {
        boolean endProgram = false;
        do {
            System.out.println(MENU);
            String choice = input.nextLine();
            VrBooker vrbooker;
            switch (choice.toUpperCase()) {
                case "A":
                    UtilityRest.addUsingRest(URL_STRING, createVrBooker());
                    break;
                case "D":
                    System.out.println("Enter id to delete");
                    id = input.nextInt();
                    input.nextLine();  //burn
                    UtilityRest.deleteUsingRest(URL_STRING, id);
                    break;
                case "U":
                    vrbooker = createVrBooker();
                    String url = URL_STRING + "" + vrbooker.getId();
                    UtilityRest.updateUsingRest(url, vrbooker);
                    break;
                case "S":
                    gson = new Gson();
                    System.out.println("Enter id to show");
                    id = input.nextInt();
                    input.nextLine();  //burn  
                    VrBooker oneBooking = gson.fromJson((UtilityRest.getJsonFromRest(URL_STRING + id)), VrBooker.class);
                    System.out.println(oneBooking);
                    break;
                case "V":
                    String jsonReturned = UtilityRest.getJsonFromRest(URL_STRING);
                    //**************************************************************
                    //Based on the json string passed back, loop through each json
                    //object which is a json string in an array of json strings.
                    //*************************************************************
                    JSONArray jsonArray = new JSONArray(jsonReturned);
                    //**************************************************************
                    //For each json object in the array, show the first and last names
                    //**************************************************************
                    System.out.println("Here are the rows");
                    gson = new Gson();
                    for (int currentIndex = 0; currentIndex < jsonArray.length(); currentIndex++) {
                        VrBooker current = gson.fromJson(jsonArray.getJSONObject(currentIndex).toString(), VrBooker.class);
                        System.out.println(current.toString());
                    }
                    break;
                case "X":
                    endProgram = true;
                    break;
                default:
                    System.out.println("INVALID OPTION");
            }
        } while (!endProgram);
    }

    /**
     * Create a camper object by passing asking user for input.
     *
     * @return camper
     * @since 20171117
     * @author BJM
     *
     * @Modified to create/update Vr Booker based user input
     * @by OB
     * @since 20200618
     */
    public static VrBooker createVrBooker() {
        VrBooker newVrBooker = new VrBooker();

        System.out.println("Enter booking id: (0 for add)");
        newVrBooker.setId(Integer.parseInt(input.nextLine()));

        System.out.println("Enter Booker Name:");
        newVrBooker.setName1(input.next());
        input.nextLine();  //burn

        System.out.println("Enter Date of Booking (yyyy-MM-dd):");
        newVrBooker.setDateOfBooking(input.nextLine());

        System.out.println("Enter Time of Booking (xx:xx): ");
        newVrBooker.setTimeOfBooking(input.nextLine());

        System.out.println("Enter Length of Booking (Minutes): ");
        newVrBooker.setLengthOfBooking(input.nextInt());
        input.nextLine();  //burn

        return newVrBooker;
    }

}
