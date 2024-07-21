import org.example.Woman;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class DataProviderTestWoman {

    @DataProvider(name = "womanData")
    public Object[][] womanData(){
        return new Object[][]{
                {"Olga", "Ivanova", 59, false},
                {"Anna", "Petrova", 60, true},
                {"Maria", "Sidorova", 61, true}
        };
    }

    @Test(dataProvider = "womanData")
    public void testIsRetired(String firstName, String lastName, int age, boolean expectedRetirementStatus) {
        Woman woman = new Woman(firstName, lastName, age);
        assertEquals(woman.isRetired(), expectedRetirementStatus);
    }

}
