package com.pd.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.pd.pojo.Item;
import com.pd.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	
	/*
	 * SolrClient实例是在 SolrAutoConfiguration 类中创建的
	 * 
	 * SolrAutoConfiguration添加了@Configuration注解,
	 * 是spring boot自动配置类,其中的solrClient()方法中创建了SolrClient实例
	 */
	@Autowired
	private SolrClient solrClient;

	@Override
	public List<Item> findItemByKey(String key) throws Exception {
		//封装查询的关键词
		//也可以封装其他的查询参数,比如指定字段,facet设置等
		SolrQuery query = new SolrQuery(key);
		//查询前多少条数据
		query.setStart(0);
		query.setRows(20);
		
		//执行查询并得到查询结果
		QueryResponse qr = solrClient.query(query);
		//把查询结果转成一组商品实例
		List<Item> beans = qr.getBeans(Item.class);
		return beans;
	}

}