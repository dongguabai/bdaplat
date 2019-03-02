package dgb.test.concurrent;

public class SynchronizedDemo2 {
	//�������
    private boolean ready = false;
    private int result = 0;
    private int number = 1;   
    //д����
    public void write(){
    	ready = true;	      				 //1.1				
    	number = 2;		                    //1.2			    
    }
    //������
    public void read(){			   	 
    	if(ready){						     //2.1
    		result = number*3;	 	//2.2
    	}   	
    	System.out.println("result��ֵΪ��" + result);
    }

    //�ڲ��߳���
    private class ReadWriteThread2 extends Thread {
    	//���ݹ��췽���д����flag������ȷ���߳�ִ�ж���������д����
    	private boolean flag;
    	public ReadWriteThread2(boolean flag){
    		this.flag = flag;
    	}
        @Override                                                                    
        public void run() {
        	if(flag){
        		//���췽���д���true��ִ��д����
        		write();
        	}else{
        		//���췽���д���false��ִ�ж�����
        		read();
        	}
        }
    }

    public static void main(String[] args)  {
    	SynchronizedDemo2 synDemo = new SynchronizedDemo2();
    	//�����߳�ִ��д����
    	synDemo .new ReadWriteThread2(true).start();
    	/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	//�����߳�ִ�ж�����
    	synDemo.new ReadWriteThread2(false).start();
    }
}

