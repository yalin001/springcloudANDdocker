package com.pd.service;

import java.util.List;

import com.pd.pojo.Item;

public interface SearchService {
	List<Item> findItemByKey(String key) throws Exception;
}