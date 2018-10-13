package controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServerSendEvent {
	 //删除的媒体类型为text/event-stream 这是服务器端sse的支持  服务器端会一直保持浏览器发来的请求   然后有新消息就的返回消息  这里是用线程等待来模拟服务器端事件
	@RequestMapping(value="/push",produces="text/event-stream")
	public @ResponseBody String push()
	{
		Random r = new Random();
		System.out.println("debug5.............");
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		return "data:Testing 1,2,3" + r.nextInt() + "\n\n"; //这个返回数据的格式是不能随意改的  “data:”+data+"\n\n"  冒号两段不能家空格 \n\n不能少
	}
}
