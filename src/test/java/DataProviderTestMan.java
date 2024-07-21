import org.example.Man;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class DataProviderTestMan {
    @DataProvider(name = "manData")
    public Object[][] manData() {
        return new Object[][]{
                {"Vasyl", "Fedorenko", 22, false},
                {"Ivan", "Fedorenkov", 78, true},
                {"Lev", "Fedor", 66, true}
        };
    }

    @Test(dataProvider = "manData")
    public void testIsRetired(String firstName, String lastName, int age, boolean expectedRetirementStatus) {
        Man man = new Man(firstName, lastName, age);
        assertEquals(man.isRetired(), expectedRetirementStatus);
    }
}
