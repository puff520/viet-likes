package com.likes.common.util;

import org.json.JSONObject;
import org.json.XML;

public class Xml2JsonUtils {

    /**
     * xml 字符串
     *
     * @param xml
     * @return
     */
    public static JSONObject xml2Json(String xml) {
        try {
            return XML.toJSONObject(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	/*public static void main(String[] args) {
		String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		str += "<result>";
		str += "<info>64010</info>";
		str += "<Code>64010</Code>";
		str += "<row productid=\"TST\" username=\"test1400\" points=\"1087794\" />";
		str += "<row productid=\"TST\" username=\"test1401\" points=\"1002228\" />";
		str += "<row productid=\"TST\" username=\"test1402\" points=\"1002826\" />";
		str += "</result>";
	
		System.out.println(org.json.XML.toJSONObject(str).toString());
		JSONObject jo = xml2Json(str);
		System.out.println(jo.toString());
	
	}*/

}
