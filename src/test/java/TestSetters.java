import org.example.Man;
import org.example.Person;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestSetters {

    public void testSetFirstLastName() {
        Man man = new Man();

        man.setFirstName("Petro");
        Assert.assertEquals(man.getFirstName(), "Petro", "Name is required");

        man.setLastName("Yevtushenko");
        Assert.assertEquals(man.getLastName(), "Yevtushenko", "LastName is required");

        man.setAge(20);
        Assert.assertFalse(man.isRetired(), "Is not retired");

        Man person2 = new Man();

        man.setPartner(person2);
        Assert.assertTrue(man.isPartner(),"Person have partner");


    }
}
