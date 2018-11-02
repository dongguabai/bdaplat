package dgb.nospring.myrpc.demo;

import dgb.nospring.myrpc.registry.RpcAnnotation;

/**
 * @author Dongguabai
 * @date 2018/11/1 15:51
 */
@RpcAnnotation(value = IHelloService.class,version = "1.0")
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String name) {
        return "你好，" + name;
    }
}
