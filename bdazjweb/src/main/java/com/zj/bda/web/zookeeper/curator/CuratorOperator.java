package com.zj.bda.web.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorOperator {

	public CuratorFramework client = null;
	public static final String zkServerPath = "192.168.220.135:2181,192.168.220.136:2181,192.168.220.137:2181";

	/**
	 * 实例化zk客户端
	 */
	public CuratorOperator() {
		/**
		 * 同步创建zk示例，原生api是异步的
		 * 
		 * curator链接zookeeper的重试策略:
		 *
		 * 1>ExponentialBackoffRetry【推荐】
			 * baseSleepTimeMs：初始sleep时间(ms)
			 * maxRetries：最大重试次数，超过时间就不链接了
			 * maxSleepMs：最大重试时间(ms)
		 *
		 * 给定一个初始sleep时间base5leep丁imeMs，在这个基础上结合重试次数，通过以下公式计算出当前需要sleep的时间:
		   当前sleep时间=baseSleepTimeMs*Math.max(1, random.nextInt(1<<(retryCount+1)))
		   可以看出，随着重试次数的增加，计算出的sleep时间会越来越大。如果该sleep时间在maxSleepMs的范围之内，那么就使用该sleep时间，否则使用maxSleepMs。另外，
		   maxRetries参数控制了最大重试次数，以避免无限制的重试。
		 */
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);
		
		/**
		 * curator链接zookeeper的策略:
		 * 2>RetryNTimes【推荐】
			 * n：重试的次数
			 * sleepMsBetweenRetries：每次重试间隔的时间(ms)
		 */
//		RetryPolicy retryPolicy = new RetryNTimes(3, 5000);
		
		/**
		 * curator链接zookeeper的策略:
		 * 3>RetryOneTime
		 * sleepMsBetweenRetry:只重试一次，重试间隔的时间
		 */
//		RetryPolicy retryPolicy2 = new RetryOneTime(3000);
		
		/**
		 * 4>
		 * 永远重试，不推荐使用
		 */
//		RetryPolicy retryPolicy3 = new RetryForever(retryIntervalMs)
		
		/**
		 * curator链接zookeeper的策略:
		 * 5>RetryUntilElapsed
		 * maxElapsedTimeMs:最大重试时间
		 * sleepMsBetweenRetries:每次重试间隔
		 * 重试时间超过maxElapsedTimeMs后，就不再重试
		 */
//		RetryPolicy retryPolicy4 = new RetryUntilElapsed(2000, 3000);

		//创建客户端
		client = CuratorFrameworkFactory.builder()  //builder
				.connectString(zkServerPath)
				.sessionTimeoutMs(10000)  //session超时时间
				.retryPolicy(retryPolicy)  //重试策略
				//namespace：
				.namespace("testCRUD")
				.build();
		/**
		 * CuratorFrameworkFactory工厂在创建出一个客户端CuratorFramework实例之后，实质上并没有完成会话的创建，而是需要调用
		   CuratorFramework的sta rt)方法来完成会话的创建。
		 */
		client.start();
	}
	
	/**
	 * 
	 * @Description: 关闭zk客户端连接
	 */
	public void closeZKClient() {
		if (client != null) {
			this.client.close();
		}
	}
	
	public static void main(String[] args) throws Exception {
		// 实例化
		CuratorOperator cto = new CuratorOperator();
		boolean isZkCuratorStarted = cto.client.isStarted();
		System.out.println("当前客户的状态：" + (isZkCuratorStarted ? "连接中" : "已关闭"));

        String nodePath = "/dongguabai/a";
        //创建节点
       /* byte[] data = "abcd".getBytes();
        cto.client.create()
                .creatingParentContainersIfNeeded()   //递归创建节点
                .withMode(CreateMode.PERSISTENT)      //节点模式
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE) //ACL
                .forPath(nodePath,data); //不指定内容，则内容为空*/

        //获取节点
       /* byte[] bytes = cto.client.getData().forPath(nodePath);
        System.out.println("第一次获取节点数据为："+new String(bytes));

        Stat stat = new Stat();
        byte[] bytes1 = cto.client.getData().storingStatIn(stat).forPath(nodePath);
        System.out.println("第二次获取节点数据为："+new String(bytes1));
        System.out.println("获取的Stat为："+ JsonUtil.toJSON(stat));
*/

       cto.client.getData().usingWatcher((CuratorWatcher) event -> {
		   System.out.println("【使用usingWatcher】触发了watcher事件，节点路径为："+event.getPath()+"，事件类型为："+event.getType());
	   }).forPath(nodePath);

       //NodeCache：监听数据节点的变更，会触发事件

		//构造NodeCache实例
		NodeCache nodeCache = new NodeCache(cto.client,nodePath);
		//建立Cache
		//该方法有个boolean类型的参数，默认是false，如果设置为true，那么NodeCache在第一次启动的时候就会立刻从ZooKeeper上读取对应节点的数据内容，并保存在Cache中。
		nodeCache.start();

        //获取子节点
      /*  List<String> list = cto.client.getChildren().forPath(nodePath);
        System.out.println("开始打印子节点：");
        list.forEach(result-> System.out.println(result));
        System.out.println("打印结束！");*/

        //修改节点
      /*  Stat stat = cto.client.setData().forPath(nodePath,"new1".getBytes());
        System.out.println("第一次获取节点数据为："+new String(cto.client.getData().forPath(nodePath)));

        Stat stat1 = cto.client.setData().withVersion(stat.getVersion()).forPath(nodePath, "new2".getBytes());
        System.out.println("第二次获取节点数据为："+new String(cto.client.getData().forPath(nodePath)));*/

        //删除节点
       /* Stat stat = new Stat();
        byte[] bytes1 = cto.client.getData().storingStatIn(stat).forPath(nodePath);
        System.out.println("获取节点数据为："+new String(bytes1));
        cto.client.delete()
                .guaranteed()  //防止网络抖动，只要客户端会话有效，那么Curator 会在后台持续进行删除操作，直到节点删除成功
                .deletingChildrenIfNeeded()  //如果有子节点会删除，注意除非人为删除namespace，否则namespace不会删除
                .withVersion(stat.getVersion())
                .forPath(nodePath);*/

        Thread.sleep(300000);
		
		cto.closeZKClient();
		boolean isZkCuratorStarted2 = cto.client.isStarted();
		System.out.println("当前客户的状态：" + (isZkCuratorStarted2 ? "连接中" : "已关闭"));
	}
	
}
