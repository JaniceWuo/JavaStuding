package com.reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

//反射操作注解
public class test12 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class c1 = Class.forName("com.reflection.Student2");
        //通过反射获取注解
        Annotation[] annotations = c1.getAnnotations();
//        for(Annotation annotation:annotations){
//            System.out.println(annotation);
//        }
        TableJanice tableJanice = (TableJanice) c1.getAnnotation(TableJanice.class);
        String value = tableJanice.value();
        System.out.println(value);

        Field name = c1.getDeclaredField("name");
        FieldJanice annotation = name.getAnnotation(FieldJanice.class);
        System.out.println(annotation.columnName());
        System.out.println(annotation.type());
        System.out.println(annotation.length());
    }
}

@TableJanice("db_student")
class Student2{
    @FieldJanice(columnName = "db_id",type = "int",length = 10)
    private int id;
    @FieldJanice(columnName = "db_age",type = "int",length = 10)
    private int age;
    @FieldJanice(columnName = "db_name",type = "varchar",length = 3)
    private String name;

    public Student2() {
    }

    public Student2(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}


//类名的注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableJanice{
    String value();
}

//属性的注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldJanice{
    String columnName();
    String type();
    int length();
}