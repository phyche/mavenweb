/*
* 
 * 
 * 
 *//*

package com.springmvc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.com.springmvc.util.Date;
import java.com.springmvc.util.Map;
import java.com.springmvc.util.Map.Entry;

import com.sun.javafx.css.converters.EnumConverter;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.com.springmvc.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

*/
/**
 * Utils - Freemarker
 * 
 * 
 * @version 3.0
 *//*

@SuppressWarnings("unchecked")
public final class FreemarkerUtils {
	
	//private static final Logger logger=Logger.getLogger(FreemarkerUtils.class);

	*/
/** ConvertUtilsBean *//*

	private static final ConvertUtilsBean convertUtils = new ConvertUtilsBean() {
        @Override
        public String convert(Object value) {
            if (value != null) {
                Class<?> type = value.getClass();
                if (type.isEnum() && super.lookup(type) == null) {
                    super.register(new EnumConverter(type), type);
                } else if (type.isArray() && type.getComponentType().isEnum()) {
                    if (super.lookup(type) == null) {
                        ArrayConverter arrayConverter = new ArrayConverter(type, new EnumConverter(type.getComponentType()), 0);
                        arrayConverter.setOnlyFirstToString(false);
                        super.register(arrayConverter, type);
                    }
                    Converter converter = super.lookup(type);
                    return ((String) converter.convert(String.class, value));
                }
            }
            return super.convert(value);
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Object convert(String value, Class clazz) {
            if (clazz.isEnum() && super.lookup(clazz) == null) {
                super.register(new EnumConverter(clazz), clazz);
            }
            return super.convert(value, clazz);
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Object convert(String[] values, Class clazz) {
            if (clazz.isArray() && clazz.getComponentType().isEnum() && super.lookup(clazz.getComponentType()) == null) {
                super.register(new EnumConverter(clazz.getComponentType()), clazz.getComponentType());
            }
            return super.convert(values, clazz);
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Object convert(Object value, Class targetType) {
            if (super.lookup(targetType) == null) {
                if (targetType.isEnum()) {
                    super.register(new EnumConverter(targetType), targetType);
                } else if (targetType.isArray() && targetType.getComponentType().isEnum()) {
                    ArrayConverter arrayConverter = new ArrayConverter(targetType, new EnumConverter(targetType.getComponentType()), 0);
                    arrayConverter.setOnlyFirstToString(false);
                    super.register(arrayConverter, targetType);
                }
            }
            return super.convert(value, targetType);
        }
    };

	static {

        DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(CommonAttributes.DATE_PATTERNS);
		convertUtils.register(dateConverter, Date.class);
	}

	*/
/**
	 * 不可实例化
	 *//*

	private FreemarkerUtils() {
	}

	*/
/**
	 * 解析字符串模板
	 * 
	 * @param template
	 *            字符串模板
	 * @param model
	 *            数据
	 * @return 解析后内容
	 *//*

	public static String process(String template, Map<String, ?> model) throws IOException {
        Configuration configuration = null;
		ApplicationContext applicationContext = SpringUtils.getApplicationContext();
		if (applicationContext != null) {
			FreeMarkerConfigurer freeMarkerConfigurer = SpringUtils.getBean("freeMarkerConfigurer", FreeMarkerConfigurer.class);
			if (freeMarkerConfigurer != null) {
				configuration = freeMarkerConfigurer.getConfiguration();
			}
		}
		return process(template, model, configuration);
	}

	*/
/**
	 * 解析字符串模板
	 * 
	 * @param template
	 *            字符串模板
	 * @param model
	 *            数据
	 * @param configuration
	 *            配置
	 * @return 解析后内容
	 *//*

	public static String process(String template, Map<String, ?> model, Configuration configuration) throws IOException, TemplateException {
		if (template == null) {
			return null;
		}
		if (configuration == null) {
			configuration = new Configuration();
		}
		StringWriter out = new StringWriter();
		new Template("template", new StringReader(template), configuration).process(model, out);
		return out.toString();
	}

	*/
/**
	 * 获取参数
	 * 
	 * @param name
	 *            名称
	 * @param type
	 *            类型
	 * @param params
	 *            参数
	 * @return 参数,若不存在则返回null
	 *//*

	public static <T> T getParameter(String name, Class<T> type, Map<String, TemplateModel> params) throws TemplateModelException {
		Assert.hasText(name);
		Assert.notNull(type);
		Assert.notNull(params);
		TemplateModel templateModel = params.get(name);
		if (templateModel == null) {
			return null;
		}
		Object value = DeepUnwrap.unwrap(templateModel);
		return (T) convertUtils.convert(value, type);
	}

	*/
/**
	 * 获取变量
	 * 
	 * @param name
	 *            名称
	 * @param env
	 *            Environment
	 * @return 变量
	 *//*

	public static TemplateModel getVariable(String name, Environment env)  {
		Assert.hasText(name);
		Assert.notNull(env);
		return env.getVariable(name);
	}

	*/
/**
	 * 设置变量
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            变量值
	 * @param env
	 *            Environment
	 *//*

	public static void setVariable(String name, Object value, Environment env) throws TemplateException {
		Assert.hasText(name);
		Assert.notNull(env);
		if (value instanceof TemplateModel) {
			env.setVariable(name, (TemplateModel) value);
		} else {
			env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
		}
	}

	*/
/**
	 * 设置变量
	 * 
	 * @param variables
	 *            变量
	 * @param env
	 *            Environment
	 *//*

	public static void setVariables(Map<String, Object> variables, Environment env) throws TemplateException {
		Assert.notNull(variables);
		Assert.notNull(env);
		for (Entry<String, Object> entry : variables.entrySet()) {
			String name = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof TemplateModel) {
				env.setVariable(name, (TemplateModel) value);
			} else {
				env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
			}
		}
	}
	
	
	*/
/**
	  * 方法描述：将模板文件转换成字符串
	  * @param: template 模板文件
	  * @param: rootMap 动态数据
	  * @return: String
	  * @version: 1.0
	  * @author: 李金魁 lijinkui@f-road.com.cn
	  * @time: 2013-10-15 下午3:41:36
	  *//*

	public static String process(File template,Map<String, Object> rootMap){
		if(template==null||!template.exists()){
			logger.info("文件不存在");
			return null;
		}
		try {
			FileInputStream in=new FileInputStream(template);
			BufferedReader reader=new BufferedReader(new InputStreamReader(in,"UTF-8"));
			Template tmp=new Template("template", reader,null);
			StringWriter out=new StringWriter();
			tmp.process(rootMap, out);
			out.close();
			reader.close();
			in.close();
			return out.toString();
		} catch (IOException e) {
			logger.error("模板读取出现异常",e);
		}catch (Exception e) {
			logger.error("模板读取出现异常",e);
		}
		return null;
	}

}*/
