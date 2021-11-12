package com.example.quick_cash_grp13;

public class Admin extends User {

    public Admin(String userType, String firstName, String lastName, int age) {
        super(userType, firstName, lastName, age);
    }

    @Override
    public void setAccessLevel() {
        setAccessLevel(AccessLevel.adminAccessLevel);
    }

    @Override
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    @Override
    public boolean canModifyDatabase() {
        return true;
    }

}
