package com.offcn.page.service;

/**
 * @author wcz
 * @create 2022-05-10 11:16
 */
/**
 * 商品详细页接口
 * @author Administrator
 *
 */
public interface ItemPageService {
    /**
     * 生成商品详细页
     * @param goodsId
     */
    public boolean genItemHtml(Long goodsId);
    /**
     * 删除商品详细页
     * @param
     * @return
     */
    public boolean deleteItemHtml(Long[] goodsIds);
}

