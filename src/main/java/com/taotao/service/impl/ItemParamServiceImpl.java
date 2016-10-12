package com.taotao.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamVo;
import com.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	
	@Autowired
	private TbItemParamMapper mapper;
	@Override
	public List<TbItemParamVo> queryParamByCatId(long catId) {
		TbItemParamExample example = new TbItemParamExample();
		example.createCriteria().andItemCatIdEqualTo(catId);
		List<TbItemParamVo> list = mapper.selectByExampleWithBLOBs(example);
		return list;
	}
	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		//不全itemParam
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		mapper.insert(itemParam);
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult deleteParam(String ids) {
		String[] strIds = ids.split(",");
		for (String str : strIds) {
			mapper.deleteByPrimaryKey(Long.parseLong(str));
		}
		return TaotaoResult.ok();
	}

}
