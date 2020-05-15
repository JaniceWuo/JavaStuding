package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;

public class JacksonTest {
    //java对象转为Json
    @org.junit.Test
    public void test1() throws JsonProcessingException {
        Person p = new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");
        //创建jackson的核心对象
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(p);
        System.out.println(json);
    }


}
