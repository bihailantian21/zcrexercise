package com.zcr.exercisetest.httpserverproject.server;


public class RegisterServlet implements Servlet {

    @Override
    public void service(RequestServlet request,ResponseServlet response) {
        response.print("注册成功");
    }

}

