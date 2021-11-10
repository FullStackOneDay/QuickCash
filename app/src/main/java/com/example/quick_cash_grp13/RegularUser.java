package com.example.quick_cash_grp13;

public class RegularUser extends User {

    public RegularUser(String userType, String firstName, String lastName, int age) {
        super(userType, firstName, lastName, age);
    }

    @Override
    public void setAccessLevel() {
        setAccessLevel(AccessLevel.regUserAccessLevel);
    }

    @Override
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    @Override
    public boolean canModifyDatabase() {
        return false;
    }

}
