import com.alibaba.druid.support.http.StatViewServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
//开启定时任务
@EnableScheduling
@EnableAsync
public class Application{

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	/**
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.debug("启动成功");
	}
	/**
	 * 配置 Druid 监控界面
	 * 访问地址:http://localhost:80/druid/login.html
	 */
	@Bean
	public ServletRegistrationBean statViewServlet(){
		ServletRegistrationBean srb =
				new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
		//设置控制台管理用户
		srb.addInitParameter("loginUsername","root");
		srb.addInitParameter("loginPassword","root");
		//是否可以重置数据
		srb.addInitParameter("resetEnable","false");
		return srb;
	}
	/*@Bean
	public ServletRegistrationBean druidStatViewServlet() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(),  "/druid/*");
		registrationBean.addInitParameter("allow", "127.0.0.1");// IP白名单 (没有配置或者为空，则允许所有访问)
		registrationBean.addInitParameter("deny", "");// IP黑名单 (存在共同时，deny优先于allow)
		registrationBean.addInitParameter("loginUsername", "root");
		registrationBean.addInitParameter("loginPassword", "1234");
		registrationBean.addInitParameter("resetEnable", "false");
		return registrationBean;
	}*/
}