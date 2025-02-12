import com.example.demo.RunnableAdapter;
import com.example.demo.Task;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

/**
 * @Author: zhoujing
 * @Date: 2025/2/6 17:28
 * @Description:
 */
public class ApiTest {

    @Test
    public void test() {
//        Callable<Long> callable = new Task(12345L);
//        Thread thread = new Thread(callable);
//        thread.start();
    }

    @Test
    public void test2() {
        Callable<Long> callable = new Task(12345L);
        Thread thread = new Thread(new RunnableAdapter(callable));
        thread.start();
    }
}
