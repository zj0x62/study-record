import com.example.demo.CompressionDecorator;
import com.example.demo.DataSource;
import com.example.demo.DataSourceDecorator;
import com.example.demo.EncryptionDecorator;
import com.example.demo.FileDataSource;
import org.junit.jupiter.api.Test;

/**
 * @Author: zhoujing
 * @Date: 2025/2/8 10:29
 * @Description:
 */
public class ApiTest {

    @Test
    public void test() {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        DataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource("D:/home/OutputDemo.txt")));
        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource("D:/home/OutputDemo.txt");
        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}
