package com.taotao.service;

import java.util.List;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

/**
 * 内容分类
 * @author 浮生若梦
 * 2016年10月18日 下午4:59:19
 */
public interface ContentCategoryService {
	/**
	 * 获取内容分类列表
	 * @param parentId
	 * @return
	 */
	public List<EUTreeNode> getCategoryList(Long parentId);
	
	/**
	 * 新增新的内容分类
	 * @param parentId
	 * @param name
	 * @return
	 */
	public TaotaoResult insertContentCategory(Long parentId,String name);

	/**
	 * 修改内容分类
	 * @param id
	 * @param name
	 * @return
	 */
	public TaotaoResult updateContentCategory(Long id, String name);

	/**
	 * 删除内容分类
	 * @param parentId
	 * @param id
	 * @return
	 */
	public TaotaoResult deleteContentCategory(Long parentId, Long Long);

}
