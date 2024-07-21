import org.example.Man;
import org.example.Person;
import org.example.Woman;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestGetters {
    public void testGetFirstLastName() {
        Woman woman = new Woman("Julia","Ivanenko",90);

        Assert.assertEquals(woman.getFirstName(), "Julia", "FirsName is nod valid");
        Assert.assertEquals(woman.getLastName(), "Ivanenko", "LasName is not valid");

        Woman  person2 = new Woman();

        woman.setPartner(person2);
        Person  retrievedPartner = woman.getPartner();
        Assert.assertEquals(person2,retrievedPartner,"Okey");

        int age = woman.getAge();
        Assert.assertEquals(age,90);

    }
}

