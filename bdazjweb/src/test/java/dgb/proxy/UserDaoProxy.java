package dgb.proxy;

/**
 * @author Dongguabai
 * @date 2018/10/19 10:09
 */
public class UserDaoProxy implements IUserDao{

    private IUserDao userDao;

    @Override
    public Integer save(String username) {
        log();
        return userDao.save(username);
    }

    public UserDaoProxy(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void log(){
        System.out.println("记录了日志------");
    }
}
