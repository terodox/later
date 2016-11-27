package BasicLambda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by adesmarais on 11/25/2016.
 */
public class MessageProcessor {
    private final Logger logger;
    private final ItemsRepository repository;
    private final ObjectMapper mapper;

    public MessageProcessor(Logger logger, ItemsRepository repository, ObjectMapper mapper) {
        if(logger == null) throw new RuntimeException("logger cannot be null");
        if(repository == null) throw new RuntimeException("repository cannot be null");
        if(mapper == null) throw new RuntimeException("mapper cannot be null");
        
        this.logger = logger;
        this.repository = repository;
        this.mapper = mapper;
    }

    public NewItem processItem(LaterDetails input) throws JsonProcessingException {
        try {
            logger.log(input.toString());
            String object = mapper.writeValueAsString(input);

            NewItem resultingItem = repository.addItem(new NewItem(object, null, 0));

            logger.log(resultingItem.getId().toString());
            logger.log("" + resultingItem.getTimeInTicks());
            return resultingItem;
        } catch (JsonProcessingException e) {
            logger.log("Failed to parse exception properly.");
            logger.logError(e);
            throw e;
        }
    }
}
