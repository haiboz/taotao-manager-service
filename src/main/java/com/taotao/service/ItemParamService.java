package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamVo;

public interface ItemParamService {
	/**
	 * 根据catId查询模版
	 * @param catId
	 * @return
	 */
	List<TbItemParamVo> queryParamByCatId(long catId);
	
	/**
	 * 新增规格模版
	 * @param itemParam
	 * @return
	 */
	public TaotaoResult insertItemParam(TbItemParam itemParam);

	/**
	 * 批量删除规格模版
	 * @param ids
	 * @return
	 */
	TaotaoResult deleteParam(String ids);
}
