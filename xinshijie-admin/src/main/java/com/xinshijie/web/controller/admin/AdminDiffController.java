package com.xinshijie.web.controller.admin;

import cn.hutool.http.HtmlUtil;
import com.alibaba.fastjson2.JSONObject;
import com.github.difflib.patch.Patch;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.utils.DiffHandleUtils;
import com.xinshijie.wiki.dto.*;
import com.xinshijie.wiki.service.IDraftContentService;
import com.xinshijie.wiki.service.IElementContentService;
import com.xinshijie.wiki.vo.DiffVo;
import com.xinshijie.wiki.vo.DraftContentVo;
import com.xinshijie.wiki.vo.ElementContentVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "AdminDiffController", description = "后台-内容比对")
@RequestMapping("/admin/diff")
public class AdminDiffController extends BaseController {

    @Autowired
    private IElementContentService contentService;
    @Autowired
    private IDraftContentService draftContentService;

    /**
     * 获取世界评论详细信息
     */
    @GetMapping(value = "/content")
    public Result<DiffVo> diff(@RequestParam("newId") Long newId, @RequestParam("oldId") Long oldId) {
        ElementContentVo oldContent = contentService.getInfo(oldId);
        DraftContentVo newContent = draftContentService.getInfo(newId);
        Document doc = Jsoup.parse(oldContent.getContent());
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

        Document doc2 = Jsoup.parse(newContent.getContent());
        List<TextNode> all2 = new ArrayList<>();
        List<String> alltext2 = new ArrayList<>();
        getString(doc2, all2, alltext2);

//        Patch<String> patch = com.github.difflib.DiffUtils.diff(alltext, alltext2);
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

        DiffVo diffVo = new DiffVo();
        diffVo.setNewTitle(oldContent.getTitle());
        diffVo.setOldTitle(newContent.getTitle());

        diffVo.setNewContent(HtmlUtil.unescape(doc.body().html()));
        diffVo.setOldContent(HtmlUtil.unescape(doc2.body().html()));
        return Result.success(diffVo);
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
