package ru.sergeysemenov;

public class StringParser {

    public String getFileName(String firstLine){
        String [] parts = firstLine.split(" ");
        return parts[1];
    }

}
