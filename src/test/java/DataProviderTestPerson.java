import org.example.Man;
import org.example.Person;
import org.example.Woman;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

public class DataProviderTestPerson {

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
                {woman1, man1, true, "Collins", "Smith"},
                {woman2, man2, true, "Lopes", "Gosling"},
                {woman3, null, false, "Brown", null}
        };
    }

    @Test(dataProvider = "deregisterData")
    public void testDeregisterPartnership(Woman woman, Person partner, boolean shouldRevert, String expectedWomanLastName, String expectedPartnerLastName) {
        if (shouldRevert) {
            woman.setPreviousLastName(woman.getLastName());
            if (partner != null) {
                partner.setPreviousLastName(partner.getLastName());
            }
        }

        woman.deregisterPartnership();

        assertNull(woman.getPartner());
        if (partner != null) {
            assertNull(partner.getPartner());
        }

        assertEquals(woman.getLastName(), expectedWomanLastName);
        if (partner != null) {
            assertEquals(partner.getLastName(), expectedPartnerLastName);
        }
    }
}