/**
 * 单例模式测试
 */

public class SingleTest
{
    public static void main(String[] args) {
        SingleDemo sd = SingleDemo.getInstance();
        new Thread(new Run1(sd)).start();
        new Thread(new Run1(sd)).start();
        new Thread(new Run2(sd)).start();
        new Thread(new Run2(sd)).start();
    }
}

class Run1 implements Runnable
{
    private SingleDemo sd;
    private static int flag = 0;

    public Run1(SingleDemo sd) 
    {
        this.sd = sd;
    }

    public void run()
    {
        while (true){
            synchronized (sd) {
                while(sd.getFlag()) {
                    try{sd.wait();}catch(Exception e) {}
                }
                if(flag % 2 == 0) {
                    sd.setName("lisi");
                    sd.setAge(18);
                    sd.setFlag(true);
                    sd.notifyAll();
                } else {
                    sd.setName("zhangsan");
                    sd.setAge(90);
                    sd.setFlag(true);
                    sd.notifyAll();
                }

                try{Thread.sleep(50);}catch(Exception e){}
            }

            flag++;
        }
    }
}

class Run2 implements Runnable
{
    private SingleDemo sd;

    public Run2(SingleDemo sd)
    {
        this.sd = sd;
    }

    public void run()
    {
        while(true) 
        {
            synchronized (sd) {
                while(!sd.getFlag()) {
                    try{sd.wait();}catch(Exception e) {}
                }

                System.out.println(sd.getName() + " " + sd.getAge());
                sd.setFlag(false);
                sd.notifyAll();
                try{Thread.sleep(50);}catch(Exception e){}
            }
        }
    }
}
