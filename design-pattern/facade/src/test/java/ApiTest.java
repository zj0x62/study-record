import com.alibaba.fastjson.JSONObject;
import com.example.demo.Company;
import com.example.demo.Facade;
import org.junit.jupiter.api.Test;

/**
 * @Author: zhoujing
 * @Date: 2025/2/8 13:25
 * @Description:
 */
public class ApiTest {

    @Test
    public void test() {
        Facade facade = new Facade();
        Company company = facade.openCompany("test");
        System.out.println(JSONObject.toJSONString(company));
    }
}
