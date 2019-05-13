package com.stys.ipfs.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * This class assists skin writers in getting parameters.
 */
public class ParamUtils {

	/**
	 * Gets a parameter as a string.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return The value of the parameter or null if the parameter was not found
	 *         or if the parameter is a zero-length string.
	 */
	public static String getParameter(HttpServletRequest request, String name) {
		return getParameter(request, name, false);
	}

	/**
	 * Gets a parameter as a string.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @param emptyStringsOK
	 *            Return the parameter values even if it is an empty string.
	 * @return The value of the parameter or null if the parameter was not
	 *         found.
	 */
	public static String getParameter(HttpServletRequest request, String name, boolean emptyStringsOK) {
		String temp = request.getParameter(name);
		if (temp != null) {
			if (temp.equals("") && !emptyStringsOK) {
				return null;
			} else {
				return temp;
			}
		} else {
			return null;
		}
	}

	public static String getParameter(HttpServletRequest request, String name, String returnValue) {
		String temp = request.getParameter(name);
		if (temp != null && !"".equals(temp))
			return temp;
		else
			return returnValue;
	}
	
	public static String getParameterOfDecoder(HttpServletRequest request, String name, String charSet, String returnValue) {
		String result = "";
		try {
			String temp = request.getParameter(name);
			if (temp != null && !"".equals(temp))
				result =  java.net.URLDecoder.decode(temp, charSet);
			else
				result =  returnValue;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Returns a list of parameters of the same name
	 * 
	 * @param request
	 *            an HttpServletRequest object.
	 * @return an array of non-null, non-blank strings of the same name. This
	 *         method will return an empty array if no parameters were found.
	 */
	public static String[] getParameters(HttpServletRequest request, String name) {
		if (name == null) {
			return new String[0];
		}
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null || paramValues.length == 0) {
			return new String[0];
		} else {
			List<String> values = new ArrayList<String>(paramValues.length);
			for (int i = 0; i < paramValues.length; i++) {
				if (paramValues[i] != null && !"".equals(paramValues[i])) {
					values.add(paramValues[i]);
				}
			}
			return (String[]) values.toArray(new String[] {});
		}
	}

	/**
	 * Gets a parameter as a boolean.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return True if the value of the parameter was "true", false otherwise.
	 */
	public static boolean getBooleanParameter(HttpServletRequest request,
			String name) {
		return getBooleanParameter(request, name, false);
	}

	/**
	 * Gets a parameter as a boolean.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return True if the value of the parameter was "true", false otherwise.
	 */
	public static boolean getBooleanParameter(HttpServletRequest request,
			String name, boolean defaultVal) {
		String temp = request.getParameter(name);
		if ("true".equals(temp) || "on".equals(temp)) {
			return true;
		} else if ("false".equals(temp) || "off".equals(temp)) {
			return false;
		} else {
			return defaultVal;
		}
	}

	/**
	 * Gets a parameter as an int.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return The int value of the parameter specified or the default value if
	 *         the parameter is not found.
	 */
	public static int getIntParameter(HttpServletRequest request, String name,
			int defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * Gets a list of int parameters.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @param defaultNum
	 *            The default value of a parameter, if the parameter can't be
	 *            converted into an int.
	 */
	public static int[] getIntParameters(HttpServletRequest request,
			String name, int defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null || paramValues.length == 0) {
			return new int[0];
		}
		int[] values = new int[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Integer.parseInt(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}
	
	
	/**
	 * Gets a parameter as an float.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return The int value of the parameter specified or the default value if
	 *         the parameter is not found.
	 */
	public static float getFloatParameter(HttpServletRequest request, String name, float defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			float num = defaultNum;
			try {
				num = Float.parseFloat(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}
	
	/**
	 * Gets a list of float parameters.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @param defaultNum
	 *            The default value of a parameter, if the parameter can't be
	 *            converted into an int.
	 */
	public static float[] getIntParameters(HttpServletRequest request, String name, float defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null || paramValues.length == 0) {
			return new float[0];
		}
		float[] values = new float[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Float.parseFloat(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}

	/**
	 * Gets a parameter as a double.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return The double value of the parameter specified or the default value
	 *         if the parameter is not found.
	 */
	public static double getDoubleParameter(HttpServletRequest request,
			String name, double defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			double num = defaultNum;
			try {
				num = Double.parseDouble(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * Gets a parameter as a long.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return The long value of the parameter specified or the default value if
	 *         the parameter is not found.
	 */
	public static long getLongParameter(HttpServletRequest request, String name, long defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * Gets a list of long parameters.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @param defaultNum
	 *            The default value of a parameter, if the parameter can't be
	 *            converted into a long.
	 */
	public static long[] getLongParameters(HttpServletRequest request,
			String name, long defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null || paramValues.length == 0) {
			return new long[0];
		}
		long[] values = new long[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Long.parseLong(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}
	
	
	public static Date getDateParameter(HttpServletRequest request, String name, String format, Date defaultDate) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			Date date = defaultDate;
			try {
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.applyPattern(format);
				
				date = sdf.parse(temp);
			} catch (Exception ignored) {
			}
			return date;
		} else {
			return defaultDate;
		}
	}
	
	public static Date[] getDateParameters(HttpServletRequest request, String name, String format, Date defaultDate) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null || paramValues.length == 0) {
			return new Date[0];
		}
		Date[] values = new Date[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.applyPattern(format);
				
				values[i] = sdf.parse(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultDate;
			}
		}
		return values;
	}

	/**
	 * Gets a parameter as a string.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return The value of the parameter or null if the parameter was not found
	 *         or if the parameter is a zero-length string.
	 */
	public static String getAttribute(HttpServletRequest request, String name) {
		return getAttribute(request, name, false);
	}

	/**
	 * Gets a parameter as a string.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @param emptyStringsOK
	 *            Return the parameter values even if it is an empty string.
	 * @return The value of the parameter or null if the parameter was not
	 *         found.
	 */
	public static String getAttribute(HttpServletRequest request, String name,
			boolean emptyStringsOK) {
		String temp = (String) request.getAttribute(name);
		if (temp != null) {
			if (temp.equals("") && !emptyStringsOK) {
				return null;
			} else {
				return temp;
			}
		} else {
			return null;
		}
	}

	/**
	 * Gets an attribute as a boolean.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the attribute you want to get
	 * @return True if the value of the attribute is "true", false otherwise.
	 */
	public static boolean getBooleanAttribute(HttpServletRequest request,
			String name) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && temp.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets an attribute as a int.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the attribute you want to get
	 * @return The int value of the attribute or the default value if the
	 *         attribute is not found or is a zero length string.
	 */
	public static int getIntAttribute(HttpServletRequest request, String name,
			int defaultNum) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * Gets an attribute as a long.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the attribute you want to get
	 * @return The long value of the attribute or the default value if the
	 *         attribute is not found or is a zero length string.
	 */
	public static long getLongAttribute(HttpServletRequest request,
			String name, long defaultNum) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}
	
	/**
	 * 判断值是否在允许的范围内，如果不是则返回传入的默认值
	 * @param range
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static String checkParam(String[] range, String value, String defaultValue){
		if(Arrays.asList(range).contains(value)){
			return value;
		}else{
			return defaultValue;
		}
	}
	
	/**
	 * Get init param from web.xml
	 * 
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String getInitParameter(HttpServletRequest request, String name, String defaultValue){
		String value = request.getSession().getServletContext().getInitParameter(name);
		if(null != value){
			return value;
		}else{
			return defaultValue;
		}
	}

}
