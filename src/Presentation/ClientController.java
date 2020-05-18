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

    private final URI url= UriBuilder.fromUri("http://132.72.65.77:8080/ServerTry_war_exploded/rest").build();// change server name!!!


    public static String connectToServer(String appName, String funcName, String... args){
        String ans="";
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);


        return ans;
    }

}
