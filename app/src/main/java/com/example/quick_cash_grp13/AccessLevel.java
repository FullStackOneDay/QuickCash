package com.example.quick_cash_grp13;
/*
This AccessLevel class code is adapted from Assignment 3 CSCI 3130 source code from
https://git.cs.dal.ca/masud/csci3130-fall2021-a3
 */

public class AccessLevel {
    public static final AccessLevel regUserAccessLevel = new AccessLevel("regUser");
    public static final AccessLevel adminAccessLevel = new AccessLevel("admin");

    String accessLevel;

    public AccessLevel(String userAccessLevel) {
        this.accessLevel = userAccessLevel;

    }
}
