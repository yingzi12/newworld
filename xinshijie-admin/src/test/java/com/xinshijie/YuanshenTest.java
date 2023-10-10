//package com.xinshijie;
//
//import cn.hutool.core.io.FileUtil;
//import cn.hutool.core.util.IdUtil;
//import cn.hutool.http.HttpUtil;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.xinshijie.common.enums.*;
//import com.xinshijie.common.utils.file.AliOSS;
//import com.xinshijie.wiki.domain.Category;
//import com.xinshijie.wiki.domain.Chapter;
//import com.xinshijie.wiki.domain.Story;
//import com.xinshijie.wiki.dto.ElementCategoryDto;
//import com.xinshijie.wiki.dto.ElementContentDto;
//import com.xinshijie.wiki.dto.ElementDto;
//import com.xinshijie.wiki.dto.ImageDto;
//import com.xinshijie.wiki.service.*;
//import com.xinshijie.wiki.vo.ImageVo;
//import lombok.SneakyThrows;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.IOException;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class YuanshenTest {
//    @Autowired
//    private ICategoryService categoryService;
//
//    @Autowired
//    private IElementService elementService;
//    @Autowired
//    private IElementCategoryService elementCategoryService;
//    @Autowired
//    private IElementContentService contentService;
//    @Autowired
//    private IImageService imageService;
//
//    @Autowired
//    private IStoryService storyService;
//    @Autowired
//    private IChapterService chapterService;
//
//    private int wid=33;
//    private String  wname="原神";
//    private String imgUrl="https://shadow-xinshijie.oss-cn-hongkong.aliyuncs.com";
//    //    @Test
////    public void test(){
////
////        String tujian=HttpRequest.get("https://api-static.mihoyo.com/common/blackboard/ys_obc/v1/home/content/list?app_sn=ys_obc&channel_id=189")
////                .execute().body();
////        JSONObject jsonObject=JSONObject.parseObject(tujian);
////        JSONObject dateJson=jsonObject.getJSONObject("data");
////        JSONArray listJson=dateJson.getJSONArray("list");
////        List<Children> childrenList=listJson.toList(Children.class);
////
////        for(Children c:childrenList){
////            int code=10;
////            int tier=0;
////            for(Children children:c.getChildren()){
////                Category category=new Category();
////                category.setWid(33);
////                category.setCode(code+"");
////                code++;
////                category.setPcode(0+"");
////                category.setExtra(children.getCh_ext());
////                category.setCreateName("admin");
////                category.setCreateId(0l);
////                category.setTier(tier);
////                category.setPid(0l);
////                category.setLabel(children.getName());
////                System.out.println(children.getList().size());
//////                categoryService.insertCategory(category);
////
////            }
////        }
////
////    }
////
//    public void insertElement(ElementDto elementDto,Long cid,String content){
//        QueryWrapper<com.xinshijie.wiki.domain.Element> qwElement=new QueryWrapper<>();
//        qwElement.eq("title",elementDto.getTitle());
//        qwElement.eq("wid",elementDto.getWid());
//        com.xinshijie.wiki.domain.Element el = elementService.getOne(qwElement);
//        if(el != null){
//            return;
//        }
//        if((cid == null) || (elementDto.getTitle() == null)){
//            System.out.println("缺少关键参数");
//            return;
//        }
//        elementDto.setWid(wid);
//        elementDto.setWname(wname);
//        elementDto.setSofttype(0);
//        elementDto.setVersion(0);
//        elementDto.setStatus(ElementStatusEnums.NORMAL.getCode());
//        elementDto.setCreateId(0L);
//        elementDto.setCreateName("admin");
//        elementDto.setUpdateId(0L);
//        elementDto.setUpdateName("admin");
//
//        com.xinshijie.wiki.domain.Element element=elementService.add(elementDto);
//
//        ElementCategoryDto elementCategoryDto=new ElementCategoryDto();
//        elementCategoryDto.setEid(element.getId());
//        elementCategoryDto.setCid(cid);
//        elementCategoryDto.setWid(wid);
//
//        elementCategoryService.add(elementCategoryDto);
//
//        ElementContentDto elementContent=new ElementContentDto();
//        elementContent.setEid(element.getId());
//        elementContent.setContent(content);
//        elementContent.setTitle("简介");
//        elementContent.setStatus(ElementStatusEnums.NORMAL.getCode());
//        elementContent.setWid(wid);
//        elementContent.setCreateId(0L);
//        elementContent.setCreateName("admin");
//        elementContent.setUpdateId(0L);
//        elementContent.setUpdateName("admin");
//        contentService.add(elementContent);
//    }
//
//    @SneakyThrows
//    public String uploadImage(String fileName, File file){
//        BufferedInputStream in = FileUtil.getInputStream(file);
//        String md5= DigestUtils.md5Hex(in);
//        ImageVo imageVo=imageService.getInfoByMd5(md5);
//        if(imageVo != null){
//            return imageVo.getFileName();
//        }else {
//            AliOSS.upload(fileName, file);
//            // 上传并返回新文件名称
//            ImageDto image = new ImageDto();
//            image.setFileName("/profile/upload/" +fileName);
//            image.setFileUrl(AliOSS.URL);
//            image.setFileSize(file.length());
//            image.setFileType("png");
//            image.setMd5(md5);
//            image.setOriginalFilename(file.getName());
//            imageService.add(image);
//            return image.getFileName();
//        }
//    }
//
//    public Long addCategory(String pcname, String cname){
//        QueryWrapper<Category> pqw=new QueryWrapper<>();
//        pqw.eq("label",pcname);
//        pqw.eq("wid",wid);
//        Category pc = categoryService.getOne(pqw);
//        if(pc == null){
//            QueryWrapper<Category> qwCount=new QueryWrapper<>();
//            qwCount.eq("wid",wid);
//            qwCount.eq("pid",0);
//            Long count = categoryService.count(qwCount);
//            pc=new Category();
//            pc.setTier(0);
//            pc.setPid(0L);
//            pc.setLabel(pcname);
//            pc.setPcode("0");
//            pc.setCode((count+10)+"");
//            pc.setWid(wid);
//            categoryService.insertCategory(pc);
//        }
//        QueryWrapper<Category> qw=new QueryWrapper<>();
//        qw.eq("label",cname);
//        qw.eq("wid",wid);
//        qw.eq("pid",pc.getId());
//        Category category = categoryService.getOne(qw);
//        if(category == null){
//            QueryWrapper<Category> qwCount=new QueryWrapper<>();
//            qwCount.eq("wid",wid);
//            qwCount.eq("pid",pc.getId());
//            Long count = categoryService.count(qwCount);
//            category=new Category();
//            category.setTier(1);
//            category.setPid(pc.getId());
//            category.setLabel(cname);
//            category.setPcode(pc.getCode());
//            category.setCode(pc.getCode()+(count+10));
//            category.setWid(wid);
//            categoryService.insertCategory(category);
//        }
//        return category.getId();
//    }
//    //@Test
//    public void test2() throws IOException {
//        Document doc = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\ii.html"));
//        Element element=doc.getElementById("CardSelectTr");
//        Elements trList = element.getElementsByTag("tr");
//        int i=0;
//        for (Element tr : trList) {
//            String fl1 = tr.attr("data-param1");
//            String fl2 = tr.attr("data-param2");
//            String mc = tr.attr("data-collection");
//            System.out.println(fl1+":"+fl2+":"+mc);
//            Elements tdLIST=tr.getElementsByTag("td");
//            Elements td0= tdLIST.get(0).getElementsByTag("img");
//            String fileName="";
//            if(td0.size()>0) {
//                String imgUrl = td0.get(0).attr("src");
//                System.out.println("td:" + imgUrl);
//                String filePath="c://test//"+i+".png";
//                i++;
//                long size = HttpUtil.downloadFile(imgUrl, FileUtil.file(filePath));
//                System.out.println("Download size: " + size);
//                String simpleUUID = IdUtil.simpleUUID();
//                fileName="33/"+simpleUUID+".png";
//                uploadImage(fileName,new File(filePath));
//            }
//            Element td4= tdLIST.get(4);
//            ElementDto dto=new ElementDto();
//            Long cid=addCategory(fl1,fl2);
//            dto.setTitle(mc);
//            dto.setImageUrls(fileName);
//            dto.setIntro(td4.html().split("<br>")[0]);
//            insertElement(dto,cid, td4.html());
//            System.out.println("td4:" + td4.html());
//
//        }
//    }
//
//    @Test
//    public void test3() throws IOException {
//        String httpUrl="https://wiki.biligame.com";
////        Document doc = Jsoup.connect(httpUrl+"/ys/%E6%9D%90%E6%96%99%E4%B8%80%E8%A7%88").get();;
//        Document doc = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\ii.html"));
//
//        Element element=doc.getElementById("CardSelectTr");
////        System.out.println("element:" + element.html());
//
//        Elements trList = element.getElementsByTag("tr");
//        int i=0;
//        for (Element tr : trList) {
//            Elements td = tr.getElementsByTag("td");
//            System.out.println("----------------------------------------------------");
//            String tcHtml="";
//            if(td.size()!=0) {
//                Elements aList=td.get(1).getElementsByTag("a");
//                String wangzhi=aList.get(0).attr("href");
//                System.out.println("网址:" +wangzhi );
//                Document detailDoc = Jsoup.connect(httpUrl+wangzhi).get();
////                 Document detailDoc = Jsoup.parse( new File("C:\\git\\RuoYi-Vue\\xinshijie-wiki\\src\\test\\java\\wiki\\detail.html"));
//                Element contenEl=detailDoc.getElementById("mw-content-text");
//                Elements tableList=contenEl.getElementsByTag("table");
//                Elements trConList=tableList.get(0).getElementsByTag("tr");
//                String elementName=trConList.get(0).getElementsByTag("th").get(0).ownText();
//                System.out.println("元素名称:"+elementName);
//                Elements imgList=trConList.get(1).getElementsByTag("img");
//                String fileName="";
//                if (imgList.size() > 0) {
//                    String imgUrl=trConList.get(1).getElementsByTag("img").get(0).attr("src");
//                    System.out.println("图片地址:"+imgUrl);
//                    System.out.println("td:" + imgUrl);
//                    String filePath="c://test//"+i+".png";
//                    i++;
//                    long size = HttpUtil.downloadFile(imgUrl, FileUtil.file(filePath));
//                    System.out.println("Download size: " + size);
//                    String simpleUUID = IdUtil.simpleUUID();
//                    fileName="33/"+simpleUUID+".png";
//                    uploadImage(fileName,new File(filePath));
//                }
//                String types=trConList.get(3).getElementsByTag("td").get(0).ownText();
//                System.out.println("类型:"+types);
//                String source=trConList.get(4).getElementsByTag("td").get(0).ownText();
//                System.out.println("来源:"+source);
//                try {
//                    if(trConList.size()>=6) {
//                        String shuoming = trConList.get(6).getElementsByTag("td").get(0).html();
//                        System.out.println("说明:" + shuoming);
//
//                        ElementDto dto = new ElementDto();
//                        Long cid = addCategory("材料", types);
//                        dto.setTitle(elementName);
//                        dto.setImageUrls(imgUrl + "/profile/upload/" + fileName);
//                        dto.setIntro(source);
//                        insertElement(dto, cid, "<img src=\"" + imgUrl + "/profile/upload/" + fileName + "\"   />\n" + shuoming);
//                    }
//                }catch (Exception ex){
//                    System.out.println();
//                    ex.printStackTrace();
//                }
////                 if(i>2){
////
////                     return;
////                 }
////                 i++;
//            }
//        }
//    }
//
//
//    public Story addStory(String name,String imagurl){
//       Story story=new Story();
//       story.setName(name);
//       story.setStatus(StoryStatusEnums.NORMAL.getCode());
//       story.setCreateId(0L);
//       story.setImgUrl(imagurl);
//       story.setCreateName("admin");
//       story.setWid(wid);
//       story.setCategory(TypeEnums.OTHER.getCode());
//       story.setWname(wname);
//       story.setAuditNumber(AuditStatusEnums.YES.getCode());
//       story.setCountChapter(1);
//       storyService.save(story);
//
//       return story;
//    }
//    public Long addReel(Long sid,String sname){
//        Chapter chapter=new Chapter();
//        chapter.setWid(wid);
//        chapter.setTitle("其他");
//        chapter.setLevel(0);
//        chapter.setStatus(ChapterStatusEnums.NORMAL.getCode());
//        chapter.setPid(0L);
//        chapter.setSname(sname);
//        chapter.setSid(sid);
//        chapterService.save(chapter);
//        return chapter.getId();
//    }
//    public Long addChapter(Long sid,String sname,Long pid,String content){
//        Chapter chapter=new Chapter();
//        chapter.setWid(wid);
//        chapter.setTitle("其他");
//        chapter.setLevel(0);
//        chapter.setStatus(ChapterStatusEnums.NORMAL.getCode());
//        chapter.setPid(pid);
//        chapter.setPname("其他");
//        chapter.setSname(sname);
//        chapter.setContent(content);
//        chapter.setSid(sid);
//        chapterService.save(chapter);
//        return chapter.getId();
//    }
//}
