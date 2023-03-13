package co.develhope.firstapi4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class HeaderService {

    @Autowired
    Environment environment;

    private String host;
    private String port;

    public String getPort() {
        port = environment.getProperty("local.server.port");
        return port;
    }

    public String getHost() throws UnknownHostException {
        host = InetAddress.getLocalHost().getHostName();
        return host;
    }

}