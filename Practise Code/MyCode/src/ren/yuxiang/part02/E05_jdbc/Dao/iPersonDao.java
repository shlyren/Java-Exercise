package ren.yuxiang.part02.E05_jdbc.Dao;

import java.util.ArrayList;

// person 增删改查 接口
public interface iPersonDao {

	/**
	 * 保存一条用户信息
	 * @param person 用户对象
	 */
	public void save(Person person);
	
	/**
	 * 删除一条用户信息
	 * @param personId 用户对象Id
	 */
	public void delete(String personId);
	
	/**
	 * 更新一条用户信息
	 * @param person 用户对象
	 */
	public void update(Person person);
	
	/**
	 * 查询用户信息
	 * @param personId 用户id
	 * @return 用户信息 
	 */
	public Person get(String personId);
	
	/**
	 * 查询所有用户
	 * @return 所有用户集合
	 */
	public ArrayList<Person> getAll();
}
