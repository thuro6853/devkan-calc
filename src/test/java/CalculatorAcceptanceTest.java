import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.thoughtworks.selenium.SeleneseTestCase.assertEquals;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorAcceptanceTest {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
        String baseUrl = System.getProperty("host");
        String context = System.getProperty("context");

        if (baseUrl == null || context == null) {
            System.out.println(System.getProperties());
            fail("接続先未設定");
        }

		WebDriver driver = new FirefoxDriver();
		selenium = new WebDriverBackedSelenium(driver, baseUrl);

        selenium.open(context);

        assertThat(selenium.getTitle(), is("The 電卓"));
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
	public void 通常() {
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

    @Test
    public void 入力バリエーション() {
        // 全角数字
        selenium.type("id=x1", "４");
        selenium.type("id=x2", "０５");
        selenium.click("id=send");

        selenium.waitForCondition("selenium.browserbot.getCurrentWindow().jQuery.active == 0", "3000");
        assertEquals("9", selenium.getText("id=result"));

        // 漢数字
        selenium.type("id=x1", "一");
        selenium.type("id=x2", "1");
        selenium.click("id=send");

        selenium.waitForCondition("selenium.browserbot.getCurrentWindow().jQuery.active == 0", "3000");
        assertEquals("", selenium.getText("id=result"));
        assertEquals("error", selenium.getText("id=errors"));

        // 入力無しは0扱い
        selenium.type("id=x1", "");
        selenium.type("id=x2", "");
        selenium.click("id=send");

        selenium.waitForCondition("selenium.browserbot.getCurrentWindow().jQuery.active == 0", "3000");
        assertEquals("0", selenium.getText("id=result"));

    }

    @Test
    public void INTの境界() {
        // 最大値丁度
        selenium.type("id=x1", "0");
        selenium.type("id=x2", "2147483647");
        selenium.click("id=send");

        selenium.waitForCondition("selenium.browserbot.getCurrentWindow().jQuery.active == 0", "3000");
        assertEquals("2147483647", selenium.getText("id=result"));

        // 入力値が最大値を超える
        selenium.type("id=x1", "2147483648");
        selenium.type("id=x2", "-1");
        selenium.click("id=send");

        selenium.waitForCondition("selenium.browserbot.getCurrentWindow().jQuery.active == 0", "3000");
        assertEquals("", selenium.getText("id=result"));
        assertEquals("error", selenium.getText("id=errors"));

        // 最小値丁度
        selenium.type("id=x1", "-2147483648");
        selenium.type("id=x2", "0");
        selenium.click("id=send");

        selenium.waitForCondition("selenium.browserbot.getCurrentWindow().jQuery.active == 0", "3000");
        assertEquals("-2147483648", selenium.getText("id=result"));

        // 計算結果が最小値を超える
        selenium.type("id=x1", "-2147483648");
        selenium.type("id=x2", "-1");
        selenium.click("id=send");

        selenium.waitForCondition("selenium.browserbot.getCurrentWindow().jQuery.active == 0", "3000");
        assertEquals("", selenium.getText("id=result"));
        assertEquals("error", selenium.getText("id=errors"));
    }

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
