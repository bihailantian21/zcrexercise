补充相关知识
1.多请求处理——反射Class.forName("完整路径")clz.getConstructor().newInstance()
2.xml配置文件解析
（1）p.xml XmlTest01.java 解析出每个键值对的内容
SAXParserFactory SAXParser PHandler  parse.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("x/p.xml"),handler);
（2）p.xml Person.java XmlTest02.java 将解析出的键值对构造出对象列表
（3）web.xml Entity.java Mapping.java XmlTest03.java 将解析出的键值对分别构造出对象列表
（4）web.xml Entity.java Mapping.java WebContext.java XmlTest04.java Servlet.java LoginServlet.java RegisterServlet.java
将解析出的键值对分别构造出对象列表；再将列表分别都转成对应的map，把两个属性分别作为键和值，根据两个map的对应关系通过URL的路径找到了对应class；
然后，根据完整的class名进行反射，创建出servlet对象实例，调用方法；
3.HTML，表单，get/post请求方式
4.HTTP响应格式及相关
（1）获取请求协议Server01.java 使用ServerSocket建立与浏览器的连接，通过输入流读取内容，获取请求协议
（2）返回响应协议Server02.java 返回响应协议。通过输入流读取内容，获取请求协议；通过输出流写出包含指定格式的响应内容
（3）封装响应信息Server03.java Response.java 封装response响应内容。封装通过输出流写出包含指定格式的响应内容
（4）封装请求信息Server04.java Response.java Request1.java 封装request分解协议，封装请求协议: 获取 method uri以及请求参数
通过输入流读取内容，获取请求协议后；将请求协议的内容进行细致的分解打印到控制台，并且获取URL，method，params串等内容；还是封装通过输出流写出包含指定格式的响应内容
（5）处理请求参数Server05.java Response.java Request2.java 获取参数 处理中文。封装请求协议: 封装请求参数为Map
在（4）的基础上；处理请求参数存储为Map，并且处理中文，通过name获取对应的多个值，通过name获取对应的一个值；
（6）引入servlet Server06.java ResponseServlet.java RequesServlet.java Servlet.java RegisterServlet.java LoginServlet.java引入servlet
封装response响应内容,封装通过输出流写出包含指定格式的响应内容;封装request分解协议，处理请求参数存储为Map，并且处理中文，获取 method uri以及请求参数,通过name获取对应的多个值，通过name获取对应的一个值
获取请求协议后，获取到URL得到类名，创建出servlet对象实例，调用方法；获取响应协议，返回响应内容；
（7）加入配置文件和反射Server07.java ResponseServlet.java RequesServlet.java Servlet.java RegisterServlet.java LoginServlet.java OthersServlet.java
web.xml Entity.java Mapping.java WebContext.java WebApp.java WebHandler.java 整合webxml
将解析出的键值对分别构造出对象列表；再将列表分别都转成对应的map，把两个属性分别作为键和值，根据两个map的对应关系通过URL的路径找到了对应class；
通过url获取配置文件对应的servlet,即根据完整的class名进行反射，创建出servlet对象实例；
封装response响应内容,封装通过输出流写出包含指定格式的响应内容;封装request分解协议，处理请求参数存储为Map，并且处理中文，获取 method uri以及请求参数,通过name获取对应的多个值，通过name获取对应的一个值
获取请求协议后，获取到URL得到servlet，创建出servlet对象实例，调用方法；获取响应协议，返回响应内容；
（8）封装多线程的分发器Server08.java  （7）的所有文件 Dispatcher.java 高效分发器，多线程处理加入分发器
将请求和相应的所有内容封装到Dispatcher.java，作为多线程类；然后启动多个线程，即可以处理多个客户端提出的请求，并对他们作出响应
并且根据情况返回200，404，500的响应信息
Package Explorer目录
（9）处理错误和首页Server09.java （7）的所有文件 Dispatcher.java error.html index.html经典404及首页处理
还要获取参数进行判断，从而响应不同的内容
