package com.macro.mall.portal.service2;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.exception.Asserts;
import com.macro.mall.mapper.PapersMapper;
import com.macro.mall.model.Papers;
import com.macro.mall.model.PapersExample;
import com.macro.mall.portal.oss.Ossutil;
import com.macro.mall.portal.pojo.PaperState;
import com.macro.mall.portal.pojo.parameterPojo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Service
public class PapreServiceImp implements PaperService{
    @Autowired
    Ossutil ossutil;
    @Autowired
    PapersMapper papersMapper;
    @Override
    public CommonPage<PaperState> all(Integer pageNum, Integer pageSize) {

        Page<Object> objects = PageHelper.startPage(pageNum, pageSize);

//        UmsMember member = memberService.getCurrentMember();
        CommonPage<PaperState> pageResult = new CommonPage<>();
        PapersExample papersExample = new PapersExample();
        papersExample.createCriteria().andAuthorEqualTo("kang");
        List<Papers> papersResult = papersMapper.selectByExample(papersExample);
       PaperState temp=null;
        ArrayList<PaperState> papersList = new ArrayList<>();
        for (Papers paper:papersResult) {
             temp=  new PaperState();
             temp.setName(paper.getName());
             temp.setCheckStatus(paper.getStatus());
             temp.setUpdateTime(paper.getUpdatetime());
             papersList.add(temp);
             temp=null;

        }
//        CommonPage<PaperState> paperCommonPage = CommonPage.restPage(papersList);
        pageResult.setPageSize(objects.getPageSize());
        pageResult.setPageNum(objects.getPageNum());
        pageResult.setTotal(objects.getTotal());
        pageResult.setTotalPage(objects.getPages());
        pageResult.setList(papersList);
        System.out.println("papersList = " + papersList);
        return pageResult;
    }

    @Override
    public List<Papers> searchAll(PageInfo pageInfo) {
        return null;
    }

    @Override
    public String upload(MultipartFile file) {

        String url = ossutil.upLoad(file, "paper");
        return url;
    }

    @Override
    public Boolean submit(Papers paper) {
        PapersExample papersExample = new PapersExample();
        papersExample.createCriteria().andAuthorEqualTo(paper.getAuthor()).andNameEqualTo(paper.getName());
        List<Papers> papers = papersMapper.selectByExample(papersExample);
        if(!CollectionUtils.isEmpty(papers)){
            Asserts.fail("论文已存在，不可重复提交");
        }else {
            paper.setUpdatetime(new DateTime());
            if(paper.getStatus()==null){
                paper.setStatus("初筛中");
            }
            papersMapper.insert(paper);
        }
        return true;
    }


    @Override
    public Integer cancel() {
        return null;
    }

    @Override
    public Integer update() {
        return null;
    }

    @Override
    public String down() {
        return null;
    }

    @Override
    public Integer pay() {
        return null;
    }
}
