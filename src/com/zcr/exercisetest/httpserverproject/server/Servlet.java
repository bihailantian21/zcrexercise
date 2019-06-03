package com.zcr.exercisetest.httpserverproject.server;

/**
 * 服务器小脚本接口
 */
public interface Servlet {
    void service(RequestServlet request,ResponseServlet response);
}

