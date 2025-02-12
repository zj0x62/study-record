import com.example.demo.CacheService;
import com.example.demo.factory.JDKProxy;
import com.example.demo.factory.impl.EGMCacheAdapter;
import com.example.demo.factory.impl.IIRCacheAdapter;
import com.example.demo.impl.CacheServiceImpl;
import org.junit.Test;

/**
 * @Author: zhoujing
 * @Date: 2025/2/5 10:50
 * @Description:
 */
public class ApiTest {

    @Test
    public void test_CacheService() throws Exception {

        CacheService proxy_EGM = JDKProxy.getProxy(CacheServiceImpl.class, new EGMCacheAdapter());
        proxy_EGM.set("user_name_01", "张三");
        String val01 = proxy_EGM.get("user_name_01");
        System.out.println("测试结果：" + val01);

        CacheService proxy_IIR = JDKProxy.getProxy(CacheServiceImpl.class, new IIRCacheAdapter());
        proxy_IIR.set("user_name_01", "张三");
        String val02 = proxy_IIR.get("user_name_01");
        System.out.println("测试结果：" + val02);

    }
}
