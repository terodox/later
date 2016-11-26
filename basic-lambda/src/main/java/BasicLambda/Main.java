package BasicLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Main implements RequestHandler<InputObject, String> {

    @Override
    public String handleRequest(InputObject input, Context context) {
        logRemainingTime(context);
        log(context, "Input: " + input.getId());

        ItemsRepository repo = DynamoItemsRepository.Create();
        NewItem resultingItem = repo.AddItem(new NewItem(input.getId(), null, 0));

        log(context, resultingItem.getId().toString());
        log(context, "" + resultingItem.getTimeInTicks());
        return "Hello, " + input.getId() + "!";
    }

    private void logRemainingTime(Context context) {
        context.getLogger().log(String.format("Remaining Time: %1$d\n", context.getRemainingTimeInMillis()));
    }

    private void log(Context context, String message) {
        context.getLogger().log(message + "\n");
    }
}

