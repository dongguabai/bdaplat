package com.zj.bda.web.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class CuratorOperator3 {

	public CuratorFramework client = null;
	public static final String zkServerPath = "192.168.220.136:2181,192.168.220.137:2181";

	public CuratorOperator3() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);
		
		client = CuratorFrameworkFactory.builder()  //builder
				.connectString(zkServerPath)
				.sessionTimeoutMs(10000)  //session超时时间
				.retryPolicy(retryPolicy)  //重试策略
				//namespace：
				.namespace("testCRUD")
				.build();
		client.start();
	}
	
	public void closeZKClient() {
		if (client != null) {
			this.client.close();
		}
	}
	
	public static void main(String[] args) throws Exception {
		// 实例化
		CuratorOperator3 cto = new CuratorOperator3();
		boolean isZkCuratorStarted = cto.client.isStarted();
		System.out.println("当前客户的状态：" + (isZkCuratorStarted ? "连接中" : "已关闭"));

		String nodePath = "/dongguabai/a";
		//为子节点添加watcher
		PathChildrenCache childrenCache = new PathChildrenCache(cto.client,nodePath,true);
		/**
		 * StartMode：初始化方式
		 *  NORMAL：普通异步初始化
		    BUILD_INITIAL_CACHE:同步初始化
		    POST_INITIALIZED_EVENT：异步初始化，初始化之后会触发事件，而且所有的子节点的add操作都会来一遍这个也是比较坑的地方
		 */
//		childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
		childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
//		childrenCache.start(PathChildrenCache.StartMode.NORMAL);
		/*List<ChildData> list = childrenCache.getCurrentData();
		System.out.println("获取子节点列表：");
		//如果是BUILD_INITIAL_CACHE可以获取这个数据，如果不是就不行
		list.forEach(childData -> {
			System.out.println(new String(childData.getData()));
		});*/

		//注册事件，也可以加Excutor

		/**
		 * 当指定节点的子节点发生变化时，就会回调该方法oPathChildrenCacheEvent
		   类中定义了所有的事件类型，主要包括新增子节点(CHILD_ADDED)、子节点数据
		   变更(CHILD_UPDATED)和子节点删除(CHILD_REMOVED)三类。
		 */
		AtomicInteger atomicInteger = new AtomicInteger(0);
		childrenCache.getListenable().addListener(((client1, event) -> {
			atomicInteger.getAndIncrement();
			System.out.println("-----  "+LocalDateTime.now()+"  "+event.getType());
			if(event.getType().equals(PathChildrenCacheEvent.Type.INITIALIZED)){
				System.out.println("子节点初始化成功...");
			}else if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)){
				String path = event.getData().getPath();
				System.out.println("添加子节点:" + event.getData().getPath());
				System.out.println("子节点数据:" + new String(event.getData().getData()));
			}else if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)){
				System.out.println("删除子节点:" + event.getData().getPath());
			}else if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)){
				System.out.println("修改子节点路径:" + event.getData().getPath());
				System.out.println("修改子节点数据:" + new String(event.getData().getData()));
			}

			//如果想指定节点可以判断路径
			/*String path = event.getData().getPath();
			if (path.equals(ADD_PATH)) {
				System.out.println("添加子节点:" + event.getData().getPath());
				System.out.println("子节点数据:" + new String(event.getData().getData()));
			} else if (path.equals("/super/imooc/e")) {
				System.out.println("添加不正确...");
			}*/
		}));

		Thread.sleep(1000);
		System.out.println("结果："+atomicInteger);
		System.in.read();
		cto.closeZKClient();
		boolean isZkCuratorStarted2 = cto.client.isStarted();
		System.out.println("当前客户的状态：" + (isZkCuratorStarted2 ? "连接中" : "已关闭"));
	}
	
}
