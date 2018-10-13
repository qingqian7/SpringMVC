package Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DemoInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime",startTime);
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		long startTime  = (Long)request.getAttribute("startTime");
		request.removeAttribute("startTime");
		Long endTime = System.currentTimeMillis();
		System.out.println("本次处理请求时间为：" +new Long(endTime - startTime) + "ms" );
		request.setAttribute("lastTime", (endTime - startTime));
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
