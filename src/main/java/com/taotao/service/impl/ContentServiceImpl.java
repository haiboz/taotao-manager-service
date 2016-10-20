package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.bo.ContentBo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentBo contentBoImpl;  
	@Autowired
	private TbContentMapper mapper;
	
	
	@Override
	public EUDataGridResult getContentList(Long categoryId,int page,int rows) {
//		int total = contentBoImpl.getContentCount(categoryId);
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		int total = mapper.countByExample(example);
		int begin = (page-1)*rows;
		List<TbContent> list = contentBoImpl.getContentPageList(categoryId, begin, rows);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

	@Override
	public TaotaoResult saveContent(TbContent tbContent) {
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		mapper.insert(tbContent);
		return TaotaoResult.ok();
	}
	
	

}
