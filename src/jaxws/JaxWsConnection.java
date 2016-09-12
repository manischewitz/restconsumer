package jaxws;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;






@Profile("JAX-WS")
@Configuration
public class JaxWsConnection {

	
	@Bean
	public JaxWsPortProxyFactoryBean jaxService() throws MalformedURLException{
		JaxWsPortProxyFactoryBean jaxWsProxy = new JaxWsPortProxyFactoryBean();
		jaxWsProxy.setWsdlDocumentUrl((new URL("http://localhost:9999/services/applicationService?wsdl")));
		jaxWsProxy.setServiceName("applicationService");
		jaxWsProxy.setPortName("ApplicationServiceEndpointPort");
		jaxWsProxy.setServiceInterface(ApplicationService.class);
		jaxWsProxy.setNamespaceUri("http://jaxws/");
		return jaxWsProxy;
	}
}
