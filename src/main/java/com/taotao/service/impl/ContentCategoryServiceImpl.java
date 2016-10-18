package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper mapper;
	
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		example.setOrderByClause("created desc");
		List<TbContentCategory> list = mapper.selectByExample(example);
		List<EUTreeNode> listTree = new ArrayList<EUTreeNode>();
		for (TbContentCategory tbContentCategory : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			listTree.add(node);
		}
		return listTree;
	}

}
