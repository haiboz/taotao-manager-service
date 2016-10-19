package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.bo.ContentBo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentBo contentBoImpl;  
	
	
	@Override
	public EUDataGridResult getContentList(Long categoryId,int page,int rows) {
		int total = contentBoImpl.getContentCount(categoryId);
		int begin = (page-1)*rows;
		List<TbContent> list = contentBoImpl.getContentPageList(categoryId, begin, rows);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

}
