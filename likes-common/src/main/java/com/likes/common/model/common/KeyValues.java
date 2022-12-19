package com.likes.common.model.common;


import com.likes.common.util.encrypt.MD5Encoder;
import com.likes.common.util.URLUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


/***
 * 键值对封装
 */
public class KeyValues {

    private final List<KeyValue> keyValues = new LinkedList<>();

    public List<KeyValue> items() {
        return keyValues;
    }


    public void add(KeyValue kv) {
        if (null != kv.getVal() && !"null".equals(kv.getVal()) && !"".equals(kv.getVal())) {
            keyValues.add(kv);
        }
    }



}
