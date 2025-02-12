import com.example.demo.CacheService;
import com.example.demo.cuisine.impl.CacheServiceImpl;
import org.junit.Test;

/**
 * @Author: zhoujing
 * @Date: 2025/2/5 10:34
 * @Description:
 */
public class ApiTest {

    @Test
    public void test_CacheService() {

        CacheService cacheService = new CacheServiceImpl();

        cacheService.set("user_name_01", "张三", 1);
        String val01 = cacheService.get("user_name_01", 1);
        System.out.println("测试结果：" + val01);

    }
}
