package BasicLambda;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * Created by adesmarais on 11/25/2016.
 */
public class ContextLogger implements Logger {
    private final Context context;

    public ContextLogger(Context context) {
        this.context = context;
    }

    @Override
    public void log(String message) {
        context.getLogger().log(message + "\n");
    }
}
