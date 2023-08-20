package com.seleniumexpress.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("Dispatcher Servlet Initialized");
		Class[] myConfigs= {MyAppConfig.class};
		return myConfigs;
	}

	@Override
	protected String[] getServletMappings() {
		String[] mappings= {"/"};
		return mappings;
	}

}
