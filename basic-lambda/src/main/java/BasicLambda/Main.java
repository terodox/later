package BasicLambda;

import Dynamo.DynamoItemsRepository;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Main implements RequestHandler<InputObject, String> {

    @Override
    public String handleRequest(InputObject input, Context context) {
        logRemainingTime(context);
        log(context, "Input: " + input.getId());

        ItemsRepository repo = DynamoItemsRepository.Create();
        Logger logger = new ContextLogger(context);
        MessageProcessor processor = new MessageProcessor(logger, repo);

        return processor.processItem(input);
    }

    private void logRemainingTime(Context context) {
        context.getLogger().log(String.format("Remaining Time: %1$d\n", context.getRemainingTimeInMillis()));
    }

    private void log(Context context, String message) {
        context.getLogger().log(message + "\n");
    }
}

