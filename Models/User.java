package com.NamTQ.Models;

import java.util.regex.Pattern;

public class User {
    private String name;
    private String customerID;
    public User(String name, String customerID) {
        this.name = name;
        this.customerID = customerID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        if(validCustomerId(customerID)){
            this.customerID = customerID;
        }
    }
//    xác định số CCCD có hợp lệ không
    public static boolean validCustomerId(String customerID){
        Pattern pattern = Pattern.compile("^\\d{12}$");
        return pattern.matcher(customerID).find();
    }
}
