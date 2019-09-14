package com.example.foodhut.Common;

import com.example.foodhut.Model.User;

public class Common {
    public static User currentUser;

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";

    public static String convertCodeToStatus(String code)
    {
        if(code.equals("0"))
            return "Placed";
        else if(code.equals("1"))
            return "On My Way";
        else
            return "Shipped";
    }
}
