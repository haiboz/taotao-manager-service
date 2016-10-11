package com.taotao.bo;

import java.util.List;

import com.taotao.common.LavaBo;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamVo;

public interface ItemParamBo extends LavaBo<TbItemParam, TbItemParamExample> {
	public void getTest();
	
	public List<TbItemParamVo> queryPageList(int begin,int length);
}
