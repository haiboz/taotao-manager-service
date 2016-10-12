package com.taotao.bo;

import java.util.List;

import com.taotao.common.LavaBo;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamVo;

public interface ItemParamBo extends LavaBo<TbItemParam, TbItemParamExample> {
	public void getTest();
	
	/**
	 * 分页获取规格数据
	 * @param begin
	 * @param length
	 * @return
	 */
	public List<TbItemParamVo> queryPageList(int begin,int length);
	/**
	 * 获取规格参数总数
	 * @return
	 */
	public int queryListCount();
}
