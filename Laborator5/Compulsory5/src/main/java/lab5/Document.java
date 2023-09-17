package lab5;

import java.io.Serializable;
import java.util.*;

public class Document implements Serializable {
    private String id;
    private String title;
    private String location;
    private Map<String, String> tags;

    public Document() {
        this.tags = new HashMap<>();
    }

    public Document(String id) {
        this.id = id;
        this.tags = new HashMap<>();
    }

    public Document(String title, String id, String location) {
        this.title = title;
        this.id = id;
        this.location = location;
        this.tags = new HashMap<>();
    }

    public String getId() {
        return this.id;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return this.title;
    }

    public void addTag(String key, String value) {
        tags.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(title).append(" (").append(id).append(")").append(" [").append(location).append("], ");
        for(var tag : tags.keySet()) {
            sb.append(tag).append(" = ").append(tags.get(tag));
        }
        return sb.toString();
    }
}
