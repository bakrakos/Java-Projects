
package com.mycompany.ojtreflection.entity;

import com.mycompany.ojtreflection.util.CisUtility;

/**
 * An OjtReflection class to contain the attributes.
 * An object oriented solution is expected. 
 * @author OB
 * @since 20200519
 * 
 */

public class OjtReflection {
    private int studentId;

    private String name1, reflection;   

    public OjtReflection(){
        
    }
    
    public OjtReflection(int studentId, String name1, String reflection) {
        this.studentId = studentId;
        this.name1 = name1;
        this.reflection = reflection;
    }
    

    public void getInformation(){
        
        studentId = CisUtility.getInputInt("Student ID number:");
        name1 = CisUtility.getInputString("Name of student:");
        reflection = CisUtility.getInputString("Name of reflection:");
        
    }
    
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getReflection() {
        return reflection;
    }

    public void setReflection(String reflection) {
        this.reflection = reflection;
    }
    
    public String toString(){
        return "Student ID: "+ studentId + " Name: " +name1 + " Reflection: "+ reflection;
    }
    
}
