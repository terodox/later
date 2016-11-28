package BasicLambda;

import java.util.UUID;

/**
 * Created by adesmarais on 11/24/2016.
 */
public class NewItem {
    private UUID id;
    private long timeInTicks;
    private String object;

    public static NewItem Create(String object) {
        return new NewItem(object, UUID.randomUUID(), System.currentTimeMillis());
    }

    public NewItem(String object, UUID id, long timeInTicks) {
        if(object.isEmpty()) throw new IllegalArgumentException("object cannot be empty");
        if(id == null) throw new IllegalArgumentException("id cannot be null");

        this.object = object;
        this.id = id;
        this.timeInTicks = timeInTicks;
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
