package com.example.quick_cash_grp13;

public abstract class User {
    private String userType;
    private String firstName;
    private String lastName;
    private int age;
    protected AccessLevel accessLevel;

    public User(String userType, String firstName, String lastName, int age) {
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getUserType() { return userType; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }

    public void setUserType(String userType) { this.userType = userType; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setAge(int age) { this.age = age; }
    protected void setAccessLevel(AccessLevel accessLevel) { this.accessLevel = accessLevel; }

    public abstract void setAccessLevel();

    public abstract AccessLevel getAccessLevel();

    public abstract boolean canModifyDatabase();

    public String toString() {
        return firstName + " " + lastName + ": " + age;
    }
}
