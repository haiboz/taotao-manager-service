package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.ItemCat;
import com.taotao.common.pojo.ItemCatResult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper; 
	@Override
	public List<TbItemCat> getItemCatList(Long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		return list;
	}
	
	@Override
	public ItemCatResult queryAllCotegory(){
		ItemCatResult result = new ItemCatResult();
		result.setData(getItemCatList(0l));
		return result;
	}
	
	
	
	private List<?> getItemCatList(long parentid) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		//查询parentid为0的分类信息
		criteria.andParentIdEqualTo(parentid);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List dataList = new ArrayList();
		for (TbItemCat tbItemCat : list) {
			//判断是否为父节点
			if (tbItemCat.getIsParent()) {
				ItemCat itemCat = new ItemCat();
				itemCat.setUrl("/category/" + tbItemCat.getId() + ".html");
				itemCat.setName(tbItemCat.getName());
				//递归调用
				itemCat.setItem(getItemCatList(tbItemCat.getId()));
				//添加到列表
				dataList.add(itemCat);
			} else {
				String catItem = "/item/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
				dataList.add(catItem);
			}
		}
		return dataList;
	}

}
