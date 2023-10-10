package com.xinshijie;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinshijie.common.enums.*;
import com.xinshijie.common.utils.file.AliOSS;
import com.xinshijie.wiki.domain.*;
import com.xinshijie.wiki.dto.ElementCategoryDto;
import com.xinshijie.wiki.dto.ElementContentDto;
import com.xinshijie.wiki.dto.ElementDto;
import com.xinshijie.wiki.dto.ImageDto;
import com.xinshijie.wiki.service.*;
import com.xinshijie.wiki.vo.ImageVo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class WikiUtils {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IElementService elementService;
    @Autowired
    private IElementCategoryService elementCategoryService;
    @Autowired
    private IElementContentService contentService;
    @Autowired
    private IImageService imageService;

    @Autowired
    private IStoryService storyService;
    @Autowired
    private IChapterService chapterService;

    private final int wid = 33;
    private final String wname = "原神";
    private final String imgUrl = "https://shadow-xinshijie.oss-cn-hongkong.aliyuncs.com";

    public int getWid() {
        return wid;
    }

    public String getWname() {
        return wname;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void addEl(String eleTitle, String intro, String content, String imgUrlSour, String fl1, String fl2) {

        String fileName = uploadImage(eleTitle, imgUrlSour);
        Long cid = addCategory(fl1, fl2);
        if (isElement(eleTitle, wid, cid)) {
            Long eid = insertElement(eleTitle, intro, "/profile/upload/" + fileName, cid);
            insertElementContent(eid, "简介", "<img src=\"" + imgUrl + "/profile/upload/" + fileName + "\"" + content);
        }
    }

    public boolean isElement(String title, Integer wid, Long cid) {
        QueryWrapper<Element> qwElement = new QueryWrapper<>();
        qwElement.eq("title", title);
        qwElement.eq("wid", wid);
        List<Element> el = elementService.list(qwElement);
        return el.size() <= 0;
    }

    public Long insertElement(String title, String intro, String imgUrl, Long cid) {
        ElementDto elementDto = new ElementDto();
        elementDto.setTitle(title);
        elementDto.setIntro(intro);
        elementDto.setImageUrls(imgUrl);
        elementDto.setWid(wid);
        elementDto.setWname(wname);
        elementDto.setSofttype(2);
        elementDto.setVersion(0);
        elementDto.setStatus(ElementStatusEnums.NORMAL.getCode());
        elementDto.setCreateId(0L);
        elementDto.setCreateName("admin");
        elementDto.setUpdateId(0L);
        elementDto.setUpdateName("admin");

        Element element = elementService.add(elementDto);

        ElementCategoryDto elementCategoryDto = new ElementCategoryDto();
        elementCategoryDto.setEid(element.getId());
        elementCategoryDto.setCid(cid);
        elementCategoryDto.setWid(wid);
        elementCategoryService.add(elementCategoryDto);
        return element.getId();
    }

    public void insertElementContent(Long eid, String title, String content) {
        ElementContentDto elementContent = new ElementContentDto();
        elementContent.setEid(eid);
        elementContent.setTitle(title);
        elementContent.setContent(content);
        elementContent.setStatus(ElementStatusEnums.NORMAL.getCode());
        elementContent.setWid(wid);
        elementContent.setWname(wname);
        elementContent.setCreateId(0L);
        elementContent.setCreateName("admin");
        elementContent.setUpdateId(0L);
        elementContent.setUpdateName("admin");
        contentService.add(elementContent);
    }

    @SneakyThrows
    public String uploadImage(String title, String imgUrlSour) {
        ImageDto image = new ImageDto();
        if (imgUrlSour != null && !"".equals(imgUrlSour)) {
            String fileName = "";
            String filePath = "c://test//" + title + ".png";
            System.out.println("Download size: " + filePath);

            long size = HttpUtil.downloadFile(imgUrlSour, FileUtil.file(filePath));
            String simpleUUID = IdUtil.simpleUUID();
            fileName = wid + "/" + simpleUUID + ".png";
            File file = new File(filePath);
            BufferedInputStream in = FileUtil.getInputStream(file);
            String md5 = DigestUtils.md5Hex(in);
            ImageVo imageVo = imageService.getInfoByMd5(md5);
            if (imageVo != null) {
                return imageVo.getFileName();
            } else {
                AliOSS.upload(fileName, file);
                // 上传并返回新文件名称
                image.setFileName("/profile/upload/" + fileName);
                image.setFileUrl(AliOSS.URL);
                image.setFileSize(file.length());
                image.setFileType("png");
                image.setMd5(md5);
                image.setOriginalFilename(file.getName());
                imageService.add(image);
            }
        }
        return image.getFileName();
    }

    public Boolean isElement(String title) {
        QueryWrapper<Element> qwElement = new QueryWrapper<>();
        qwElement.eq("title", title);
        qwElement.eq("wid", wid);
        List<Element> el = elementService.list(qwElement);
        return el.size() == 0;
    }

    public Long addCategory(String pcname, String cname) {
        QueryWrapper<Category> pqw = new QueryWrapper<>();
        pqw.eq("label", pcname);
        pqw.eq("wid", wid);
        pqw.eq("pid", 0);
        Category pc = categoryService.getOne(pqw);
        if (pc == null) {
            QueryWrapper<Category> qwCount = new QueryWrapper<>();
            qwCount.eq("wid", wid);
            qwCount.eq("pid", 0);
            Long count = categoryService.count(qwCount);
            pc = new Category();
            pc.setTier(0);
            pc.setPid(0L);
            pc.setLabel(pcname);
            pc.setPcode("0");
            pc.setCode(String.valueOf(count + 10));
            pc.setWid(wid);
            categoryService.insertCategory(pc);
        }
        if ("".equals(cname)) {
            return pc.getId();
        }
        QueryWrapper<Category> qw = new QueryWrapper<>();
        qw.eq("label", cname);
        qw.eq("wid", wid);
        qw.eq("pid", pc.getId());
        Category category = categoryService.getOne(qw);
        if (category == null) {
            QueryWrapper<Category> qwCount = new QueryWrapper<>();
            qwCount.eq("wid", wid);
            qwCount.eq("pid", pc.getId());
            Long count = categoryService.count(qwCount);
            category = new Category();
            category.setTier(1);
            category.setPid(pc.getId());
            category.setLabel(cname);
            category.setPcode(pc.getCode());
            category.setCode(pc.getCode() + (count + 10));
            category.setWid(wid);
            categoryService.insertCategory(category);
        }
        return category.getId();
    }

    public Long addStory(String name, String imagurl) {
        String fileName = uploadImage(name, imagurl);
        Story story = new Story();
        story.setName(name);
        story.setStatus(StoryStatusEnums.NORMAL.getCode());
        story.setCreateId(0L);
        story.setCreateName("admin");
        story.setCreateTime(LocalDateTime.now());

        story.setImgUrl(fileName);
        story.setCreateName("admin");
        story.setWid(wid);
        story.setCategory(TypeEnums.OTHER.getCode());
        story.setWname(wname);
        story.setAuditNumber(AuditStatusEnums.YES.getCode());
        story.setCountChapter(1);
        storyService.save(story);

        Author author = new Author();
        author.setWid(wid);
        author.setWname(wname);
        author.setCreateId(0L);
        author.setCreateName("admin");
        author.setUserId(1L);
        author.setUserName("admin");
        author.setSname(story.getName());
        author.setSid(story.getId());
        author.setTypes(ManageTypeEnums.AUTHOR.getCode());
        return story.getId();
    }

    public void updateStory(Long sid, String intro, String content) {
        Story story = new Story();
        story.setId(sid);
        story.setIntro(intro);
        story.setDescription(content);
        storyService.updateById(story);
    }

    public void updateElement(Long eid, String intro) {
        Element element = new Element();
        element.setId(eid);
        element.setIntro(intro);
        elementService.updateById(element);
    }

    public Long addReel(Long sid, String sname) {
        Chapter chapter = new Chapter();
        chapter.setWid(wid);
        chapter.setTitle("其他");
        chapter.setLevel(0);
        chapter.setStatus(ChapterStatusEnums.NORMAL.getCode());
        chapter.setPid(0L);
        chapter.setSname(sname);
        chapter.setSid(sid);
        chapter.setCreateId(0L);
        chapter.setSerialNumber(System.currentTimeMillis());

        chapter.setCreateName("admin");
        chapter.setCreateTime(LocalDateTime.now());
        chapterService.save(chapter);
        return chapter.getId();
    }

    public Long addChapter(Long sid, String sname, Long pid, String bt, String content) {
        Chapter chapter = new Chapter();
        chapter.setWid(wid);
        chapter.setTitle(bt);
        chapter.setLevel(0);
        chapter.setStatus(ChapterStatusEnums.NORMAL.getCode());
        chapter.setPid(pid);
        chapter.setPname("其他");
        chapter.setSname(sname);
        chapter.setContent(content);
        chapter.setSerialNumber(System.currentTimeMillis());
        chapter.setSid(sid);
        chapter.setCreateId(0L);
        chapter.setCreateName("admin");
        chapter.setCreateTime(LocalDateTime.now());
        chapterService.save(chapter);
        return chapter.getId();
    }
}
