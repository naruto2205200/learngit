package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public CatResult getItemCatList() {
		CatResult catResult = new CatResult();
		catResult.setData(getCatList(0));
		return catResult;
	}

	/**
	 * 查询分类类表
	 * 
	 * @param parendId
	 * @return
	 */
	private List<?> getCatList(long parendId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parendId);
		// 执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		// 返回值List
		List resultList = new ArrayList<>();
		// 向list中添加节点
		int count = 0;
		for (TbItemCat tbItemCat : list) {
			// 判断是否为叶子节点
			if (tbItemCat.getIsParent()) {

				CatNode catNode = new CatNode();
				if (parendId == 0) {
					catNode.setName(
							"<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/" + tbItemCat.getId() + ".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				resultList.add(catNode);
				count++;
				//第一级节点直加载前14个
				if (parendId == 0 && count>=14) {
					break;
				}
			} else {//叶子节点（添加字符串）
				resultList.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());	
			}
		}
		return resultList;
	}
}
