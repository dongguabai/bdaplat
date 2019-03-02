package dgb.test.concurrent;
 
import java.util.Date;
 
public class TestSingleObject1{
	private static TestSingleObject1 object;
	private Date createTime;
	
	public static TestSingleObject1 getInstatnce(){
		if(object==null){
			synchronized(TestSingleObject1.class){
				if(object==null){
					object=new TestSingleObject1();
					
					try {
						Thread.sleep(1000);//故意休眠，模拟初始化耗时过程
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					object.createTime=new Date();
				}
			}
		}
		return object;
	}
 
	public static void main(String[] args) throws ClassNotFoundException {
		Runnable runnable=new Runnable() {
			@Override
			public void run() {
				TestSingleObject1 object=TestSingleObject1.getInstatnce();
				//这里的object.createTime可能是null，引发空指针异常
				System.out.println("hashCode="+object.hashCode()+",createTime="+object.createTime.toString());
			}
		};
		
		Thread thread1=new Thread(runnable);
		Thread thread2=new Thread(runnable);
		Thread thread3=new Thread(runnable);
		
		thread1.start();
		thread2.start();
		thread3.start();
	}
	
}
