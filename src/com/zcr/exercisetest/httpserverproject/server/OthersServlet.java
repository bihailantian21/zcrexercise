package com.zcr.exercisetest.httpserverproject.server;


public class OthersServlet implements Servlet {

    @Override
    public void service(RequestServlet request,ResponseServlet response) {
        response.print("其他测试页面");
    }

}

