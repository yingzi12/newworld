//package com.xinshijie;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.File;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.Arrays;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class YuanshenGuanTest {
//    @Autowired
//    private WikiUtils wikiUtils;
//
//    static WebDriver driver = new ChromeDriver();
//    static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
////    @Test
////    public  void test() throws IOException {
////        WebClient webClient = new WebClient(BrowserVersion.CHROME);
////        webClient.getOptions().setCssEnabled(false);
////        webClient.getOptions().setJavaScriptEnabled(true);
////        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
////        webClient.getOptions().setThrowExceptionOnScriptError(false);
////        webClient.waitForBackgroundJavaScript(600*1000);
////        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
////        webClient.getOptions().setThrowExceptionOnScriptError(false);
////
////        String httpUrl="https://bbs.mihoyo.com";
//////        Document doc = Jsoup.connect(httpUrl+"/ys/%E6%9D%90%E6%96%99%E4%B8%80%E8%A7%88").get();;
////        Document doc = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\yuanshen.html"));
////
////        Elements element=doc.getElementsByTag("ul");
//////        System.out.println("element:" + element.html());
////
////        Elements liList = element.get(0).children();
////        int i=0;
////        for (Element tr : liList) {
////            if(i != 0) {
////              Element ulEle = tr.getElementsByTag("ul").get(0);
////              Elements liEle=ulEle.children();
////              int j=0;
////              for (Element el:liEle){
////                  Element aEl= el.getElementsByTag("a").get(0);
////                  System.out.println(aEl.html());
////                  Element imgEl= aEl.getElementsByTag("img").get(0);
////                  String href= aEl.attr("href");
////                  String title= aEl.attr("title");
////                  String img= imgEl.attr("data-src").split("\\?")[0];
////
////                  System.out.println("title:"+title+"\t href:"+href+"\t img:"+img);
//////                  HtmlPage page=webClient.getPage("https://bbs.mihoyo.com"+href);
//////                  String pageAsXml=page.asXml();
////
//////                  Document content =Jsoup.parse(pageAsXml);
////                  if(i==1){
//////                      Document content = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\yuanshenzhiwu.html"));
////                     if(wikiUtils.isElement(title)) {
////                         driver.get(httpUrl+ href);
////                         zhiwu(title, title, img, "动物", "其他");
////                     }
////
////                  }
////                  if(i==2){
////                      driver.get(httpUrl+ href);
////
//////                      Document content = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\yuanshengushi.html"));
////                      gushi(img);
////                  }
//////                  j++;
//////                  if(j>2){
//////                      break;
//////                  }
////              }
////             System.out.println("----------------------------------------------------");
////
////             }
////                i++;
////
////        }
////        System.out.println("");
////        // 5.退出浏览器
////        driver.quit();
////    }
//
////    public  void zhiwu(Element element,String title,String intro ,String imgUrlSource,String fl1,String fl2){
////        System.out.println(element.html());
////        Elements conEl =  element.getElementsByClass("obc-tmpl__paragraph-box");
////        String content= conEl.get(0).html();
////        System.out.println(content);
////        wikiUtils.addEl(title,intro,content,imgUrlSource,fl1,fl2);
////    }
//
////    public  void gushi(Element element,String imgUrlSour){
////        Elements titleEle =  element.getElementsByClass("detail__title");
////        System.out.println(titleEle.get(0).html());
////        Elements conEle =  element.getElementsByClass("detail__content").get(0).getElementsByClass("obc-tmpl");
//////        Elements conEle =  titleEle.getElementsByClass("detail__content").getElementsByClass("obc-tmpl");
////        Long storyId = wikiUtils.addStory(titleEle.get(0).html(),imgUrlSour);
////        Long reelId = wikiUtils.addReel(storyId,titleEle.get(0).html());
////        String jianjie="";
////        for(Element el:conEle){
////            Element btEl= el.getElementsByClass("obc-tmpl__part--align-banner").get(0);
////            String bt=btEl.getElementsByTag("tr").get(0).getElementsByTag("td").get(1).html().replace("<label>名称：</label>","");
////            String ms=btEl.getElementsByTag("tr").get(2).getElementsByTag("td").get(0).html().replace("描述：","");
////            Element tdEl= btEl.getElementsByTag("tr").get(2).getElementsByTag("td").get(0);
////            jianjie=tdEl.getElementsByTag("p").get(0).text().replace("描述：","");
////            String content= el.getElementsByClass("obc-tmpl__paragraph-box").get(0).html();
////
////            wikiUtils.addChapter(storyId,titleEle.get(0).html(),reelId,bt,content);
////        }
////        wikiUtils.updateStory(storyId,jianjie,jianjie);
////        System.out.println(conEle.size());
////
////    }
//
//    public void zhiwu(String title, String intro, String imgUrlSource, String fl1, String fl2) {
//        // 查找id为“kw"的元素是否加载出来了（已经在页面DOM中存在）
//        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("obc-tmpl__paragraph-box")));
//        WebElement element = driver.findElement(new By.ByClassName("obc-tmpl__paragraph-box"));
////        Elements conEl =  element.getElementsByClass("obc-tmpl__paragraph-box");
////        String content= conEl.get(0).html();
//        System.out.println("结果:" + element.getText());
//        wikiUtils.addEl(title, intro, element.getText(), imgUrlSource, fl1, fl2);
//
//    }
//
//    public void gushi(String imgUrlSour) {
//        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("detail__title")));
//        WebElement titleEle = driver.findElement(new By.ByClassName("detail__title"));
////        System.out.println("元素内容："+driver.findElement(new By.ByClassName("detail__content")).getAttribute("outerHTML"));
////        System.out.println("标题："+titleEle.getText());
//
//        String sname = titleEle.getText().replace("名称：", "");
//        Long storyId = wikiUtils.addStory(sname, imgUrlSour);
//        Long reelId = wikiUtils.addReel(storyId, sname);
//
//        List<WebElement> conEles = driver.findElement(new By.ByClassName("detail__content")).findElements(new By.ByClassName("obc-tmpl--col-l2r1"));
////        System.out.println("conEles长度："+conEles.size());
//        String bt = "";
//        String ms = "";
//        String content = "";
//        for (WebElement el : conEles) {
//            try {
//                //  String str = el.getAttribute("innerHTML");
//                // System.out.println("元素内容：" + str);
//                WebElement tbEl = el.findElement(By.tagName("table"));
//                bt = tbEl.findElement(By.xpath("tbody/tr[1]")).getText().replace("名称：", "");
//                ms = tbEl.findElement(By.xpath("tbody/tr[3]")).getText().replace("描述：", "").replace("编辑寄语:", "");
//            } catch (Exception ex) {
//                System.out.println("读取标题出问题");
//                ex.printStackTrace();
//            }
//            try {
//                content = el.findElement(new By.ByClassName("obc-tmpl__paragraph-box")).getAttribute("innerHTML");
//                System.out.println(bt);
//                System.out.println(ms);
//                System.out.println(content);
//                wikiUtils.addChapter(storyId, sname, reelId, bt, content);
//                bt = "";
//                content = "";
//
//
//            } catch (Exception ex) {
//                System.out.println("读取内容出问题");
//                ex.printStackTrace();
//            }
//        }
//        wikiUtils.updateStory(storyId, ms, ms);
//
//    }
//
//   // @Test
//    public  void testRole() throws IOException {
//        System.getProperties().setProperty("webdriver.chrome.driver","C:\\git\\RuoYi-Vue\\lib\\chromedriver.exe");
//        String httpUrl="https://bbs.mihoyo.com";
////        Document doc = Jsoup.connect(httpUrl+"/ys/%E6%9D%90%E6%96%99%E4%B8%80%E8%A7%88").get();;
//        Document doc = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\diren.html"));
//
////    Elements element=doc.getElementsByTag("ul");
////        System.out.println("element:" + element.html());
//
////        Elements liList = doc.getElementsByClass("collection-avatar__item");
////        int i=0;
////        for (Element tr : liList) {
////            String pathUrl=tr.attr("href");
////            String imgUrlSource=tr.getElementsByClass("collection-avatar__icon").get(0).attr("data-src").split("\\?")[0];
////            String mc=tr.getElementsByClass("collection-avatar__title").get(0).html();
////            System.out.println(pathUrl+"\t"+imgUrlSource+"\t"+mc);
//        Elements liList = doc.getElementsByClass("monster-image");
//        int i=0;
//        for (Element tr : liList) {
//            String pathUrl=tr.attr("href");
//            String imgUrlSource=tr.getElementsByClass("monster-image__top--image").get(0).getElementsByTag("img").get(0).attr("data-src").split("\\?")[0];
//            String mc=tr.getElementsByClass("monster-image__top--title").get(0).html();
//            System.out.println(pathUrl+"\t"+imgUrlSource+"\t"+mc);
//            driver.get(httpUrl+ pathUrl);
////            wikiUtils.insertElement(mc,mc,mc,imgUrlSource,"角色","角色");
//            diren( mc,imgUrlSource);
////            if(i>2){
////                break;
////            }
//            i++;
//        }
//        System.out.println("");
//        // 5.退出浏览器
//        driver.quit();
//    }
//
//
//    public   void role(String eleTitle,String imgUrlSour){
//        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("obc-tmpl__part--main")));
//        List<WebElement> titleEle= driver.findElements(new By.ByClassName("obc-tmpl__part--main"));
//        List<String> noTitle= Arrays.asList("角色CV","特殊料理","角色名片");
//
//        Long cid=wikiUtils.addCategory("角色","角色");
//        if (!wikiUtils.isElement(eleTitle, wikiUtils.getWid(), cid)) {
//            return;
//        }
//        String fileName= wikiUtils.uploadImage(eleTitle,imgUrlSour);
//        Long eid=wikiUtils.insertElement(eleTitle,"角色说明","/profile/upload/"+fileName,cid);
//
//        for(WebElement webElement:titleEle){
//            String title= webElement.findElement(new By.ByClassName("obc-tmpl-fold__title")).getText();
//            System.out.println("标题:"+title);
//            if(!noTitle.contains(title)) {
//                String content = webElement.findElement(new By.ByClassName("obc-tmpl__paragraph-box")).getAttribute("innerHTML");
//                wikiUtils.insertElementContent(eid,title,content);
//                System.out.println("内容:"+content);
//            }
//        }
//    }
//
//    public  void diren(String eleTitle,String imgUrlSour){
//
//        Long cid=wikiUtils.addCategory("生物","怪物");
//        if (!wikiUtils.isElement(eleTitle, wikiUtils.getWid(), cid)) {
//            return;
//        }
//
//        String fileName= wikiUtils.uploadImage(eleTitle,imgUrlSour);
//        Long eid=wikiUtils.insertElement(eleTitle,"怪物说明",fileName,cid);
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("obc-tmpl__paragraph-box")));
//        List<WebElement> titleEle= driver.findElements(new By.ByClassName("obc-tmpl__paragraph-box"));
//        String ruoElement = titleEle.get(0).getAttribute("innerHTML");
//        String contentElement = titleEle.get(1).getAttribute("innerHTML");
//        wikiUtils.insertElementContent(eid,"介绍","<img src=\"" + wikiUtils.getImgUrl() + "/profile/upload/" + fileName + "\"/>" +contentElement);
//        wikiUtils.insertElementContent(eid,"弱点",ruoElement);
//    }
//
////    @Test
//    public  void shiwuEl() throws IOException {
//        String httpUrl="https://bbs.mihoyo.com";
////        Document doc = Jsoup.connect(httpUrl+"/ys/%E6%9D%90%E6%96%99%E4%B8%80%E8%A7%88").get();;
//        Document doc = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\diren.html"));
//        Elements liList = doc.getElementsByClass("position-list__item");
//        int i=0;
//        for (Element liTr : liList) {
//            Element tr=liTr.getElementsByTag("a").get(0);
//            String pathUrl= tr.attr("href");
//            String imgUrlSource=tr.getElementsByTag("img").get(0).attr("data-src").split("\\?")[0];
//            String eleTitle=tr.attr("title");
//            System.out.println(pathUrl+"\t"+imgUrlSource+"\t"+eleTitle);
//
//            Long cid=wikiUtils.addCategory("食物","");
//            if (!wikiUtils.isElement(eleTitle, wikiUtils.getWid(), cid)) {
//
//            }else {
//                String fileName = wikiUtils.uploadImage(eleTitle, imgUrlSource);
//                Long eid = wikiUtils.insertElement(eleTitle, eleTitle, fileName, cid);
//
//                driver.get(httpUrl + pathUrl);
//                shiwu(eid);
//            }
////            if(i>2){
////                break;
////            }
//            i++;
//        }
//        driver.quit();
//    }
//
//    public  void shiwu(Long eid){
//        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("detail__content")));
//        WebElement contentEle= driver.findElement(new By.ByClassName("detail__content"));
//        List<WebElement> cenEles=contentEle.findElements(new By.ByTagName("table"));
//        for(WebElement we:cenEles){
//            List<WebElement> trEls=we.findElements(By.tagName("tr"));
//            String imgUrl= trEls.get(0).findElement(By.tagName("img")).getAttribute("src").split("\\?")[0];
//
//            String mc= trEls.get(0).findElements(By.tagName("td")).get(1).getText().replace("名称：","");
//            String fileName= wikiUtils.uploadImage(mc,imgUrl);
//
//            //加工材料
//            String cl= trEls.get(2).findElement(By.tagName("td")).getAttribute("innerHTML");
//            //描述
//            String ms= trEls.get(3).findElement(By.tagName("td")).getAttribute("innerHTML");
//            System.out.println(fileName+"\t"+mc+"\t"+cl+"\t"+ms);
//
//            wikiUtils.insertElementContent(eid,mc,"<img src=\"" + wikiUtils.getImgUrl() + fileName + "\"/>" +"<br>"+
//                            cl+"<br>"+
//                    ms);
//        }
//    }
//
//
//    public  void bbaoEl() throws IOException {
//        String httpUrl="https://bbs.mihoyo.com";
////        Document doc = Jsoup.connect(httpUrl+"/ys/%E6%9D%90%E6%96%99%E4%B8%80%E8%A7%88").get();;
//        Document doc = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\bbao.html"));
//        Elements liList = doc.getElementsByClass("position-list__item");
//        int i=0;
//        for (Element liTr : liList) {
//            Element tr=liTr.getElementsByTag("a").get(0);
//            String pathUrl= tr.attr("href");
//            String imgUrlSource=tr.getElementsByTag("img").get(0).attr("data-src").split("\\?")[0];
//            String eleTitle=tr.attr("title").replace("说明:","");
//            System.out.println(pathUrl+"\t"+imgUrlSource+"\t"+eleTitle);
//
//            Long cid=wikiUtils.addCategory("其他","");
//            if (wikiUtils.isElement(eleTitle, wikiUtils.getWid(), cid)) {
//
//                String fileName = wikiUtils.uploadImage(eleTitle.replace(":",""), imgUrlSource);
//                Long eid = wikiUtils.insertElement(eleTitle, eleTitle, fileName, cid);
//
//                driver.get(httpUrl + pathUrl);
//                bbao(eid, fileName);
//            }
//            i++;
////          if(i>2){
////                break;
////            }
//        }
//        driver.quit();
//    }
//
//    public  void bbao(Long  eid,String fileName){
//        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("detail__content")));
//        WebElement contentEle= driver.findElement(new By.ByClassName("detail__content"));
//        WebElement cenEles=contentEle.findElement(new By.ByTagName("table"));
//        List<WebElement> trEls=cenEles.findElements(By.tagName("tr"));
//        try {
//            String ms = trEls.get(2).findElement(new By.ByTagName("td")).findElement(new By.ByTagName("p")).getText().replace("描述：", "");
//            String content = trEls.get(2).findElement(new By.ByTagName("td")).getAttribute("innerHTML").replace("描述：", "");
//            System.out.println(ms);
//            System.out.println(content);
//
//            wikiUtils.insertElementContent(eid, "简介", "<img src=\"" + wikiUtils.getImgUrl() + fileName + "\"/>" + "<br>" +
//                    content);
//            wikiUtils.updateElement(eid, ms);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
////    @Test
//    public  void npcEl() throws IOException {
//        String httpUrl="https://bbs.mihoyo.com";
////        Document doc = Jsoup.connect(httpUrl+"/ys/%E6%9D%90%E6%96%99%E4%B8%80%E8%A7%88").get();;
//        Document doc = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\npc.html"));
//        Elements liList = doc.getElementsByClass("position-list__item");
//        int i=0;
//        for (Element liTr : liList) {
//            Element tr=liTr.getElementsByTag("a").get(0);
//            String pathUrl= tr.attr("href");
//            String imgUrlSource=tr.getElementsByTag("img").get(0).attr("data-src").split("\\?")[0];
//            String eleTitle=tr.attr("title");
//            System.out.println(pathUrl+"\t"+imgUrlSource+"\t"+eleTitle);
//
//            Long cid=wikiUtils.addCategory("角色","其他");
//            if (wikiUtils.isElement(eleTitle, wikiUtils.getWid(), cid)) {
//
//                String fileName = wikiUtils.uploadImage(eleTitle.replace(":",""), imgUrlSource);
//                Long eid = wikiUtils.insertElement(eleTitle, eleTitle, fileName, cid);
//
//                driver.get(httpUrl + pathUrl);
//                try {
//                    npc(eid, fileName);
//                }catch (Exception ex){
//                    System.out.println("------------------------------");
//                    System.out.println(eleTitle);
//                }
//            }
////            if(i>2){
////                break;
////            }
//            i++;
//        }
//        driver.quit();
//    }
//
//    public  void npc(Long  eid,String fileName){
//        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("detail__content")));
//        WebElement contentEle= driver.findElement(new By.ByClassName("detail__content"));
//        String jj= contentEle.findElement(new By.ByClassName("obc-tmpl-character-table--pc")).getAttribute("outerHTML");
//        String ms=  contentEle.findElements(new By.ByClassName("obc-tmpl__rich-text")).get(1).findElement(By.tagName("p")).getAttribute("innerHTML");
//        String content=  contentEle.findElement(new By.ByClassName("obc-tmpl__paragraph-box")).getAttribute("innerHTML");
//        System.out.println(jj);
//        System.out.println(ms);
//        System.out.println(content);
//
//        wikiUtils.insertElementContent(eid, "简介",  jj);
//        wikiUtils.insertElementContent(eid, "语言",  content);
//        wikiUtils.updateElement(eid, ms);
//    }
//
//    @Test
//    public  void wupinEl() throws IOException {
//        String httpUrl="https://bbs.mihoyo.com";
////        Document doc = Jsoup.connect(httpUrl+"/ys/%E6%9D%90%E6%96%99%E4%B8%80%E8%A7%88").get();;
//        Document doc = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\wupin.html"));
//        Elements liList = doc.getElementsByClass("position-list__item");
//        int i=0;
//        for (Element liTr : liList) {
//            Element tr=liTr.getElementsByTag("a").get(0);
//            String pathUrl= tr.attr("href");
//            String imgUrlSource="";
//            try {
//                 imgUrlSource = tr.getElementsByTag("img").get(0).attr("data-src").split("\\?")[0];
//            }
//            catch (Exception ex){
//                ex.printStackTrace();
//            }
//            String mc=tr.attr("title");
//
//            System.out.println(pathUrl+"\t"+imgUrlSource+"\t"+mc);
//
//            Long cid=wikiUtils.addCategory("物品","");
//            if (wikiUtils.isElement(mc, wikiUtils.getWid(), cid)) {
//                String fileName="";
//                if(!imgUrlSource.equals("")) {
//                    fileName = wikiUtils.uploadImage(mc.replace(":", ""), imgUrlSource);
//                }
//                Long eid = wikiUtils.insertElement(mc, mc, fileName, cid);
//
//                driver.get(httpUrl + pathUrl);
//                wupin(eid, fileName);
//            }
////            if(i>2){
////                break;
////            }
//            i++;
//        }
//    }
//
//    public  void wupin(Long  eid,String fileName){
//        try {
//            wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("obc-tmpl__part--main")));
//            WebElement jianjieEle = driver.findElement(new By.ByClassName("obc-tmpl__part--main"));
//            String jianjie = jianjieEle.findElement(new By.ByTagName("table")).getAttribute("outerHTML");
//            wikiUtils.insertElementContent(eid, "简介", jianjie);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        try{
////           wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByClassName("obc-tmpl__part--introduction")));
//           WebElement contentEle= driver.findElement(new By.ByClassName("obc-tmpl__part--introduction"));
//           String content=  contentEle.findElement(new By.ByTagName("table")).getAttribute("outerHTML");
//           wikiUtils.insertElementContent(eid, "描述",  content);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//    }
//}
