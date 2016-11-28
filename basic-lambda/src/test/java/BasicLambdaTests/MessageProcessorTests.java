package BasicLambdaTests;

import BasicLambda.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by adesmarais on 11/28/2016.
 */
public class MessageProcessorTests {
    private Logger logger;
    private ItemsRepository repo;
    private ObjectMapper mapper;
    private MessageProcessor sut;

    @Before
    public void BeforeTests() {
        logger = Mockito.mock(Logger.class);
        repo = Mockito.mock(ItemsRepository.class);
        mapper = Mockito.mock(ObjectMapper.class);
        sut = new MessageProcessor(logger, repo, mapper);
    }

    @Test(expected = Exception.class)
    public void Constructor_WithNullLogger_ThrowsException() {
        new MessageProcessor(null, repo, mapper);
    }

    @Test(expected = Exception.class)
    public void Constructor_WithNullItemsRepository_ThrowsException() {
        new MessageProcessor(logger, null, mapper);
    }

    @Test(expected = Exception.class)
    public void Constructor_WithNukkObjectMapper_ThrowsException() {
        new MessageProcessor(logger, repo, null);
    }

    @Test(expected = Exception.class)
    public void ProcessItem_WithNullItem_ThrowsException() throws JsonProcessingException {
        sut.processItem(null);
    }

    @Test
    public void ProcessItem_WithValidItem_StoresStringifiedObject() throws JsonProcessingException {
        LaterDetails input = new LaterDetails();
        NewItem successResult = new NewItem("something", UUID.randomUUID(), 1);

        when(mapper.writeValueAsString(input)).thenReturn("Flying and coding");
        when(repo.addItem(any(String.class))).thenReturn(successResult);

        sut.processItem(input);

        verify(repo, Mockito.times(1)).addItem(any(String.class));
    }

    @Test
    public void ProcessItem_WithValidItem_PassesAddItemResultUnmodified() throws JsonProcessingException {
        LaterDetails input = new LaterDetails();
        NewItem successResult = new NewItem("something", UUID.randomUUID(), 1);

        when(repo.addItem(any(String.class))).thenReturn(successResult);

        NewItem result = sut.processItem(input);

        Assert.assertEquals(successResult.getId(), result.getId());
        Assert.assertEquals(successResult.getTimeInTicks(), result.getTimeInTicks());
        Assert.assertEquals(successResult.getObject(), result.getObject());
    }

    @Test
    public void ProcessItem_WithInvalidLaterDetails_LogsJsonProcessException() {
        try {

            when(mapper.writeValueAsString(any(LaterDetails.class))).thenThrow(JsonProcessingException.class);

            sut.processItem(new LaterDetails());
        } catch(JsonProcessingException e) { }
        verify(logger, Mockito.times(1)).logError(any(JsonProcessingException.class));
    }
}
