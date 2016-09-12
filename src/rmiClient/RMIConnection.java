package rmiClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
@Profile("RMI")
@Configuration
public class RMIConnection {

	@Bean
	public RmiProxyFactoryBean otherAppService(){
		RmiProxyFactoryBean rmipfb = new RmiProxyFactoryBean();
		rmipfb.setServiceUrl("rmi://localhost:1099/ApplicationService");
		rmipfb.setServiceInterface(ApplicationService.class);
		return rmipfb;
	}
	
	
}
