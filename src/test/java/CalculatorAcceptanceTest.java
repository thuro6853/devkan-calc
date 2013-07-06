import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorAcceptanceTest {

    private String calcService;
    private Client client;

    @Before
    public void setUp() {
        client = ClientBuilder.newClient();

        String host = System.getProperty("host") + "/" + System.getProperty("context", "devkan-calc");
        calcService = host + "/services/calc";
    }

    @Test
    public void 足し算_有効値() {
        String actual = client.target(calcService + "/add")
                .queryParam("a", "2147483646")
                .queryParam("b", "1")
                .request()
                .get(String.class);

        assertThat(actual, is("2147483647"));
    }

    @Test(expected = NotFoundException.class)
    public void 足し算_ギリシャ数字() {
        client.target(calcService + "/add")
                .queryParam("a", "Ⅰ")
                .queryParam("b", "1")
                .request()
                .get(String.class);
    }

    @Test(expected = InternalServerErrorException.class)
    public void 足し算_桁溢れ() {
        client.target(calcService + "/add")
                .queryParam("a", "2147483647")
                .queryParam("b", "1")
                .request()
                .get(String.class);
    }
}
