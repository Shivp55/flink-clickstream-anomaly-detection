package com.shaishav.flink.model;

/**
 * Simple click event model coming from Kafka.
 */
public class ClickEvent {

    private String userId;
    private String url;
    private long timestamp;

    public ClickEvent() {
        // default constructor needed for Jackson
    }

    public ClickEvent(String userId, String url, long timestamp) {
        this.userId = userId;
        this.url = url;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ClickEvent{" +
                "userId='" + userId + '\'' +
                ", url='" + url + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
