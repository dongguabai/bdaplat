package dgb.proxy;

/**
 * @author Dongguabai
 * @date 2018/10/19 10:00
 */
public class UserDaoImpl implements IUserDao{
    @Override
    public Integer save(String username) {
        System.out.println("保存了用户："+username);
        return 1;
    }

}
