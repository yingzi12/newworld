package wiki;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class HtmlTest2 {
    public static void main(String[] args) throws IOException {

        String httpUrl = "https://wiki.biligame.com";
//        Document doc = Jsoup.connect(httpUrl+"/ys/%E6%9D%90%E6%96%99%E4%B8%80%E8%A7%88").get();;
        Document doc = Jsoup.parse(new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\ii.html"));

        Element element = doc.getElementById("CardSelectTr");
//        System.out.println("element:" + element.html());

        Elements trList = element.getElementsByTag("tr");
        int i = 0;
        for (Element tr : trList) {
            Elements td = tr.getElementsByTag("td");
            System.out.println("----------------------------------------------------");
            if (td.size() != 0) {
                Elements aList = td.get(1).getElementsByTag("a");
                String wangzhi = aList.get(0).attr("href");
                System.out.println("网址:" + wangzhi);
                Document detailDoc = Jsoup.connect(httpUrl + wangzhi).get();
//                 Document detailDoc = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\detail.html"));
                Element contenEl = detailDoc.getElementById("mw-content-text");
                Elements tableList = contenEl.getElementsByTag("table");
                Elements trConList = tableList.get(0).getElementsByTag("tr");
                String elementName = trConList.get(0).getElementsByTag("th").get(0).ownText();
                System.out.println("元素名称:" + elementName);
                Elements imgList = trConList.get(1).getElementsByTag("img");
                if (imgList.size() > 0) {
                    String elementImage = trConList.get(1).getElementsByTag("img").get(0).attr("src");
                    System.out.println("图片地址:" + elementImage);
                }
                String types = trConList.get(3).getElementsByTag("td").get(0).ownText();
                System.out.println("类型:" + types);
                String source = trConList.get(4).getElementsByTag("td").get(0).ownText();
                System.out.println("来源:" + source);
                String shuoming = trConList.get(6).getElementsByTag("td").get(0).html();
                System.out.println("说明:" + shuoming);
//                 if(i>2){
//
//                     return;
//                 }
//                 i++;
            }
        }
        System.out.println();
    }
}
