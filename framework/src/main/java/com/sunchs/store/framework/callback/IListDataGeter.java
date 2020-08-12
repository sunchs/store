package com.sunchs.store.framework.callback;

import java.util.List;

/**
 * 列表对象集合获取器
 */
public interface IListDataGeter<T> {

	/**
	 * 获取列表数据接口
	 */
	List<T> getList();
}
