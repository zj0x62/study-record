import com.alibaba.fastjson.JSON;
import com.example.demo.Activity;
import com.example.demo.ActivityController;
import org.junit.jupiter.api.Test;

/**
 * @Author: zhoujing
 * @Date: 2025/2/8 14:32
 * @Description:
 */
public class ApiTest {

    private ActivityController activityController = new ActivityController();

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Long req = 10001L;
            Activity activity = activityController.queryActivityInfo(req);
            System.out.println("测试结果：" + req + " " + JSON.toJSONString(activity));
            Thread.sleep(1000);
        }
    }
}
