package dgb.proxy;

import java.lang.reflect.Proxy;

/**
 * @author Dongguabai
 * @date 2018/10/19 10:02
 */
public class Tes {

    public static void main(String[] args) {
       /* UserDaoImpl userDao = new UserDaoImpl();
        userDao.save("张三");*/
      /* IUserDao userDao = new UserDaoProxy(new UserDaoImpl());
       userDao.save("张三");*/
      IUserDao userDao = (IUserDao) Proxy.newProxyInstance(IUserDao.class.getClassLoader(), new Class[]{IUserDao.class}, (proxy, method, args1) -> {
          System.out.println("记录了日志-----");
          return method.invoke(new UserDaoImpl(),args1);
      });
      userDao.save("张三");
    }
}
