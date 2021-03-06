package com.taotao.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.bo.ItemParamBo;
import com.taotao.common.AbstractLavaBoImpl;
import com.taotao.mapper.TbItemParamMapperExt;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamVo;

public class ItemParamBoImpl extends AbstractLavaBoImpl<TbItemParam, TbItemParamMapperExt, TbItemParamExample> implements ItemParamBo{

	@Override
	public void getTest() {
		TbItemParamExample example = new TbItemParamExample();
		mapper.countByExample(example);
	}
	@Autowired
	public void setBaseMapper(TbItemParamMapperExt mapper){
		setMapper(mapper);
	}

	@Override
	public List<TbItemParamVo> queryPageList(int begin,int length) {
		Map<String,Object> map = new HashMap<>();
		map.put("begin", begin);
		map.put("length", length);
		List<TbItemParamVo> list = mapper.queryPageList(map);
		return list;
	}
	@Override
	public int queryListCount() {
		int count = mapper.queryListCount();
		return count;
	}
	

}
