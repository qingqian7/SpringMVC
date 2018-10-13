import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import Interceptor.DemoInterceptor;

@Configuration
@EnableWebMvc //开启对springmvc的支持
@EnableScheduling  //开启对异步调度的支持
@ComponentScan("controller,service") //针对发布后的项目的目录 看工作空间中的发布路径
public class MyMvcConfig extends WebMvcConfigurerAdapter{  //继承该adapter才能对springmvc进行配置
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/"); //运行时diamagnetic会自动将页面编译到WEB-INF/classes
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		System.out.println("debug1....");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		//addResourceLocation指的是文件存放位置   addResourceHandler指的是对外开放的访问路径
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
	}
	
	@Bean
	public DemoInterceptor demoInterceptor() {
		return new DemoInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(demoInterceptor());
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		System.out.println("debug4..........");
		registry.addViewController("/index").setViewName("/index");  // 添加用于控制页面跳转的view controller  而不用像hellocontroller那样写控制跳转的controller了  简化配置
		registry.addViewController("/toUpload").setViewName("/upload"); //添加文件上传
		registry.addViewController("/sse").setViewName("/sse"); //添加服务器端推送（基于Server Send Event）
		registry.addViewController("/async").setViewName("/async"); // 添加服务器端推送（基于servlet3+的异步方法新特性）
	}
	
	//控制文件上传  跟web.xml或者struts.xml里配置是一个意思
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000);
		return multipartResolver; //刚才这儿误写成 return multipartResolver()  带有括号  就成了递归调用该函数  递归调用时 每次调用都会产生新的栈帧区块   然后压栈到当前栈帧   而递归调用不停 会导致栈溢出错误
	}
}
