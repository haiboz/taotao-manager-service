package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
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
}
