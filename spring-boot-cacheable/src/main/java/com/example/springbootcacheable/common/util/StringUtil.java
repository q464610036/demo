package com.example.springbootcacheable.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 检查字符串是否为空
     * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank("bob")     = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is
     *  not empty and not null and not whitespace
     * @since 2.0
     */
    public static boolean isNotBlank(String str) {
    	boolean isBlank = true;
    	if (str == null || "".equals(str.trim())) {
    		isBlank = false;
		}
        return isBlank;
    }
    
    

	public static boolean isEmpty(String str) { 
		boolean empty = false;
		if(str == null || str.trim().length() <=0){
			empty = true;
		}
		return empty;
	}
	/**
	 * 获取一个字符串的数字部分
	 * @param str
	 * @return
	 */
	public static Integer getStrInt(String str){
		Integer n = 0;
		if(!isEmpty(str)){
			String regEx="[^0-9]";   
			Pattern p = Pattern.compile(regEx);   
			Matcher m = p.matcher(str);  
			try{
				n = Integer.parseInt(m.replaceAll("").trim());
			}catch (Exception e) {
			}
		}
		return n;
	}
	
	/**
	 * 字符串是否是正整数
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
	   Pattern pattern = Pattern.compile("[0-9]*"); 
	   Matcher isNum = pattern.matcher(str);
	   if( !isNum.matches() ){
	       return false; 
	   } 
	   return true; 
	}
	
}
