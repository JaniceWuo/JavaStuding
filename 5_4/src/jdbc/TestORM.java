package jdbc;

import TestLambda.Hero;
import com.sun.corba.se.impl.orbutil.HexOutputStream;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestORM {
    public static Hero get(int id){
        Hero hero = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/how2java?useSSL=false","root",
                "aptx4869."); Statement s = c.createStatement();)
        {
            String sql = "select * from hero where id = "+id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()){
                hero = new Hero();
                String name = rs.getString(2);
                float hp = rs.getFloat("hp");
                int damage = rs.getInt(4);
                hero.name = name;
                hero.hp = hp;
                hero.damage = damage;
                System.out.println("取出id为"+id+"的数据，它的name是:"+hero.name);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return hero;
    }

    public static void add(Hero h){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = String.format("insert into hero values(null,'%s',%f,%d)",h.name,h.hp,h.damage);
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/how2java?useSSL=false","root",
                "aptx4869."); Statement s = c.createStatement();)
        {
            s.execute(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static List<Hero> list(){
        List<Hero> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = "select * from hero";
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/how2java?useSSL=false","root",
                "aptx4869."); Statement s = c.createStatement();)
        {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                Hero hero = new Hero();
//                int id = rs.getInt(1);
                String name = rs.getString(2);
                float hp = rs.getFloat(3);
                int damage = rs.getInt(4);
                hero.name = name;
                hero.hp = hp;
                hero.damage = damage;
                list.add(hero);
            }
            System.out.println("数据库一共有" + list.size() + "条数据");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
//        Hero h = get(22);
//        System.out.println(h);
//        Hero h = new Hero();
//        h.name = "sb";
//        h.damage = 100;
//        h.hp = 300.0f;
//        add(h);
        list();
    }
}
