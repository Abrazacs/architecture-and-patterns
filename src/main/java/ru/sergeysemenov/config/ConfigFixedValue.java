package ru.sergeysemenov.config;

public class ConfigFixedValue implements ServerConfig{


    @Override
    public String getWww() {
        return "C:/Users/serge/IdeaProjects/architecture-and-patterns/WWW";
    }

    @Override
    public int getPort() {
        return 8091;
    }
}
