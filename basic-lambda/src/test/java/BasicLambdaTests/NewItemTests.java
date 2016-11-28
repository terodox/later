package BasicLambdaTests;

import BasicLambda.NewItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by adesmarais on 11/28/2016.
 */
public class NewItemTests {
    @Test(expected = Exception.class)
    public void Constructor_WithEmptyObject_ThrowsException() {
        new NewItem("", UUID.randomUUID(), 1);
    }

    @Test(expected = Exception.class)
    public void Constructor_WithNullId_ThrowsException() {
        new NewItem("woohooo", null, 1);
    }

    @Test
    public void Create_WithValidObject_PopulatesIdAndTimeInTicks() {
        NewItem result = NewItem.Create("The flying, so slow.");

        Assert.assertTrue((System.currentTimeMillis() + 1000) > result.getTimeInTicks());
        Assert.assertNotNull(result.getId());
    }
}
