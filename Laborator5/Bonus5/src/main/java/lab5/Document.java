package lab5;

import java.io.Serializable;
import java.util.*;
import org.apache.tika.metadata.Metadata;

public class Document implements Serializable {
    private String id;
    private String title;
    private String location;
    private Map<String, String> tags;
    private Metadata metadata;

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

    public Document(String title, String id, String location, Metadata metadata) {
        this.title = title;
        this.id = id;
        this.location = location;
        this.tags = new HashMap<>();
        this.metadata = metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getId() {
        return this.id;
    }

    public String getLocation() {
        return location;
    }

    public Metadata getMetadata() {
        return this.metadata;
    }

    public String getTitle() {
        return this.title;
    }

    public void addTag(String key, String value) {
        tags.put(key, value);
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(title).append(" (").append(id).append(")").append(" [").append(location).append("], ");
        for(String tag : tags.keySet()) {
            sb.append(tag).append(" = ").append(tags.get(tag));
        }
        return sb.toString();
    }
}
