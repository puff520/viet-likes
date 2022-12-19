package com.likes.common.util.uploadFile;

import java.text.ParseException;
import java.util.UUID;

public class UUIDUtils {

	public static void main(String[] argv) throws ParseException {

		String id =UUIDUtils.getUUID();
		System.out.println("id:"+id);
	}


	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
