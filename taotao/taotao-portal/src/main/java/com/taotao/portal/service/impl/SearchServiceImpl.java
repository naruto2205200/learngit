package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.taotao.portal.pojo.SearchResult;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCE_BASE_URL}")
	private String SEARCE_BASE_URL;
	@Override
	public SearchResult search(String queryString, int page) {
		//查询参数
		Map<String,String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page+"");
		try {
			//调用taotao-search服务
			String json = HttpClientUtil.doGet(SEARCE_BASE_URL,param);
			//把字符串转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
			if(taotaoResult.getStatus()==200){
				SearchResult result = (SearchResult)taotaoResult.getData();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
