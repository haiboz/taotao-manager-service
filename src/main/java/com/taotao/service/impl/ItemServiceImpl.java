package com.taotao.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.bo.ItemParamBo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.IDUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamVo;
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
	@Autowired
	public TbItemParamMapper itemParamMapper;
	@Autowired
	public TbItemCatMapper itemCatMapper;
	@Autowired
	public ItemParamBo itemParamBoImpl;
	@Autowired
	public TbItemParamItemMapper itemParamItemMapper;
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
		example.setOrderByClause("created desc");
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
	public TaotaoResult createItem(TbItem item,String desc,String itemParam) throws Exception {
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
			Long itemId = item.getId();
			TaotaoResult result = insertItemDesc(itemId,desc);
			if(result.getStatus() != 200){
				throw new Exception();
			}
			//添加规格参数
			result = this.insertItemParamItem(itemId, itemParam);
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
	@Override
	public EUDataGridResult getItemParamList(int pageNum, int length) {
		//方法1 此方法暂时无法获得类目的中文名称
//		TbItemParamExample example = new TbItemParamExample();
//		PageHelper.startPage(page, rows);
//		List<TbItemParamVo> voList = new ArrayList<TbItemParamVo>();
//		example.createCriteria();
//		//要使用带blob类型的查询  否则规格参数查询不出来
//		List<TbItemParamVo> list = itemParamMapper.selectByExampleWithBLOBs(example);
//		PageInfo<TbItemParamVo> pageInfo = new PageInfo<>(list);
//		long total = pageInfo.getTotal();
		//方法2
		//计算起始位置
		int begin = (pageNum-1)*length;
		List<TbItemParamVo> list = itemParamBoImpl.queryPageList(begin, length);
		int total = itemParamBoImpl.queryListCount();
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		result.setTotal(total);
		return result;
	}
	
	/**
	 * 保存规格参数
	 * @param itemId
	 * @param itemPatam
	 * @return
	 */
	private TaotaoResult insertItemParamItem(Long itemId ,String itemParam){
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		itemParamItemMapper.insert(itemParamItem);
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult deleteItems(String ids) {
		String[] idsStr = ids.split(",");
		for (String str : idsStr) {
			itemMapper.deleteByPrimaryKey(Long.parseLong(str));
		}
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult reshelfItem(String ids) {
		String[] idsStr = ids.split(",");
		for (String str : idsStr) {
			TbItem tbItem = itemMapper.selectByPrimaryKey(Long.parseLong(str));
			tbItem.setStatus((byte)1);//状态1 上架
			itemMapper.updateByPrimaryKey(tbItem);
		}
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult instockItem(String ids) {
		String[] idsStr = ids.split(",");
		for (String str : idsStr) {
			TbItem tbItem = itemMapper.selectByPrimaryKey(Long.parseLong(str));
			tbItem.setStatus((byte)2);//状态2 下架
			itemMapper.updateByPrimaryKey(tbItem);
		}
		return TaotaoResult.ok();
	}
}
