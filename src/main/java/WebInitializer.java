
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//实现此接口便相当于web.xml的配置  这种方法替代web.xml的配置方式  因为不用struts2的方式  只用spring的方式 所以可以直接用spring里的类家主注解的方式来实现启动web server
public class WebInitializer implements WebApplicationInitializer{ 

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		//注解类型的web应用
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		//注册配置类  相当于web.xml的配置
		ctx.register(MyMvcConfig.class);
		//关联servlet上下文
		ctx.setServletContext(servletContext);
		//注册springmvc的dispatcher类
		Dynamic servlet = (Dynamic) servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/");
		System.out.println("debug2.......");
		servlet.setLoadOnStartup(1);
		servlet.setAsyncSupported(true);
	}

}
