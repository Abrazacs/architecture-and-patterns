package ru.sergeysemenov.config;

import java.io.IOException;
import java.util.Properties;

public class ConfigFromFile implements ServerConfig{

    private final String www;

    private final int port;

    public ConfigFromFile(String filename){
        System.out.println("Getting config from config file");
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(filename));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        this.www = properties.getProperty("www.home");
        this.port = Integer.parseInt(properties.getProperty("port"));
    }

    @Override
    public String getWww() {
        return this.www;
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
