package com.taotao.bo;

import java.util.List;

import com.taotao.common.LavaBo;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;

public interface ContentBo extends LavaBo<TbContent, TbContentExample> {
	/**
	 * 分页获取某一类内容列表
	 * @param categoryId
	 * @param begin
	 * @param length
	 * @return
	 */
	public List<TbContent> getContentPageList(Long categoryId,int begin, int length);
	/**
	 * 获取记录总数
	 * @param categoryId
	 * @return
	 */
	public int getContentCount(Long categoryId);
}
