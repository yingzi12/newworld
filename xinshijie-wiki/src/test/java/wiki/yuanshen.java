package wiki;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class yuanshen {
    static WebDriver driver = new ChromeDriver();
    static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    //    public static void main(String[] args) throws IOException {
//        System.getProperties().setProperty("webdriver.chrome.driver","C:\\git\\RuoYi-Vue\\lib\\chromedriver.exe");
//        String httpUrl="https://bbs.mihoyo.com";
////        Document doc = Jsoup.connect(httpUrl+"/ys/%E6%9D%90%E6%96%99%E4%B8%80%E8%A7%88").get();;
//        Document doc = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\yuanshen.html"));
//
//        Elements element=doc.getElementsByTag("ul");
////        System.out.println("element:" + element.html());
//
//        Elements liList = element.get(0).children();
//        int i=0;
//        for (Element tr : liList) {
//            if(i != 0) {
//              Element ulEle = tr.getElementsByTag("ul").get(0);
//              Elements liEle=ulEle.children();
//              int j=0;
//              for (Element el:liEle){
//                  Element aEl= el.getElementsByTag("a").get(0);
//                  Element imgEl= el.getElementsByTag("img").get(0);
//                  String href= aEl.attr("href");
//                  String title= aEl.attr("title");
//                  String img= imgEl.attr("src").split("\\?")[0];
//
//                  System.out.println("title:"+title+"\t href:"+href+"\t img:"+img);
////                  if(i==1){
//////                      Document content = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\yuanshenzhiwu.html"));
////                      zhiwu();
////                  }
//                  if(i==2){
//                      driver.get(httpUrl+ href);
////                      Document content = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\yuanshengushi.html"));
//
//                      gushi();
//                  }
//                  j++;
//                  if(j>3){
//                      break;
//                  }
//              }
//             System.out.println("----------------------------------------------------");
//
//             }
//                i++;
//
//        }
//        System.out.println("");
//        // 5.退出浏览器
//        driver.quit();
//    }
    public static void main(String[] args) throws IOException {
        System.getProperties().setProperty("webdriver.chrome.driver", "C:\\git\\RuoYi-Vue\\lib\\chromedriver.exe");
        String httpUrl = "https://bbs.mihoyo.com";
//        Document doc = Jsoup.connect(httpUrl+"/ys/%E6%9D%90%E6%96%99%E4%B8%80%E8%A7%88").get();;
        Document doc = Jsoup.parse(new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\diren.html"));

//    Elements element=doc.getElementsByTag("ul");
//        System.out.println("element:" + element.html());

//    Elements liList = doc.getElementsByClass("collection-avatar__item");
//       Elements liList = doc.getElementsByClass("monster-image");
//    int i=0;
//    for (Element tr : liList) {
//        String pathUrl=tr.attr("href");
//        String imgUrlSource=tr.getElementsByClass("monster-image__top--image").get(0).getElementsByTag("img").get(0).attr("data-src").split("\\?")[0];
//        String mc=tr.getElementsByClass("monster-image__top--title").get(0).html();
//        System.out.println(pathUrl+"\t"+imgUrlSource+"\t"+mc);
//        driver.get(httpUrl+ pathUrl);
//        diren();
//        if(i>2){
//            break;
//        }
//        i++;
//    }
//       shiwuEl();
//       bbaoEl();
        //npcEl();
        wupinEl();
        System.out.println();
        // 5.退出浏览器
        driver.quit();
    }

    public static void zhiwu() {
        // 查找id为“kw"的元素是否加载出来了（已经在页面DOM中存在）
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("obc-tmpl__paragraph-box")));
        WebElement element = driver.findElement(new By.ByClassName("obc-tmpl__paragraph-box"));
//        Elements conEl =  element.getElementsByClass("obc-tmpl__paragraph-box");
//        String content= conEl.get(0).html();
        System.out.println("结果:" + element.getText());

    }

    public static void gushi() {
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("detail__title")));
        WebElement titleEle = driver.findElement(new By.ByClassName("detail__title"));
//        Elements titleEle =  element.getElementsByClass("detail__title");
        System.out.println("元素内容：" + driver.findElement(new By.ByClassName("detail__content")).getAttribute("outerHTML"));
        System.out.println("标题：" + titleEle.getText());
        List<WebElement> conEles = driver.findElement(new By.ByClassName("detail__content")).findElements(new By.ByClassName("obc-tmpl--col-l2r1"));
        System.out.println("conEles长度：" + conEles.size());
        String bt = "";
        String ms = "";
        String content = "";
        for (WebElement el : conEles) {
            try {
                //  String str = el.getAttribute("innerHTML");
                // System.out.println("元素内容：" + str);
                WebElement tbEl = el.findElement(By.tagName("table"));
                bt = tbEl.findElement(By.xpath("tbody/tr[1]")).getText();
                ms = tbEl.findElement(By.xpath("tbody/tr[3]")).getText();
            } catch (Exception ex) {
                System.out.println("读取标题出问题");
                ex.printStackTrace();
            }
            try {
                content = el.findElement(new By.ByClassName("obc-tmpl__paragraph-box")).getAttribute("innerHTML");
                System.out.println(bt);
                System.out.println(ms);
                System.out.println(content);
                bt = "";
                ms = "";
                content = "";
            } catch (Exception ex) {
                System.out.println("读取内容出问题");
                ex.printStackTrace();
            }
        }
    }

    public static void role() {
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("obc-tmpl__part--main")));
        List<WebElement> titleEle = driver.findElements(new By.ByClassName("obc-tmpl__part--main"));
        List<String> noTitle = Arrays.asList("角色CV", "特殊料理", "角色名片");
        for (WebElement webElement : titleEle) {
            String title = webElement.findElement(new By.ByClassName("obc-tmpl-fold__title")).getText();
            System.out.println("标题:" + title);
            if (!noTitle.contains(title)) {
                String content = webElement.findElement(new By.ByClassName("obc-tmpl__paragraph-box")).getAttribute("innerHTML");
                System.out.println("内容:" + content);
            }
        }
    }

    public static void diren() {
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("obc-tmpl__paragraph-box")));
        List<WebElement> titleEle = driver.findElements(new By.ByClassName("obc-tmpl__paragraph-box"));
        WebElement ruoElement = titleEle.get(0);
        WebElement contentElement = titleEle.get(1);
        System.out.println(ruoElement.getAttribute("innerHTML"));
        System.out.println(contentElement.getAttribute("innerHTML"));
    }

    public static void shiwuEl() throws IOException {
        String httpUrl = "https://bbs.mihoyo.com";
//        Document doc = Jsoup.connect(httpUrl+"/ys/%E6%9D%90%E6%96%99%E4%B8%80%E8%A7%88").get();;
        Document doc = Jsoup.parse(new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\diren.html"));
        Elements liList = doc.getElementsByClass("position-list__item");
        int i = 0;
        for (Element liTr : liList) {
            Element tr = liTr.getElementsByTag("a").get(0);
            String pathUrl = tr.attr("href");
            String imgUrlSource = tr.getElementsByTag("img").get(0).attr("data-src").split("\\?")[0];
            String mc = tr.attr("title");
            System.out.println(pathUrl + "\t" + imgUrlSource + "\t" + mc);
            driver.get(httpUrl + pathUrl);
            shiwu();
            if (i > 2) {
                break;
            }
            i++;
        }
    }

    public static void shiwu() {
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("detail__content")));
        WebElement contentEle = driver.findElement(new By.ByClassName("detail__content"));
        List<WebElement> cenEles = contentEle.findElements(new By.ByTagName("table"));
        for (WebElement we : cenEles) {
            List<WebElement> trEls = we.findElements(By.tagName("tr"));
            String imgUrl = trEls.get(0).findElement(By.tagName("img")).getAttribute("src").split("\\?")[0];
            String mc = trEls.get(0).findElements(By.tagName("td")).get(1).getText();
            //加工材料
            String cl = trEls.get(2).findElement(By.tagName("td")).getAttribute("innerHTML");
            //描述
            String ms = trEls.get(3).findElement(By.tagName("td")).getAttribute("innerHTML");
            System.out.println(imgUrl + "\t" + mc + "\t" + cl + "\t" + ms);
        }
    }

    public static void bbaoEl() throws IOException {
        String httpUrl = "https://bbs.mihoyo.com";
//        Document doc = Jsoup.connect(httpUrl+"/ys/%E6%9D%90%E6%96%99%E4%B8%80%E8%A7%88").get();;
        Document doc = Jsoup.parse(new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\bbao.html"));
        Elements liList = doc.getElementsByClass("position-list__item");
        int i = 0;
        for (Element liTr : liList) {
            Element tr = liTr.getElementsByTag("a").get(0);
            String pathUrl = tr.attr("href");
            String imgUrlSource = tr.getElementsByTag("img").get(0).attr("data-src").split("\\?")[0];
            String mc = tr.attr("title");
            System.out.println(pathUrl + "\t" + imgUrlSource + "\t" + mc);
            driver.get(httpUrl + pathUrl);
            bbao();
            if (i > 2) {
                break;
            }
            i++;
        }
    }

    public static void bbao() {
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("detail__content")));
        WebElement contentEle = driver.findElement(new By.ByClassName("detail__content"));
        WebElement cenEles = contentEle.findElement(new By.ByTagName("table"));
        List<WebElement> trEls = cenEles.findElements(By.tagName("tr"));
        String ms = trEls.get(2).findElement(new By.ByTagName("td")).findElement(new By.ByTagName("p")).getText();
        String content = trEls.get(2).findElement(new By.ByTagName("td")).getAttribute("innerHTML");
        System.out.println(ms);
        System.out.println(content);
    }

    public static void npcEl() throws IOException {
        String httpUrl = "https://bbs.mihoyo.com";
//        Document doc = Jsoup.connect(httpUrl+"/ys/%E6%9D%90%E6%96%99%E4%B8%80%E8%A7%88").get();;
        Document doc = Jsoup.parse(new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\npc.html"));
        Elements liList = doc.getElementsByClass("position-list__item");
        int i = 0;
        for (Element liTr : liList) {
            Element tr = liTr.getElementsByTag("a").get(0);
            String pathUrl = tr.attr("href");
            String imgUrlSource = tr.getElementsByTag("img").get(0).attr("data-src").split("\\?")[0];
            String mc = tr.attr("title");
            System.out.println(pathUrl + "\t" + imgUrlSource + "\t" + mc);
            driver.get(httpUrl + pathUrl);
            npc();
            if (i > 2) {
                break;
            }
            i++;
        }
    }

    public static void npc() {
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("detail__content")));
        WebElement contentEle = driver.findElement(new By.ByClassName("detail__content"));
        String jj = contentEle.findElement(new By.ByClassName("obc-tmpl-character-table--pc")).getAttribute("innerHTML");
        String ms = contentEle.findElements(new By.ByClassName("obc-tmpl__rich-text")).get(1).getAttribute("innerHTML");
        String content = contentEle.findElement(new By.ByClassName("obc-tmpl__paragraph-box")).getAttribute("innerHTML");
        System.out.println(jj);
        System.out.println(ms);
        System.out.println(content);
    }

    public static void wupinEl() throws IOException {
        String httpUrl = "https://bbs.mihoyo.com";
//        Document doc = Jsoup.connect(httpUrl+"/ys/%E6%9D%90%E6%96%99%E4%B8%80%E8%A7%88").get();;
        Document doc = Jsoup.parse(new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\wupin.html"));
        Elements liList = doc.getElementsByClass("position-list__item");
        int i = 0;
        for (Element liTr : liList) {
            Element tr = liTr.getElementsByTag("a").get(0);
            String pathUrl = tr.attr("href");
            String imgUrlSource = tr.getElementsByTag("img").get(0).attr("data-src").split("\\?")[0];
            String mc = tr.attr("title");
            System.out.println(pathUrl + "\t" + imgUrlSource + "\t" + mc);
            driver.get(httpUrl + pathUrl);
            wupin();
            if (i > 2) {
                break;
            }
            i++;
        }
    }

    public static void wupin() {
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("obc-tmpl__part--main")));
        WebElement jianjieEle = driver.findElement(new By.ByClassName("obc-tmpl__part--main"));
        String jianjie = jianjieEle.findElement(new By.ByTagName("table")).getAttribute("outerHTML");

        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("obc-tmpl__part--introduction")));
        WebElement contentEle = driver.findElement(new By.ByClassName("obc-tmpl__part--introduction"));
        String content = contentEle.findElement(new By.ByTagName("table")).getAttribute("outerHTML");
        System.out.println(jianjie);
        System.out.println(content);
    }
}
