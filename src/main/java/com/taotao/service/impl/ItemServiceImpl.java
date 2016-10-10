package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
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
	@Autowired
	public TbItemDescMapper itemDescMapper;
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
	
	@Override
	public TaotaoResult createItem(TbItem item,String desc) throws Exception {
		//item 补全
		item.setId(IDUtils.genItemId());
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//插入商品信息
		int count = itemMapper.insert(item);
		if(count != 1){
			return TaotaoResult.error(400, "商品插入失败");
		}else{
			//插入商品描述
			TaotaoResult result = insertItemDesc(item.getId(),desc);
			if(result.getStatus() != 200){
				throw new Exception();
			}
			return result;
		}
	}
	/**
	 * 插入商品描述信息
	 * @param itemId
	 * @param desc
	 * @return
	 */
	private TaotaoResult insertItemDesc(Long itemId,String desc){
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		int count = itemDescMapper.insert(itemDesc);
		if(count != 1){
			return TaotaoResult.error(400, "商品描述插入失败");
		}else{
			return TaotaoResult.ok();
		}
	}
}
