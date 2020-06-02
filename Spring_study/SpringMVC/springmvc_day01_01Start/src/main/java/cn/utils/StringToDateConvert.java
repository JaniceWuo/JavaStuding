package cn.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//字符串转日期
public class StringToDateConvert implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if(source == null){
            throw new RuntimeException("请您传入数据");
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
