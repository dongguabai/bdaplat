package dgb.test.concurrent;

public class SynchronizedDemo {
	//共享变量
    private static boolean ready = false;
    private static int result = 0;
    private static int number = 1;

    public static void write(){
    	ready = true;	      				//1.1
    	number = 2;		                    //1.2
    }

    public static void read(){
    	if(ready){						    //2.1
    		result = number*3;	 			//2.2
    	}
    	System.out.println("result为" + result);
    }

    private static class ReadWriteThread extends Thread {
    	private boolean flag;
    	public ReadWriteThread(boolean flag){
    		this.flag = flag;
    	}
        @Override
        public void run() {
        	if(flag){
        		write();
        	}else{
        		read();
        	}
        }
    }

    public static void main(String[] args)  {
        new ReadWriteThread(true).start();
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        new ReadWriteThread(false).start();
    }
}

