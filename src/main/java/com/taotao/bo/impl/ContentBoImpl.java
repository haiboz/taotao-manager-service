package com.taotao.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.bo.ContentBo;
import com.taotao.common.AbstractLavaBoImpl;
import com.taotao.mapper.TbContentMapperExt;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;

public class ContentBoImpl extends AbstractLavaBoImpl<TbContent, TbContentMapperExt, TbContentExample> implements ContentBo {
	
	
	
	@Autowired
	public void setBaseMapper(TbContentMapperExt mapper){
		setMapper(mapper);
	}

	@Override
	public List<TbContent> getContentPageList(Long categoryId, int begin, int length) {
		
		Map<String,Object> map = new HashMap<>();
		map.put("categoryId", categoryId);
		map.put("begin", begin);
		map.put("length", length);
		List<TbContent> list = mapper.getPageContentList(map);
		return list;
	}

	@Override
	public int getContentCount(Long categoryId) {
//		TbContentExample example = new TbContentExample();
//		example.createCriteria().andCategoryIdEqualTo(categoryId);
		Map<String,Object> map = new HashMap<>();
		map.put("categoryId", categoryId);
		int count = mapper.queryContentCount(map);
		return count;
	}

}
