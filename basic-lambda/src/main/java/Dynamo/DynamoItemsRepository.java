package Dynamo;

import BasicLambda.ItemsRepository;
import BasicLambda.NewItem;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 * Created by adesmarais on 11/24/2016.
 */
public class DynamoItemsRepository implements ItemsRepository {

    private final DynamoDBMapper dynamoClient;

    public static DynamoItemsRepository Create() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();

        DynamoDBMapper mapper = new DynamoDBMapper(client);
        return new DynamoItemsRepository(mapper);
    }

    public DynamoItemsRepository(DynamoDBMapper dynamoClient) {
        if(dynamoClient == null) throw new IllegalArgumentException("dynamoClient cannot be null");

        this.dynamoClient = dynamoClient;
    }

    @Override
    public NewItem addItem(String object) {
        LaterItem laterItem = LaterItem.FromObjectString(object);
        dynamoClient.save(laterItem);
        return laterItem.ToNewItem();
    }
}
