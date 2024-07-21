package org.example;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

public abstract class Person {
    private String firstName;
    private String lastName;
    public int age;
    private Person partner;

    public Person() {

    }

    public boolean isRevertToPreviousLastName() {
        return revertToPreviousLastName;
    }

    public void setRevertToPreviousLastName(boolean revertToPreviousLastName) {
        this.revertToPreviousLastName = revertToPreviousLastName;
    }

    private boolean revertToPreviousLastName;

    public String getPreviousLastName() {
        return previousLastName;
    }

    public void setPreviousLastName(String previousLastName) {
        this.previousLastName = previousLastName;
    }

    private String previousLastName;


    // Constructor
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.previousLastName = this.lastName;
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isPartner() {
        return partner != null;
    }

    public Person getPartner() {
        return partner;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }


    protected abstract void updateLastName(Person partner);

    public void registerPartnership(Person partner) {
        this.setPartner(partner);
        partner.setPartner(this);
        this.updateLastName(partner);
        partner.updateLastName(this);
    }

    public void deregisterPartnership() {
        if (this.partner == null) {
            System.out.println("No Partnership to deregister");
            return;
        }
        Person formerPartner = this.partner;
        this.setPartner(null);
        formerPartner.setPartner(null);
        ;
        if (isRevertToPreviousLastName()) {
            this.setLastName(this.previousLastName);
            formerPartner.setLastName(formerPartner.getPreviousLastName());
        }
    }

    @DataProvider(name = "deregisterData")
    public Object[][] deregisterData() {
        Woman woman1 = new Woman("Lili", "Collins", 35);
        Man man1 = new Man("John", "Smith", 40);
        woman1.setPartner(man1);
        man1.setPartner(woman1);

        Woman woman2 = new Woman("Jeniffer", "Lopes", 28);
        Man man2 = new Man("Rick", "Gosling", 30);
        woman2.setPartner(man2);
        man2.setPartner(woman2);

        Woman woman3 = new Woman("Eva", "Brown", 45);
        woman3.setPartner(null);

        return new Object[][]{
                {woman1, man1, true, "Doe", "Smith"},
                {woman2, man2, true, "Brown", "White"},
                {woman3, null, false, "Black", null}
        };
    }

    @Test(dataProvider = "deregisterData")
    public void testDeregisterPartnership(Woman woman, Person partner, boolean shouldRevert, String expectedWomanLastName, String expectedPartnerLastName) {
        if (shouldRevert) {
            woman.setPreviousLastName(woman.getLastName());
            if (partner != null) {
                partner.setPreviousLastName(((Man) partner).getLastName());
            }
        }

        woman.deregisterPartnership();

        assertNull(woman.getPartner());
        if (partner != null) {
            assertNull(partner.getPartner());
        }

        assertEquals(woman.getLastName(), expectedWomanLastName);
        if (partner != null) {
            assertEquals(((Man) partner).getLastName(), expectedPartnerLastName);
        }
    }
}
