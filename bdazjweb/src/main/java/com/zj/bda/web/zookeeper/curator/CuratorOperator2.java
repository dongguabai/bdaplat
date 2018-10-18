package com.zj.bda.web.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.time.LocalDateTime;

public class CuratorOperator2 {

	public CuratorFramework client = null;
	public static final String zkServerPath = "192.168.220.136:2181,192.168.220.137:2181";

	public CuratorOperator2() {
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
		CuratorOperator2 cto = new CuratorOperator2();
		boolean isZkCuratorStarted = cto.client.isStarted();
		System.out.println("当前客户的状态：" + (isZkCuratorStarted ? "连接中" : "已关闭"));

        String nodePath = "/dongguabai/a";

     /*  cto.client.getData().usingWatcher((CuratorWatcher) event -> {
		   System.out.println("【使用usingWatcher】触发了watcher事件，节点路径为："+event.getPath()+"，事件类型为："+event.getType());
	   }).forPath(nodePath);*/

       //NodeCache：监听数据节点的变更，会触发事件

		//构造NodeCache实例
		NodeCache nodeCache = new NodeCache(cto.client,nodePath);
		//建立Cache
		//该方法有个boolean类型的参数，默认是false，如果设置为true，那么NodeCache在第一次启动的时候就会立刻从ZooKeeper上读取对应节点的数据内容，并保存在Cache中。
		nodeCache.start(true);
		if(nodeCache.getCurrentData()!=null){
			System.out.println("节点初始化数据为："+new String(nodeCache.getCurrentData().getData()));
		}else {
			System.out.println("节点数据为空！");
		}

		//添加事件（也有remove）
		nodeCache.getListenable().addListener(() -> {
			/*String data = new String(nodeCache.getCurrentData().getData());
			System.out.println("节点路径："+nodeCache.getCurrentData().getPath()+"，节点数据为："+data);*/
			System.out.println(LocalDateTime.now()+"  |触发节点事件!!!");
		});

		System.out.println("开始创建节点！");
		//创建节点
		cto.client.create()
				.creatingParentContainersIfNeeded()   //递归创建节点
				.withMode(CreateMode.PERSISTENT)      //节点模式
				.withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE) //ACL
				.forPath(nodePath,"new Time".getBytes());

		System.in.read();
		cto.closeZKClient();
		boolean isZkCuratorStarted2 = cto.client.isStarted();
		System.out.println("当前客户的状态：" + (isZkCuratorStarted2 ? "连接中" : "已关闭"));
	}
	
}
