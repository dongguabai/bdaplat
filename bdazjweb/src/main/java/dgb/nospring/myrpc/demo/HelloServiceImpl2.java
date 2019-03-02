package dgb.nospring.myrpc.demo;

import dgb.nospring.myrpc.registry.RpcAnnotation;

/**
 * @author Dongguabai
 * @date 2018/11/1 15:51
 */
@RpcAnnotation(value = IHelloService.class,version = "2.0")
public class HelloServiceImpl2 implements IHelloService {

    @Override
    public String sayHello(String name) {
        return "你好2.0，" + name;
    }
}
