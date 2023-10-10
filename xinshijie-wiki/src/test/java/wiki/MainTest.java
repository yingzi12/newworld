package wiki;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.github.difflib.patch.Patch;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import com.xinshijie.common.utils.DiffHandleUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
//    public static void main(String args[]){
//        List<CategoryVo> list=new ArrayList<>();
//        Map<Long,CategoryVo> mapIdCat=new HashMap<>();
//        Map<Long,List<CategoryVo>> mapPidCat=new HashMap<>();
//
//        for(CategoryVo c:list){
//            mapIdCat.put(c.getId(),c);
//            if(mapPidCat.containsKey(c.getPid())){
//                List<CategoryVo> cList=mapPidCat.get(c.getPid());
//                cList.add(c);
//                mapPidCat.put(c.getPid(),cList);
//            }else{
//                List<CategoryVo> cList=new ArrayList<>();
//                cList.add(c);
//                mapPidCat.put(c.getPid(),cList);
//            }
//        }
//
//    }

    /**
     * listToTree
     * <p>方法说明<p>
     * 将JSONArray数组转为树状结构
     *
     * @param arr   需要转化的数据
     * @param id    数据唯一的标识键值
     * @param pid   父id唯一标识键值
     * @param child 子节点键值
     * @return JSONArray
     */
    public static JSONArray listToTree(JSONArray arr, String id, String pid, String child) {
        JSONArray r = new JSONArray();
        JSONObject hash = new JSONObject();
        //将数组转为Object的形式，key为数组中的id
        for (int i = 0; i < arr.size(); i++) {
            JSONObject json = (JSONObject) arr.get(i);
            hash.put(json.getString(id), json);
        }
        //遍历结果集
        for (int j = 0; j < arr.size(); j++) {
            //单条记录
            JSONObject aVal = (JSONObject) arr.get(j);
            //在hash中取出key为单条记录中pid的值
            JSONObject hashVP = (JSONObject) hash.get(aVal.get(pid).toString());
            //如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if (hashVP != null) {
                //检查是否有child属性
                if (hashVP.get(child) != null) {
                    JSONArray ch = (JSONArray) hashVP.get(child);
                    ch.add(aVal);
                    hashVP.put(child, ch);
                } else {
                    JSONArray ch = new JSONArray();
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }
            } else {
                r.add(aVal);
            }
        }
        return r;
    }

    //    //测试代码如下
    public static void main(String[] args) {
        System.out.println(DateUtil.tomorrow());
//        MailAccount account = new MailAccount();
//        account.setHost("smtp.qiye.aliyun.com");
//        account.setPort(25);
//        account.setAuth(true);
//        account.setFrom("admin@aiavr.com");
//        account.setUser("admin@aiavr.com");
//        account.setPass("Huanglu@33573");
//
//        String content="" +
//                "<h1>尊敬的admin:<h1>\n" +
//                "你正在进行安全邮箱的绑定操作,请点击下方链接地址,来进行邮箱的验证操作.  \n" +
//                "  <a href=\"https://www.aiavr.com/login?redirect=/profile/upload/QQ%E5%9B%BE%E7%89%8720220614165758.jpg\">点击验证https://www.aiavr.com/login?redirect=/profile/upload/QQ%E5%9B%BE%E7%89%8720220614165758.jpg</a>";
//
//        MailUtil.send(account, CollUtil.newArrayList("xun335610@163.com"), "测试", content, true);
//
        Document doc = Jsoup.parse("<h3><br data-cke-filler=\"true\">这里增加了一点</h3><h2>The 这是three 我们修改了什么 greasdatest things you learn from <i><strong>trave这是</strong></i>添加到中间</h2><p><i></i></p><p>Like all thwwwe great t这是hings on earth traveling teaches us by example. Here are some of the most precious lessons I’ve learned over the&nbsp;</p><p>years of traveling.adf 这是测试</p><p>asdfwwww这是af增加了内容1</p><p>asdfwwww这是af增加了内容2</p><h3><br data-cke-filler=\"true\"></h3>");
        List<TextNode> all = new ArrayList<>();
        List<String> alltext = new ArrayList<>();
        getString(doc, all, alltext);


        System.out.println("-------------------------------------");
        for (TextNode n : all) {
            n.text(n.text());
//            n.text("修改了");
            System.out.println(n.text());
        }
        System.out.println(doc);
        System.out.println("*****************************************************");
        for (String n : alltext) {
            System.out.println(n);
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        Document doc2 = Jsoup.parse("<h3><br data-cke-filler=\\\"true\\\"></h3><h2>The 这是three 我们修改了什么,我觉得不对 greasdatest things you learn from <i><strong>trave这是</strong></i>添加到中间</h2><p><i><strong>asdfaf删除新的1</strong></i></p><p>Like all thwwwe great t这是hings on earth traveling teaches us by example. Here are some of the most precious lessons I’ve learned over the&nbsp;</p><strong>asdfaf删除新的2</strong><p>years of traveling.adf 这是测试</p><h3><br data-cke-filler=\\\"true\\\"></h3>\n");
        List<TextNode> all2 = new ArrayList<>();
        List<String> alltext2 = new ArrayList<>();
        getString(doc2, all2, alltext2);

        Patch<String> patch = com.github.difflib.DiffUtils.diff(alltext, alltext2);
        System.out.println(JSONObject.toJSONString(alltext));
        System.out.println(JSONObject.toJSONString(alltext2));

        List<String> strList = DiffHandleUtils.diffString(alltext, alltext2);
        System.out.println(JSONObject.toJSONString(strList));
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .mergeOriginalRevised(true)
                .inlineDiffByWord(true)
//                .oldTag(f -> "~")      //introduce markdown style for strikethrough
//                .newTag(f -> "**")     //introduce markdown style for bold
                .build();

//compute the differences for two test texts.
        List<DiffRow> rows = generator.generateDiffRows(
                alltext,
                alltext2);

        int i = 0;
        for (int j = 0; j < rows.size(); j++) {
            DiffRow diffRow = rows.get(j);
            System.out.print(diffRow.getTag() + "\t|");
            System.out.print(diffRow.getNewLine() + "\t|");
            System.out.println(diffRow.getOldLine());
            if (!"EQUAL".equals(diffRow.getTag())) {
                if ("".equals(diffRow.getNewLine())) {
                    i--;
                    TextNode textNode = all.get(i);
                    textNode.text(textNode.text() + diffRow.getOldLine());
                    i++;
                } else {
                    all.get(i).text(diffRow.getOldLine());
                    i++;
                }
            } else {
                i++;
            }
        }
        System.out.println(doc.body().html());
//       System.out.println(JSONObject.toJSONString(all2));
        //        System.out.println(doc.body().getAllElements());

//        System.out.println(JSONObject.toJSONString( ExpCountUtils.getExp(20)));
//        List<Map<String, Object>> data = new ArrayList<>();
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", 1);
//        map.put("pid", 0);
//        map.put("name", "甘肃省");
//        data.add(map);
//        Map<String, Object> map2 = new HashMap<>();
//        map2.put("id", 2);
//        map2.put("pid", 1);
//        map2.put("name", "天水市");
//        data.add(map2);
//        Map<String, Object> map3 = new HashMap<>();
//        map3.put("id", 3);
//        map3.put("pid", 2);
//        map3.put("name", "秦州区");
//        data.add(map3);
//        Map<String, Object> map4 = new HashMap<>();
//        map4.put("id", 4);
//        map4.put("pid", 0);
//        map4.put("name", "北京市");
//        data.add(map4);
//        Map<String, Object> map5 = new HashMap<>();
//        map5.put("id", 5);
//        map5.put("pid", 4);
//        map5.put("name", "昌平区");
//        data.add(map5);
    }

    public static Integer getElement(Node node, int i, List<TextNode> all, List<String> alltext) {
//        System.out.println(i+":"+node);
//        if(element.childNodeSize()==1){
//            System.out.println(element.ownText());
//            return;
//        }
//        List<Node> nodeList= element.childNodes();
//        Elements element1 = element.children();
//        for(Node node:nodeList){
        if (node.childNodeSize() == 0) {
            if (node instanceof Element) {
                System.out.println("是Element得" + ((Element) node).ownText());
            }
            if (node instanceof TextNode) {
                all.add((TextNode) node);
//                    alltext.add(i+":"+source+"$$"+((TextNode) node).text());
                alltext.add(((TextNode) node).text());
                i++;
                System.out.println(i + ":" + node);
            }
        } else {
            List<Node> nodeList = node.childNodes();
            for (Node n : nodeList) {
                i = getElement(n, i, all, alltext);
            }
        }
        return i;
//        }
//        if(element1.size() ==0){
//            System.out.println(element.ownText());
//        }
//        for(Element e:element1 ){
//          getElement(e,i++);
//        }
    }

    public static void getString(Document doc, List<TextNode> all, List<String> alltext) {
        Elements elements = doc.body().getElementsByTag("body");
        int i = 0;
        for (Element element : elements) {
            List<Node> nodeList = element.childNodes();
            for (Node node : nodeList) {
                if (node.childNodeSize() == 0) {
                    if (node instanceof TextNode) {
                        all.add((TextNode) node);
//                        alltext.add(i+":"+source+"$$新"+((TextNode) node).text());
                        alltext.add(((TextNode) node).text());
                        i++;
                        System.out.println(node);
                    }
//                    all.add(node);
                    System.out.println("-1:" + node);
                } else {
                    i = getElement(node, i, all, alltext);
                }
            }
        }
    }
}
