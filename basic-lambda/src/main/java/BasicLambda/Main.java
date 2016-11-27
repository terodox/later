package BasicLambda;

import Dynamo.DynamoItemsRepository;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main implements RequestHandler<LaterDetails, String> {

    @Override
    public String handleRequest(LaterDetails input, Context context) {
        logRemainingTime(context);
        log(context, "Input: " + input.getMessage());

        ItemsRepository repo = DynamoItemsRepository.Create();
        Logger logger = new ContextLogger(context);
        ObjectMapper mapper = new ObjectMapper();
        MessageProcessor processor = new MessageProcessor(logger, repo, mapper);

        try {
            NewItem newItem = processor.processItem(input);
            return mapper.writeValueAsString(newItem);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void logRemainingTime(Context context) {
        context.getLogger().log(String.format("Remaining Time: %1$d\n", context.getRemainingTimeInMillis()));
    }

    private void log(Context context, String message) {
        context.getLogger().log(message + "\n");
    }
}

