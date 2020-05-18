package Presentation;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;


public class ClientController {
    //TODO change server name!!!
    private static final URI url= UriBuilder.fromUri("http://132.72.65.77:8080/ServerTry_war_exploded/rest").build();


    public static String connectToServer(String appClassName, String funcName, String... parameters){
        String ans;
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(url);
        service=service.path(appClassName).path(funcName);
        for (String param:parameters) {
            service=service.path(param);
        }
        ans=service.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class).getEntity(String.class);
        System.out.println("Server answer "+ans);
        return ans;
    }



}
