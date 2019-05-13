package com.stys.ipfs.web;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.Article;
import com.stys.ipfs.service.IArticleService;

/**
 * <p>
 * 文章信息管理 前端控制器
 * </p>
 *
 * @author dp
 * @since 2018-11-14
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Reference(version = "1.0.0", check = false)
    private IArticleService iarticleService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("article:view")
    public @ResponseBody
        ResultInfo<List<Article>> listData(Article article, Integer page, Integer limit){
        EntityWrapper<Article> wrapper = new EntityWrapper<>(article);
        if (article != null && article.getTitle() != null) {
			wrapper.like("title", article.getTitle());
			article.setTitle(null);
		}
        Page<Article> pageObj = iarticleService.selectPage(new Page<Article>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("article:add")
    public @ResponseBody
        ResultInfo<Boolean> add(Article article,String startTime,String endTime){
        boolean b = iarticleService.insert(article);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("article:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = iarticleService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("article:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(Article article){
        boolean b = iarticleService.updateById(article);
        return new ResultInfo<>(b);
    }
    
   

}

