package com.likes.common.spring;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.util.Date;

/**
 * @author abu
 * @version : 1.00
 * @Description : java日期对象经过Jackson库转换成JSON日期格式化自定义类（去掉秒的显示）
 * @History：Editor　　　version　　　Time　　　　　Operation　　　　　　　Description*
 *
 */
public class JsonDateTimeNoMinSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeString(DateFormatUtils.format(value, "yyyy-MM-dd HH:mm"));
	}
}
