import com.example.demo.BossCar;
import com.example.demo.ElectricEngine;
import com.example.demo.FuelEngine;
import com.example.demo.RefinedCar;
import com.example.demo.TinyCar;
import org.junit.jupiter.api.Test;

/**
 * @Author: zhoujing
 * @Date: 2025/2/7 10:23
 * @Description:
 */
public class ApiTest {

    @Test
    public void test() {
        RefinedCar bossCar = new BossCar(new ElectricEngine());
        bossCar.drive();

        RefinedCar tinyCar = new TinyCar(new FuelEngine());
        tinyCar.drive();
    }
}
