package wiki;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class HtmlTest {
    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.parse(new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\ii.html"));
        Element element = doc.getElementById("CardSelectTr");
        Elements trList = element.getElementsByTag("tr");
        for (Element tr : trList) {
            String 分类1 = tr.attr("data-param1");
            String 子类2 = tr.attr("data-param2");
            String 名称 = tr.attr("data-collection");
            System.out.println(分类1 + ":" + 子类2 + ":" + 名称);
            Elements tdLIST = tr.getElementsByTag("td");
            Elements td0 = tdLIST.get(0).getElementsByTag("img");
            if (td0.size() > 0) {
                String imgUrl = td0.get(0).attr("src");
                System.out.println("td:" + imgUrl);
                long size = HttpUtil.downloadFile(imgUrl, FileUtil.file("c://test//1.png"));
                System.out.println("Download size: " + size);
            }
            Element td4 = tdLIST.get(4);
            System.out.println("td4:" + td4.html());

        }
        System.out.println();
    }
}
