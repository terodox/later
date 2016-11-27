package BasicLambdaTests;

import BasicLambda.LaterDetails;
import BasicLambda.Main;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by adesmarais on 11/23/2016.
 */
public class MainTests {

    @Test
    public void TestLoggingAtStart() {
        LambdaLogger lambdaLogger = Mockito.mock(LambdaLogger.class);
        Context mockContext = Mockito.mock(Context.class);
        when(mockContext.getLogger()).thenReturn(lambdaLogger);

        LaterDetails input = new LaterDetails();
        input.setMessage("The Best Id On The Planet.");
        Main sut = new Main();

        sut.handleRequest(input, mockContext);

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

        verify(lambdaLogger, atLeastOnce()).log(argument.capture());

        List<String> values = argument.getAllValues();

        int matches = 0;
        for (String oneValue : values) {
            if(oneValue.contains("Input")) {
                matches++;
            }
        }

        assertTrue("Must match 'Input' at least once",matches > 0);
    }
}
