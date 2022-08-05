package ru.sergeysemenov;

import java.io.PrintWriter;

public class NotFoundHandler {
    private PrintWriter output;

    public NotFoundHandler(PrintWriter output){
        this.output = output;
    }

    public void informAboutException(){
        output.println("HTTP/1.1 404 NOT_FOUND");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<h1>Файл не найден!</h1>");
        output.flush();
    }

}
