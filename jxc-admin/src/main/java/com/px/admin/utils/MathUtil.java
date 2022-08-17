package com.px.admin.utils;

/**
 * 数学工具类
 *
 */
public class MathUtil {

	/**
	 * 格式化数字 保留两位
	 * @param n
	 * @return
	 */
	public static float format2Bit(float n){
		return (float)(Math.round(n*100))/100;
	}
}
