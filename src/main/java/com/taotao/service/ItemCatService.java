package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.ItemCatResult;
import com.taotao.pojo.TbItemCat;

public interface ItemCatService {
	/**
	 * 查询叶子节点
	 * @param parentId
	 * @return
	 */
	public List<TbItemCat> getItemCatList(Long parentId);
	
	
	/**
	 * 查询所有的策略
	 * @return
	 */
	public ItemCatResult queryAllCotegory();
	
}
