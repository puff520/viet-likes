package com.likes.common.model.common;

/***
 * 键值对对象
 */
public class KeyValue {
    private final String key;
    private final String val;

    public KeyValue(String k, String v) {
        this.key = k;
        this.val = v;
    }

    public int compare(KeyValue other) {
        return key.compareTo(other.key);
    }

    public String getKey() {
        return key;
    }

    public String getVal() {
        return val;
    }
}
