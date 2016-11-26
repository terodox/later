package BasicLambda;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.UUID;

/**
 * Created by adesmarais on 11/24/2016.
 */
@DynamoDBTable(tableName = "items")
public class LaterItem {
    @DynamoDBHashKey(attributeName = "id")
    private UUID id;
    @DynamoDBRangeKey(attributeName = "timeInTicks")
    private long timeInTicks;
    private String object;

    public LaterItem(UUID id, long timeInTicks, String object) {
        if(timeInTicks == 0) throw new IllegalArgumentException("timeInTicks must be greater than 0");
        if(object.isEmpty()) throw new IllegalArgumentException("object cannot be empty");

        this.id = id;
        this.timeInTicks = timeInTicks;
        this.object = object;
    }

    public static LaterItem FromNewItem(NewItem newItem) {
        return new LaterItem(UUID.randomUUID(), System.currentTimeMillis(), newItem.getObject());
    }

    public NewItem ToNewItem() {
        return new NewItem(object, id, timeInTicks);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getTimeInTicks() {
        return timeInTicks;
    }

    public void setTimeInTicks(long timeInTicks) {
        this.timeInTicks = timeInTicks;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}