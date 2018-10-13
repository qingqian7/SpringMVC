package controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice //组合了@Component注解  向ioc容器声明是其bean
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(value = Exception.class) // 定义全局处理exception 可捕获任何controller的异常    value的值表示捕获的异常类型
	public ModelAndView exception(Exception exception, WebRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("error"); //返回到error.jsp  即跳转到显示error的页面
		modelAndView.addObject("errorMessage",exception.getMessage());//传参  在页面中用el表达式获取出来
		return modelAndView;
	}
	
	@ModelAttribute //将键值对添加到全局，所有注解了@RequestMapping的方法可获取该值
	public void addAttributes(Model model)
	{
		model.addAttribute("msg","额外信息");
	}
}
