package Thread;

public class HeroEnergy extends Thread{
    private Hero hero;
    public HeroEnergy(Hero h){
        this.hero = h;
    }
    public void run(){
        int i = 0;
        while(true){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.format("发动攻击%d次\n",++i);
            if(i==3){
                System.out.println("开始5秒的充能");
                i = 0;
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }


}
