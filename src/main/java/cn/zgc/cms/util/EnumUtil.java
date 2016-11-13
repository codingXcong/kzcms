package cn.zgc.cms.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 枚举类型的工具类
 * @author gczhang
 */
@SuppressWarnings("rawtypes")
public class EnumUtil {
	
	/**
	 * 将枚举类型的值转换成相应的名称字符串列表
	 * @param clz
	 * @return
	 */
	public static List<String> enum2Name(Class<? extends Enum> clz) {
		if(!clz.isEnum()) return null;
		Enum[] enums = clz.getEnumConstants();
		List<String> list = new ArrayList<String>();
		for(Enum en:enums){
			list.add(en.name());
		}
		return list;
	}
	
	/**
	 * 将枚举中的值转换为序号和名称的map
	 * @param clz
	 * @return
	 */
	public static Map<Integer,String> enum2BasicMap(Class<? extends Enum> clz) {
		if(!clz.isEnum()) return null;
		Enum[] enums = clz.getEnumConstants();
		Map<Integer,String> map = new HashMap<Integer,String>();
		for(Enum en:enums){
			map.put(en.ordinal(), en.name());
		}
		return map;
	}
	
	/**
	 * 将枚举中的值的某个属性转换为字符串列表
	 * @param clz
	 * @param propName
	 * @return
	 */
	public static List<String> enumProp2List(Class<? extends Enum> clz,String propName) {
		if(!clz.isEnum()) return null;
		try {
			Enum[] enums = clz.getEnumConstants();
			List<String> rels = new ArrayList<String>();
			for(Enum en:enums) {
				rels.add((String) PropertyUtils.getProperty(en, propName));
			}
			return rels;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将枚举中的值的某个属性转换为名称和字符串map
	 * @param clz
	 * @param propName
	 * @return
	 */
	public static Map<String,String> enumProp2Map(Class<? extends Enum> clz,String propName) {
		if(!clz.isEnum()) return null;
		try {
			Enum[] enums = clz.getEnumConstants();
			Map<String,String> map = new HashMap<String,String>();
			for(Enum en:enums) {
				map.put(en.name(),(String) PropertyUtils.getProperty(en, propName));
			}
			return map;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
