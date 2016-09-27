package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;

/**
 * @author 浮生若梦
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	public TbItemMapper itemMapper;
	@Override
	public TbItem getItemById(long id) {
//		TbItem item = itemMapper.selectByPrimaryKey(id);
		TbItemExample example = new TbItemExample();
		example.createCriteria().andIdEqualTo(id);
		example.setOrderByClause("created desc");
		 List<TbItem> list = itemMapper.selectByExample(example);
		 if(!CollectionUtils.isEmpty(list)){
			return list.get(0); 
		 }
		return null;
	}
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);
		example.createCriteria();
		List<TbItem> list = itemMapper.selectByExample(example);
		//取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EUDataGridResult result = new EUDataGridResult();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}
}
