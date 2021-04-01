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
//������ʱ����
@EnableScheduling
@EnableAsync
public class Application{

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	/**
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.debug("�����ɹ�");
	}
	/**
	 * ���� Druid ��ؽ���
	 * ���ʵ�ַ:http://localhost:80/druid/login.html
	 */
	@Bean
	public ServletRegistrationBean statViewServlet(){
		ServletRegistrationBean srb =
				new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
		//���ÿ���̨�����û�
		srb.addInitParameter("loginUsername","root");
		srb.addInitParameter("loginPassword","root");
		//�Ƿ������������
		srb.addInitParameter("resetEnable","false");
		return srb;
	}
	/*@Bean
	public ServletRegistrationBean druidStatViewServlet() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(),  "/druid/*");
		registrationBean.addInitParameter("allow", "127.0.0.1");// IP������ (û�����û���Ϊ�գ����������з���)
		registrationBean.addInitParameter("deny", "");// IP������ (���ڹ�ͬʱ��deny������allow)
		registrationBean.addInitParameter("loginUsername", "root");
		registrationBean.addInitParameter("loginPassword", "1234");
		registrationBean.addInitParameter("resetEnable", "false");
		return registrationBean;
	}*/
}