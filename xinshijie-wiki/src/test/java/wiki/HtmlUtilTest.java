package wiki;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class HtmlUtilTest {
    //循环等待时间
//    static long timeOutInSeconds=5;
    public static void main(String[] args) throws IOException {
        System.getProperties().setProperty("webdriver.chrome.driver", "C:\\git\\RuoYi-Vue\\lib\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
//        WebDriverWait wait = new WebDriverWait(driver );
//        wait.nutil(expectCondition);
        //Chrome浏览器
        String url = "https://bbs.mihoyo.com/ys/obc/content/4627/detail?bbs_presentation_style=no_header";
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
// 查找id为“kw"的元素是否加载出来了（已经在页面DOM中存在）
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("obc-tmpl__paragraph-box")));
        WebElement element = driver.findElement(new By.ByClassName("obc-tmpl__paragraph-box"));

        WebElement titleEle = driver.findElement(new By.ByClassName("detail__title"));
//        Elements titleEle =  element.getElementsByClass("detail__title");
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("detail__content")));
        System.out.println("元素内容：" + driver.findElement(new By.ByClassName("detail__content")).getAttribute("outerHTML"));
        System.out.println("标题：" + titleEle.getText());
        List<WebElement> conEles = driver.findElement(new By.ByClassName("detail__content")).findElements(new By.ByClassName("obc-tmpl--col-l2r1"));
        System.out.println("conEles长度：" + conEles.size());
        for (WebElement el : conEles) {
            String str = el.getAttribute("outerHTML");
            System.out.println("元素内容：" + str);
            WebElement tbEl = el.findElement(By.tagName("table"));
            String ti = tbEl.findElement(By.xpath("tbody/tr[1]")).getText();
            String ms = tbEl.findElement(By.xpath("tbody/tr[3]")).getText();
            String content = el.findElement(new By.ByClassName("obc-tmpl__paragraph-box")).getAttribute("outerHTML");
            System.out.println(ti);
            System.out.println(ms);

            System.out.println(content);
        }
        System.out.println("获取结果：" + element.getText());
        // 5.退出浏览器
        driver.quit();
    }
}
