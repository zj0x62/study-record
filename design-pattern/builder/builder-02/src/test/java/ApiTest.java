import com.example.demo.Builder;
import org.junit.Test;

/**
 * @Author: zhoujing
 * @Date: 2025/2/5 14:12
 * @Description:
 */
public class ApiTest {

    @Test
    public void test_Builder(){
        Builder builder = new Builder();

        // 豪华欧式
        System.out.println(builder.levelOne(132.52D).getDetail());

        // 轻奢田园
        System.out.println(builder.levelTwo(98.25D).getDetail());

        // 现代简约
        System.out.println(builder.levelThree(85.43D).getDetail());
    }
}
