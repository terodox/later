package BasicLambda;

/**
 * Created by adesmarais on 11/25/2016.
 */
public class MessageProcessor {
    private final Logger logger;
    private final ItemsRepository repository;

    public MessageProcessor(Logger logger, ItemsRepository repository) {
        if(logger == null) throw new RuntimeException("logger cannot be null");
        if(repository == null) throw new RuntimeException("repository cannot be null");
        
        this.logger = logger;
        this.repository = repository;
    }

    public String processItem(InputObject input) {
        NewItem resultingItem = repository.addItem(new NewItem(input.getId(), null, 0));

        logger.log(resultingItem.getId().toString());
        logger.log("" + resultingItem.getTimeInTicks());
        return "Hello, " + input.getId() + "!";
    }
}
