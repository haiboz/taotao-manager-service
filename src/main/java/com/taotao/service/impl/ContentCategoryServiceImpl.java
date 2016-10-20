package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper mapper;
	
	@Override
	public List<EUTreeNode> getCategoryList(Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		example.setOrderByClause("updated desc");
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
 
	@Override
	public TaotaoResult insertContentCategory(Long parentId, String name) {
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		mapper.insert(contentCategory);
		//查看父节点的isparent是否为true  如果不是 则置为true
		
		TbContentCategory parentTb = mapper.selectByPrimaryKey(parentId);
		if(!parentTb.getIsParent()){
			parentTb.setIsParent(true);
			mapper.updateByPrimaryKey(parentTb);
		}
		//返回结果
		return TaotaoResult.ok(contentCategory);
		
	}

	@Override
	public TaotaoResult updateContentCategory(Long id, String name) {
		TbContentCategory record = mapper.selectByPrimaryKey(id);
		record.setName(name);
		mapper.updateByPrimaryKey(record);
		return TaotaoResult.ok(record);
	}

	@Override
	public TaotaoResult deleteContentCategory(Long parentId, Long id) {
		if(parentId == null){
			parentId = mapper.selectByPrimaryKey(id).getParentId();
		}
		//删除记录
		mapper.deleteByPrimaryKey(id);
		//如果父类已没有子类 则将isParent置为false
		TbContentCategoryExample example = new TbContentCategoryExample();
		
		example.createCriteria().andParentIdEqualTo(parentId);
		List<TbContentCategory> list = mapper.selectByExample(example);
		if(CollectionUtils.isEmpty(list)){
			TbContentCategory record = mapper.selectByPrimaryKey(parentId);
			record.setIsParent(false);
			mapper.updateByPrimaryKey(record);
		}
		return TaotaoResult.ok();
	}

}
