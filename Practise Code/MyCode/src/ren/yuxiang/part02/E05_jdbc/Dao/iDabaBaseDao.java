package ren.yuxiang.part02.E05_jdbc.Dao;

import java.util.List;

// 对象 增删改查 接口
public interface iDabaBaseDao<T> {

	/**
	 * 保存一条对象信息
	 * @param obj 对象信息
	 */
	public void save(T obj);
	
	/**
	 * 删除一条对象
	 * @param objId 对象Id
	 */
	public void delete(String objId);
	
	/**
	 * 更新一条对象信息
	 * @param person 用户对象
	 */
	public void update(T obj);
	
	/**
	 * 查询用户信息
	 * @param objId 对象id
	 * @return 用户信息 
	 */
	public T get(String objId);
	
	/**
	 * 查询所有用户
	 * @return 所有用户集合
	 */
	public List<T> getAll();
}
