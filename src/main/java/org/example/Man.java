package org.example;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Man extends Person {

    public Man(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
    }

    public Man() {
        super();
    }

    public boolean isRetired() {
        return age >= 65;
    }

    @Override
    protected void updateLastName(Person partner) {
    }

}