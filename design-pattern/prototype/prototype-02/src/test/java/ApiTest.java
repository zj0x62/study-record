import com.example.demo.QuestionBankController;
import org.junit.Test;

/**
 * @Author: zhoujing
 * @Date: 2025/2/5 17:12
 * @Description:
 */
public class ApiTest {

    @Test
    public void test_QuestionBank() throws CloneNotSupportedException {
        QuestionBankController questionBankController = new QuestionBankController();
        System.out.println(questionBankController.createPaper("花花", "1000001921032"));
        System.out.println(questionBankController.createPaper("豆豆", "1000001921051"));
        System.out.println(questionBankController.createPaper("大宝", "1000001921987"));
    }
}
