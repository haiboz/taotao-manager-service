package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long id);
	/**
	 * 分页查询
	 * @param page 页码
	 * @param rows 每页条数
	 * @return
	 */
	EUDataGridResult getItemList(int page,int rows);
	
	/**
	 * 插入商品信息
	 * @param item 商品信息
	 * @param desc 描述
	 * @param itemParam 规格参数
	 * @return
	 */
	TaotaoResult createItem(TbItem item,String desc,String itemParam) throws Exception ;
	
	
	/**
	 * 分页查询规格模版
	 * @param pageNum 页码
	 * @param length 每页长度
	 * @return
	 */
	EUDataGridResult getItemParamList(int pageNum,int length);
	/**
	 * 批量删除商品
	 * @param ids
	 * @return
	 */
	TaotaoResult deleteItems(String ids);
	/**
	 * 批量上架商品
	 * @param ids
	 * @return
	 */
	TaotaoResult reshelfItem(String ids);
	/**
	 * 批量下架商品
	 * @param ids
	 * @return
	 */
	TaotaoResult instockItem(String ids);
}
