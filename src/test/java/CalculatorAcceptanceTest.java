import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.thoughtworks.selenium.SeleneseTestBase.fail;
import static com.thoughtworks.selenium.SeleneseTestCase.assertEquals;

public class CalculatorAcceptanceTest {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8080/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);

        selenium.open("/devkan-calc/");
	}

    /**
     * スモークテスト
     */
    @Test
    public void smoke() {
        selenium.type("id=x1", "1");
        selenium.type("id=x2", "1");
        selenium.click("id=send");

        selenium.waitForCondition("selenium.browserbot.getCurrentWindow().jQuery.active == 0", "3000");
        assertEquals("2", selenium.getText("id=result"));
    }

    /**
     * 足し算の受け入れテスト。
     * <ul>
     *     <li>異なる二項の足し算が出来ること。</li>
     *     <li>0及び負の整数の足し算が出来ること。</li>
     *     <li>連続で足し算が出来ること。</li>
     * </ul>
     */
	@Test
	public void add_normal() {
        selenium.type("id=x1", "2");
        selenium.type("id=x2", "3");
        selenium.click("id=send");

        selenium.waitForCondition("selenium.browserbot.getCurrentWindow().jQuery.active == 0", "3000");
        assertEquals("5", selenium.getText("id=result"));

        selenium.type("id=x1", "0");
        selenium.type("id=x2", "-10");
        selenium.click("id=send");

        selenium.waitForCondition("selenium.browserbot.getCurrentWindow().jQuery.active == 0", "3000");
        assertEquals("-10", selenium.getText("id=result"));
    }

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
