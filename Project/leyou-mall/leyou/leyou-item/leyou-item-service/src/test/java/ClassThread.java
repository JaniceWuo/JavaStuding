public class ClassThread {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }.start();

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + "程序员");
                }
            }
        };
        new Thread(runnable).start();

        new Thread(new Runnable(){

            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + "程序员222");
                }
            }
        }).start();

        new Thread(()->{
            System.out.println("执行");
        }).start();
    }
}
