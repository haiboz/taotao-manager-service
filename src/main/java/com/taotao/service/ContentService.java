package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	/**
	 * 分页查询内容列表
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	public EUDataGridResult getContentList(Long categoryId,int page ,int rows);
	
	/**
	 * 保存内容信息
	 * @param tbContent
	 * @return
	 */
	public TaotaoResult saveContent(TbContent tbContent);
}
