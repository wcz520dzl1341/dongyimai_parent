package com.offcn.solrutil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.github.promeg.pinyinhelper.Pinyin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Component;
import com.offcn.mapper.TbItemMapper;
import com.offcn.pojo.TbItemExample.Criteria;
import com.offcn.pojo.TbItem;
import com.offcn.pojo.TbItemExample;

import javax.swing.*;

@Component
public class SolrUtil {

    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private TbItemMapper itemMapper;

    /**
     * 导入商品数据
     */
    public void importItemData() {
        TbItemExample example = new TbItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo("1");// 已审核
        List<TbItem> itemList = itemMapper.selectByExample(example);
        System.out.println("===商品列表===");
        for (TbItem item : itemList) {
            System.out.println(item.getTitle());
            Map<String,String> map = JSON.parseObject(item.getSpec(), Map.class);
            HashMap<String, String> pingyin = new HashMap<>();
            //遍历map，替换key从汉字变为拼音
            for(String key :map.keySet()){
               pingyin.put(Pinyin.toPinyin(key, "").toLowerCase(), map.get(key));
            }
            item.setSpecMap(pingyin);
        }
        solrTemplate.saveBeans(itemList);
        solrTemplate.commit();
        System.out.println("===结束===");
    }
    public void testDeleteAll(){
        Query query=new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }

    public static void main(String[] args) {

        ApplicationContext context=new ClassPathXmlApplicationContext("classpath*:spring/*.xml");
        SolrUtil solrUtil = (SolrUtil) context.getBean("solrUtil");
        solrUtil.testDeleteAll();
        solrUtil.importItemData();
    }

}