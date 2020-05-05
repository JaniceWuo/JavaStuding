package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnectionPool {
    public static void main(String[] args) {
        ConnectionPool cp = new ConnectionPool(3);
        for(int i = 0;i<100;i++){
            new WorkingThread("working thread"+i,cp).start();
        }
    }
}

class WorkingThread extends Thread{
    private ConnectionPool cp;
    public WorkingThread(String name,ConnectionPool cp){
        super(name);
        this.cp = cp;
    }

    public void run(){
        Connection c = cp.getConnection();
        System.out.println(this.getName()+"获取了一根连接，并开始工作");
        try(Statement st = c.createStatement()) {
            Thread.sleep(1000);
            st.execute("select * from hero");
        }catch (SQLException | InterruptedException e){
            e.printStackTrace();
        }
        cp.returnConnection(c);
    }
}