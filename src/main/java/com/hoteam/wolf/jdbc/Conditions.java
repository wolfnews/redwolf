package com.hoteam.wolf.jdbc;

import java.util.Map;
import java.util.Set;

/**常用查询条件
 * @author mingwei.dmw
 *
 */
public class Conditions {
	public static ConditionDef loginMCondition = new ConditionDef(new Object[][] {{"MOBILE = :mobile" }});
	public static ConditionDef loginCondition = new ConditionDef(new Object[][] { { "USERNAME = :username" },{"PASSWORD = :password" }});
	public static ConditionDef loadCondition = new ConditionDef(new Object[][] { { "ID = :id" } });
	public static ConditionDef loadByNameCondition = new ConditionDef(new Object[][] { { "NAME = :name" } });
	public static ConditionDef loadByUserCondition = new ConditionDef(new Object[][]{{"USER_ID = :user"}});
	
	public static ConditionDef simpleCondition(String pref,String paramName){
		return new ConditionDef(new Object[][]{{pref+" = :"+paramName}});
	}
	
	public static ConditionDef complexCondition(Map<String,String> conditionMap) throws Exception{
		if(null == conditionMap||conditionMap.isEmpty()){
			throw new Exception("Empty condition Map!!!");
		}
		Object[][] defineArr = new Object[conditionMap.keySet().size()][];
		Set<String> keys = conditionMap.keySet();
		int i=0;
		for(String key : keys){
			Object[] item = {key+" = :"+conditionMap.get(key)};
			defineArr[i]=item;
			i++;
		}
		ConditionDef conditionDef = new ConditionDef(defineArr);
		return conditionDef;
	}
	
}
