package service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class PushService {
	private DeferredResult<String> deferredResult;
	public DeferredResult<String> getAsyncUpdate(){
		deferredResult = new DeferredResult<String>();
		return deferredResult;
	}
	
	@Scheduled(fixedDelay = 5000)  //配置中packageSacn中添加了此类所属的包  所以这个异步调度任务会被检测到并执行
	public void refresh() {
		if(deferredResult != null) {
			deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
		}
	}
}
