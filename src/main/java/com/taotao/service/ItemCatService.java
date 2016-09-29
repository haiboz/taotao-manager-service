package com.taotao.service;

import java.util.List;

import com.taotao.pojo.TbItemCat;

public interface ItemCatService {
	/**
	 * 查询叶子节点
	 * @param parentId
	 * @return
	 */
	public List<TbItemCat> getItemCatList(Long parentId);
	
}
