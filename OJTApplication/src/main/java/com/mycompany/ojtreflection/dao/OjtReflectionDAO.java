package com.mycompany.ojtreflection.dao;

import com.mycompany.ojtreflection.entity.OjtReflection;
import com.mycompany.ojtreflection.util.CisUtility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to grant select, insert, update, on ojt database
 *
 *
 * @author OB
 * @since 20200519
 */

public class OjtReflectionDAO {

    public void insert(OjtReflection ojtreflection) {
        //Establish Connection
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ojt",
                    "root",
                    "");

        } catch (SQLException ex) {
            Logger.getLogger(OjtReflectionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        //***************************************************
        // INSERT
        //***************************************************
        try {
            String theStatement = "INSERT INTO OjtReflection (studentId, name1, reflection) "
                    + "VALUES (?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(theStatement);
            //Setting placeholder values    
            stmt.setInt(1, ojtreflection.getStudentId());
            stmt.setString(2, ojtreflection.getName1());
            stmt.setString(3, ojtreflection.getReflection());

            //Execute the update.
            stmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("sql exception caught");
            sqle.printStackTrace();
        }

    }

    /**
     * Select all Reflections from the database
     *
     * @since 2020-05-21
     * @author BJM
     * 
     * Modified 20200524 to implement Assignment 2 requirements by OB
     */
    public ArrayList<OjtReflection> selectAll() {
        ArrayList<OjtReflection> ojtreflections = new ArrayList();

        try {
            //Establish Connection
            Connection conn = null;
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ojt",
                    "root",
                    "");

            Statement stmt = conn.createStatement();

            //***************************************************
            // Select using statement
            //***************************************************
           
            ResultSet rs = stmt.executeQuery("select * from OjtReflection");

            //Show all the reflections
            while (rs.next()) {

                int studentId = rs.getInt("studentId");
                String name1 = rs.getString("name1");
                String reflection = rs.getString("reflection");

                OjtReflection ojtReflection = new OjtReflection(studentId, name1, reflection);
                ojtreflections.add(ojtReflection);
                System.out.println("Student ID: " + rs.getInt("studentId") + " Name: "+ rs.getString("name1") + " Reflection: " + rs.getString("reflection"));

            }
            int numberOfReflections = ojtreflections.size();
            System.out.println("There are " + numberOfReflections + " reflections.");
        } catch (SQLException ex) {
            System.out.println("Error selecting OJT Reflections. (" + ex.getMessage() + ")");

        }
        return ojtreflections;

    }
/**
 * Update Reflections from the database
 *
 * @since 2020-05-24
 * @author OB
 * 
 */
    public void RowUpdate(OjtReflection ojtreflection) {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ojt",
                    "root",
                    "");

        } catch (SQLException ex) {
            Logger.getLogger(OjtReflectionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //***************************************************
            // Update 
            //***************************************************

            String theStatement = "UPDATE ojtreflection SET studentId=?, name1=?, reflection=? WHERE studentId=?";
            //Establish Connection
            PreparedStatement preparedStatement = conn.prepareStatement(theStatement);
            //Setting placeholder values    
            preparedStatement.setInt(1, ojtreflection.getStudentId());
            preparedStatement.setString(2, ojtreflection.getName1());
            preparedStatement.setString(3, ojtreflection.getReflection());
            preparedStatement.setInt(4, ojtreflection.getStudentId());

            //Execute the equare or update.
            preparedStatement.executeUpdate();
        } 
            // print SQL exception information
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
