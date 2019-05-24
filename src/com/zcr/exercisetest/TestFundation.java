package com.zcr.exercisetest;

import com.zcr.exercisefundation.Car;
import com.zcr.exercisefundation.ElectricCar;
import com.zcr.exercisefundation.Student;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zcr
 * @date 2019/5/6-11:11
 */
public class TestFundation {
    int aaa;//成员变量（实例变量），从属于对象；成员变量会自动被初始化。int0double0.0char'\u0000'booleanfalse
    static int size;//静态变量，从属于类;会自动初始化成该类型的默认初始值

    public static void  main(String args[]){

        //基本数据类型,变量
        //变量本质上就是代表一个”可操作的存储空间”，空间位置是确定的，但是里面放置什么值不确定。我们可通过变量名来访问“对应的存储空间”，从而操纵这个“存储空间”存储的值。
        //Java是一种强类型语言，每个变量都必须声明其数据类型。变量的数据类型决定了变量占据存储空间的大小
        //局部变量：方法或语句块内部（方法） 成员变量：类内部，方法外部（对象） 静态变量：类内部，static修饰（类）
        byte b = 30;
        byte b1;
        short s = 30000;
        int i0 = 300000000;
        int i1 = 034;//以0开头是八进制
        int i2 = 0x15;//以0x开头的是十六进制
        int i3 = 0b1101;//以0b开头的是二进制
        System.out.println("十进制数："+i0);
        System.out.println("八进制数34："+i1);
        System.out.println("十六进制数15："+i2);
        System.out.println("二进制数1101："+i3);
        long l = 100000000000L;
        //byte1字节(-128~127) short2(-32768~32767) int4(-2^31~2^31-1) long8(-2^63~2^63-1)
        //float4字节 double8字节
        float f = 3.455f;//如果不写f，会默认是double类型
        double d = 2.718281828;
        double d1 = 322e3;
        double d2 = 344e-3;
        System.out.println("科学计数法给浮点型变量赋值322e3："+d1);
        System.out.println("科学计数法给浮点型变量赋值344e-3："+d2);
        {
            char c = '中';//局部变量，从属于语句块
            char c1 = 'a';//Unicode编码表中的字符，有65536个字符,用从’\u0000’到’\uFFFF’之间的十六进制值来表示
            char c3 = '\u0061';
            char c2 = '\n';//转义字符\b退格 \r回车 \t制表符 \"双引号 \'单引号 \\反斜杠
            System.out.println("字符\u0061："+c3);
            System.out.println('a'+'b');//195 遇到加号，char类型自动转换成数字类型
            System.out.println(""+'a'+'b');//ab   为了让加号变成连接符，我们要在前面加一个空字符串
            System.out.println(""+'a'+'\n'+'b');// a换行b
            System.out.println(""+'a'+'\t'+'b'); //a制表符b
            System.out.println(""+'a'+'\''+'b');        //a'b
        }
        boolean flag = true;//局部变量，从属于方法；局部变量必须先声明并且初始化（赋初值），才能使用
        if (flag) {//boolean类型有两个常量值，true和false，在内存中占一位（不是一个字节），不可以使用 0 或非 0 的整数替代 true 和 false
            System.out.println("布尔变量为真");
        } else {
            System.out.println("布尔变量为假");
        }

        //不要使用浮点数进行比较，而是使用BigDecimal
        float b11 = 0.1f;
        double b2 = 1.0/10;
        System.out.println(b11 == b2);
        float b6 = 423423423f;
        float b7 = b6 + 1;
        System.out.println(b6 == b7);
        BigDecimal b3 = BigDecimal.valueOf(0.1);
        BigDecimal b4 = BigDecimal.valueOf(1.0/10.0);
        BigDecimal b5 = b3.subtract(BigDecimal.valueOf(0.01));
        System.out.println(b3.equals(b4));
        System.out.println(b5);


        //常量
        final double PI = 3.14;//常量一旦定义并赋值，就不能再被赋值

        //注释
        //我是单行注释
        /*public static void main(String[] args*//*我是行内注释 *//*) {
            System.out.println("Hello World!");
        }*/
        /*
           我是多行注释！
           我是多行注释！
         */



        //标识符
        //用来给变量、类、方法以及包进行命名的。
        //1标识符必须以字母、下划线_、美元符号开头。2标识符其它部分可以是字母、下划线、美元符和数字的任意组合。3Java 标识符大小写敏感，且长度无限制。4标识符不可以是Java的关键字。
        //1表示类名的标识符：每个单词的首字母大写。2表示方法和变量的标识符：第一个单词小写，从第二个单词开始首字母大写，我们称之为“驼峰原则”
        int  a123 = 1;
        //int  123abc = 2;        //数字不能开头
        int  $a = 3;
        int  _abc = 4;
        //int  #abc = 5;
        int  年龄 = 18;        //可以使用汉字，但是一般不建议
        //int class = 2;        //关键字不能作为标识符

        //运算符
        //算术运算符+ — * / % ++ --
        //整数运算：1. 如果两个操作数有一个为Long, 则结果也为long。2. 没有long时，结果为int。即使操作数全为short，byte，结果也是int。
        long y1 = 1;
        int y2 = 2;
        long y3 = y1 + y2;
        byte y4 = 2;
        short y5 = 1;
        int y6 = y2 + y4 + y5;
        //浮点运算：3. 如果两个操作数有一个为double，则结果为double。4. 只有两个操作数都是float，则结果才为float。
        float y7 = 2.1f;
        float y8 = 3.434f;
        double y9 = 2.111;
        double y10 = y7 + y9;
        float y11 = y7 + y8;
        //取模运算：1.其操作数可以为浮点数,一般使用整数，结果是“余数”，“余数”符号和左边操作数相同，如：7%3=1，-7%3=-1，7%-3=1。
        System.out.println("取模运算1：" + 7%3);
        System.out.println("取模运算2：" + -7%3);
        System.out.println("取模运算3：" + 7%-3);
        int a = 3;
        int e = a++;   //执行完后,e=3。先给e赋值，再自增。
        System.out.println("a="+a+"\ne="+e);//a=4 e=3
        a = 3;
        e = ++a;   //执行完后,e=4。a先自增，再给e赋值
        System.out.println("a="+a+"\ne="+e);//a=4 e=4
        //赋值运算符=
        //扩展运算符+= -= *= /=
        a += e;
        System.out.println("a="+a+"\ne="+e);//a=8 e=4
        a*=e+3;
        System.out.println("a="+a+"\ne="+e);//a=56 e=4
        //关系运算符> < >= <= == != instanceof,用来进行比较运算，结果是true或false
        //==、!= 是所有（基本和引用）数据类型都可以使用> 、>=、 <、 <= 仅针对数值类型（byte/short/int/long, float/double。以及char可以自动转化为数字）
        System.out.println(a == b);
        System.out.println(a >= b);
        //逻辑运算符& | ! ^ && ||,逻辑运算的操作数和运算结果都是boolean值。
        //逻辑与	&( 与)	两个操作数为true，结果才是true，否则是false
        //逻辑或	|(或)	两个操作数有一个是true，结果就是true
        //逻辑非	!（非）	取反：!false为true，!true为false
        //逻辑异或	^（异或）	相同为false，不同为true
        //从左到右计算，如果只通过运算符左边的操作数就能够确定该逻辑表达式的值，则不会继续计算运算符右边的操作数，提高效率。
        //短路与	&&( 与)	只要有一个为false，则直接返回false
        //短路或	||(或)	只要有一个为true， 则直接返回true
        boolean lj = 2>1 & 3>1;
        System.out.println("逻辑与（两个都真）："+lj);
        lj = 1>2 & 3>1;
        System.out.println("逻辑与（一个假，一个真）："+lj);
        lj = 1>2 && 3>1;
        System.out.println("短路与（一个假，一个真）："+lj);
        lj = 2>1 | 3>4;
        System.out.println("逻辑或（一个真，一个假）："+lj);
        lj = 2>1 || 3>4;
        System.out.println("短路或（一个真，一个假）："+lj);
        lj = !(1>2);
        System.out.println("逻辑非（把假的非一下）："+lj);
        lj = 1>2 ^ 3>4;
        System.out.println("逻辑异或（相同）："+lj);
        lj = 1>2 ^ 4>3;
        System.out.println("逻辑异或（不同）："+lj);
        //逻辑与、逻辑或、逻辑非的优先级一定要熟悉！（逻辑非>逻辑与>逻辑或）。如：
        //a||b&&c的运算结果是：a||(b&&c)，而不是(a||b)&&c
        //位运算符& | ^ ~ >> << >>>
        int w1 = 3&4;
        System.out.println("按位与：" + w1);
        w1 = 3|4;
        System.out.println("按位或：" + w1);
        w1 = ~3;
        System.out.println("取反：" + w1);
        w1 = 3^4;
        System.out.println("按位异或：" + w1);
        w1 = 3<<2;
        System.out.println("左移1位相当于乘2：" + w1);
        w1 = 12>>2;
        System.out.println("右移1位相当于除2："+ w1);
        //条件运算符?: 语法格式：x ? y : z
        //其中 x 为 boolean 类型表达式，先计算 x 的值，若为true，则整个运算的结果为表达式 y 的值，否则整个运算结果为表达式 z 的值。
        int score = 80;
        int x = -100;
        String type = score<60?"不及格":"及格";
        int tj = x > 0 ? 1 : (x == 0 ? 0 : -1);
        System.out.println("type= " + type);
        System.out.println("tj= "+ tj);
        //字符串连接符+“+”运算符两侧的操作数中只要有一个是字符串(String)类型，系统会自动将另一个操作数转换为字符串然后再进行连接。
        //如果是字符，会用ascii码
        String z = "3";
        int z1 = 4;
        int z2 = 5;
        String z3 = z+z1+z2;
        System.out.println("第一个加号两边是字符串" + z3);
        String z4 = z1+z2+z;
        System.out.println("第二个加号两边是字符串" + z4);
        char z5 = 'a';
        int z6= z5 + z1;
        System.out.println("字符与数字相加的话会用ASCII码" + z6);
        char z7 = 'b';
        int z8 = z5 + z7;
        System.out.println("字符与字符相加会用ASCII码" + z8);

        //自动类型转换：容量小的数据类型可以自动转换为容量大的数据类型byte-short-int-long char-int int-double float-double
        byte zd = 123;
        short zd1 = zd;
        int zd2 = zd1;
        long zd3 = zd2;
        double zd4 = zd2;
        char zd5 = 'a';
        int zd6 = zd5;
        System.out.println("自动类型转换：" + zd6);
        float zd7 = 123.8f;
        double zd8 = zd7;


        //强制类型转换：造型，用于显式的转换一个数值的类型。在有可能丢失信息的情况下进行的转换是通过造型来完成的，但可能造成精度降低或溢出。
        double qz = 3.14;
        int qz1 = (int)qz;
        System.out.println("强制类型转换：" + qz1);
        int qz2 = 101;
        System.out.println("强制类型转换：" + (char)qz2);
        int qz3 = 300;
        System.out.println("强制类型转换：" + (byte)qz3);//当将一种类型强制转换成另一种类型，而又超出了目标类型的表数范围，就会被截断成为一个完全不同的值。
        //操作比较大的数时，要留意是否溢出，尤其是整数操作时。可以把其中一个数事先转化为long类型。
        int money = 1000000000; //10亿
        int years = 20;
        int total = money*years;
        System.out.println("total="+total);//返回的total是负数，超过了int的范围
        //返回的total仍然是负数。默认是int，因此结果会转成int值，再转成long。但是已经发生//了数据丢失
        long total1 = money*years; //计算的时候就溢出了，所以之后再赋给一个大的也没用了
        System.out.println("total1="+total1);
        //返回的total2正确:先将一个因子变成long，整个表达式发生提升。全部用long来计算。
        long total2 = money*((long)years);
        System.out.println("total2="+total2);

        /*//简单的键盘输入输出
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的名字：");
        String name = sc.nextLine();
        System.out.println("请输入你的年龄：");
        int age = sc.nextInt();
        System.out.println("你的名字是：" + name + '\n' + "你的年龄是：" + age);
*/
        //随机数
        Random r1 = new Random();
        int number = r1.nextInt();
        System.out.println("产生随机数" + number);
        double r2 = Math.random();//[0.0,1.0)
        System.out.println("产生随机数" + r2);

        //语句
        //if单选择结构
        //通过掷三个骰子看看今天的手气如何？
        int i = (int)(6 * Math.random()) + 1;//通过Math.random()产生随机数，令系统随机选取大于等于 0.0 且小于 1.0 的伪随机 double 值，那么这就是产生一个随机1到6之间的整数
        int j = (int)(6 * Math.random()) + 1;//[0.0,1.0)->[0.0,6.0)->[0,5]->[1,6]
        int k = (int)(6 * Math.random()) + 1;
        int count = i + j + k;
        //如果三个骰子之和大于15，则手气不错
        if(count > 15) {
            System.out.println("今天手气不错");
        }
        //如果三个骰子之和在10到15之间，则手气一般
        if(count >= 10 && count <= 15) { //错误写法：10<=count<=15
            System.out.println("今天手气很一般");
        }
        /*if(10 <= count <= 15) {
            System.out.println("今天手气很一般");
        }*/
        //如果三个骰子之和小于10，则手气不怎么样
        if(count < 10) {
            System.out.println("今天手气不怎么样");
        }
        System.out.println("得了" + count + "分");

        //if-else结构
        //随机产生一个[0.0, 4.0)区间的半径，并根据半径求圆的面积和周长
        double r = 4 * Math.random();//[0.0,1.0)->[0.0,4.0)
        //Math.pow(r, 2)求半径r的平方
        double area = Math.PI * Math.pow(r, 2);
        double circle = 2 * Math.PI * r;
        System.out.println("半径为： " + r);
        System.out.println("面积为： " + area);
        System.out.println("周长为： " + circle);
        //如果面积>=周长，则输出"面积大于等于周长"，否则，输出周长大于面积
        if(area >= circle) {
            System.out.println("面积大于等于周长");
        } else {
            System.out.println("周长大于面积");
        }

        //条件运算符有时候可用于代替if-else
        int dt = 2;
        int dt1 = 3;
        System.out.println(dt<dt1?dt:dt1);
        if (dt < dt1){
            System.out.println(dt);
        } else {
            System.out.println(dt1);
        }

        //if-else if-else
        int age = (int) (100 * Math.random());//[0.0,1.0)->[0.0,100)->[0,99]整数
        System.out.print("年龄是" + age + "， 属于");
        if (age < 15) {
            System.out.println("儿童， 喜欢玩！");
        } else if (age < 25) {
            System.out.println("青年， 要学习！");
        } else if (age < 45) {
            System.out.println("中年， 要工作！");
        } else if (age < 65) {
            System.out.println("中老年， 要补钙！");
        } else if (age < 85) {
            System.out.println("老年， 多运动！");
        } else {
            System.out.println("老寿星， 古来稀！");
        }

        //switch多选择结构
        int month = (int)(12 * Math.random()) + 1;//[0.0,1.0)->[0.0,12.0)->[0,11]->[1,12]
        switch (month){
            case 1:
                System.out.println("一月份！过新年！");
                break;
            case 2:
                System.out.println("二月份！春天了！");
                break;
            default:
                System.out.println("我是其他月份");
                break;
        }
        char c = 'a';
        int rand = (int) (26 * Math.random());//[0.0,1.0)->[0.0,26.0)->[0,25]整数
        char c2 = (char) (c + rand);
        System.out.print(c2 + ": ");
        switch (c2) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                System.out.println("元音");
                break;
            case 'y':
            case 'w':
                System.out.println("半元音");
                break;
            default:
                System.out.println("辅音");
        }

        //while循环结构：1+2+3+…+100=? 先判断再执行
        int  i11 = 0;
        int  sum = 0;
        while (i11 <= 100) {
            sum += i11;//相当于sum = sum+i;
            i11++;
        }
        System.out.println("Sum=" + sum);

        //do-while循环结构：1-100之间的累加和 先执行再判断 do-while总是保证循环体至少会被执行一次!
        int i12 = 0;
        int sum1 = 0;
        do {
            sum1 += i12; // sum = sum + i
            i12++;
        } while (i12 <= 100);//此处的；不能省略
        System.out.println("Sum1=" + sum1);
        
        //for循环结构
        /*1.执行初始化语句：i=1;
        2.判断i<=100;
        3.执行循环体；
        4.步进迭代；
        5.回到第二步继续判断*/
        int sum2 = 0;
        for (int m = 1; m <= 100; m++) {
            sum2 =sum2 + m;
        }
        System.out.println("Sum2=" + sum2);
        for(i = 1, j = i + 10; i < 5; i++, j = i * 2) {
            System.out.println("i= " + i + " j= " + j);
        }

        //无限循环while(true)  for(;;)

        //嵌套循环
        for (i=1; i <=5; i++) {
            for(j=1; j<=5; j++){
                System.out.print(i+"  ");
            }
            System.out.println();//每次外层循环一次换行
        }
        for (i = 1; i < 10; i++) { // i是一个乘数
            for (j = 1; j <= i; j++) { // j是另一个乘数
                System.out.print(j + "*" + i + "=" + (i * j < 10 ? (" " + i * j) : i * j) + "  ");
            }
            System.out.println();
        }

        //break语句和continue语句
        int total11 = 0;//定义计数器
        System.out.println("Begin");
        while (true) {
            total11++;//每循环一次计数器加1
            int i111 = (int) Math.round(100 * Math.random());//[0.0,1.0)->[0.0,100)->[0,99)
            //当i等于88时，退出循环
            if (i111 == 88) {
                break;//break用于强行退出循环，不执行循环中剩余的语句。
            }
        }
        System.out.println("Game over， used " + total11 + " times.");////输出循环的次数

        //continue 语句用在循环语句体中，用于终止某次循环过程，即跳过循环体中尚未执行的语句，接着进行下一次是否执行循环的判定。
        int count11 = 0;//定义计数器
        for (int i1111 = 100; i1111 < 150; i1111++) {
            if (i1111 % 3 == 0) {
                continue;//如果是3的倍数，则跳过本次循环，继续进行下一次循环
            }
            //否则（不是3的倍数），输出该数
            System.out.print(i1111 + "、");
            count11++;//没输出一个数，计数器加1
            //根据计数器判断每行是否已经输出了5个数
            if (count11 % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println();

        //带有标签的break和continue:控制嵌套循环跳转(打印101-150之间所有的质数)
        //“标签”是指后面跟一个冒号的标识符，例如：“label:”。对Java来说唯一用到标签的地方是在循环语句之前。
        //而在循环之前设置标签的唯一理由是：我们希望在其中嵌套另一个循环，由于break和continue关键字通常只中断当前循环，但若随同标签使用，它们就会中断到存在标签的地方。
        outer: for (i = 101; i < 150; i++) {
            for (j = 2; j < i / 2; j++) {
                if (i % j == 0){
                    continue outer;
                }
            }
            System.out.print(i + "  ");
        }
        System.out.println();

        //数组
        int[] arr = new int[5];
        arr[0]=1;
        arr[2]=3;
        System.out.println("根据下标值取得数组中的数据" + arr[4]);

        //调用方法
        int num1 = 10;
        int num2 = 20;
        int sum12 = add(num1,num2);
        System.out.println("调用静态方法" + sum12);
        int sum13 = TestFundation.add(num1,num2);
        System.out.println("调用静态方法" + sum13);

        //递归结构
        long d11 = System.currentTimeMillis();
        System.out.printf("%d阶乘的结果:%s%n", 10, factorial(10));
        long d21 = System.currentTimeMillis();
        System.out.printf("递归费时：%s%n", d21-d11);  //耗时：17ms

        //使用循环求n!
        long d3 = System.currentTimeMillis();
        int a1 = 10;
        int result = 1;
        while (a1 > 1) {
            result *= a1 * (a1 - 1);
            a1 -= 2;
        }
        long d4 = System.currentTimeMillis();
        System.out.println(result);
        System.out.printf("普通循环费时：%s%n", d4 - d3);

    }

    //方法
    //方法用于定义该类或该类的实例的行为特征和功能实现。 方法是类和对象行为特征的抽象。
    /*方法声明格式：
    [修饰符1 修饰符2 …] 返回值类型 方法名(形式参数列表){
        Java语句；… … …
    }
    方法的调用方式：对象名.方法名(实参列表)
    方法的详细说明
    形式参数：在方法声明时用于接收外界传入的数据。
    实参：调用方法时实际传给方法的数据。
    返回值：方法在执行完毕后返还给调用它的环境的数据。
    返回值类型：事先约定的返回值的数据类型，如无返回值，必须显示指定为为void。
    实参的数目、数据类型和次序必须和所调用的方法声明的形式参数列表匹配。
    return 语句终止方法的运行并指定要返回的数据。
    Java中进行方法调用中传递参数时，遵循值传递的原则(传递的都是数据的副本)：
    基本类型传递的是该数据值的copy值。
    引用类型传递的是该对象引用的copy值，但指向的是同一个对象。*/
    public static int add(int n1,int n2){
        int sum = n1 + n2;
        return sum;
    }

    //方法的重载overload
    //方法的重载是指一个类中可以定义多个方法名相同，但参数不同的方法。 调用时，会根据不同的参数自动匹配对应的方法。
    /*构成方法重载的条件：
    1.不同的含义：形参类型、形参个数、形参顺序不同
    2.只有返回值不同不构成方法的重载
    如：int a(String str){}与 void a(String str){}不构成方法重载
    3.只有形参的名称不同，不构成方法的重载
    如：int a(String str){}与int a(String s){}不构成方法重载*/
    // 方法名相同，参数个数不同，构成重载
    public static int add(int n1, int n2, int n3) {
        int sum = n1 + n2 + n3;
        return sum;
    }
    // 方法名相同，参数类型不同，构成重载
    public static double add(double n1, int n2) {
        double sum = n1 + n2;
        return sum;
    }
    // 方法名相同，参数顺序不同，构成重载
    public static double add(int n1, double n2) {
        double sum = n1 + n2;
        return sum;
    }
    /*//编译错误：只有返回值不同，不构成方法的重载
    public static double add(int n1, int n2) {
        double sum = n1 + n2;
        return sum;
    }
    //编译错误：只有参数名称不同，不构成方法的重载
    public static int add(int n2, int n1) {
        double sum = n1 + n2;
        return sum;
    }*/

    //递归结构
    //自己调用自己 斐波那契数列的计算、汉诺塔、快排
    /*递归结构包括两个部分：
    1.定义递归头。解答：什么时候不调用自身方法。如果没有头，将陷入死循环，也就是递归的结束条件。
    2.递归体。解答：什么时候需要调用自身方法。*/
    //计算n! 求阶乘的方法
    static long  factorial(int n){
        if(n==1){//递归头
            return 1;
        }else{//递归体
            return n*factorial(n-1);//n! = n * (n-1)!
        }
    }
    //向下递归，将递归调用的返回值往上传
    //简单的程序是递归的优点之一。但是递归调用会占用大量的系统堆栈，内存耗用多，在递归调用层次多时速度要比循环慢的多，所以在使用递归时要慎重。
    //使用循环求n!只需要0s
    //任何能用递归解决的问题也能使用迭代解决。当递归方法可以更加自然地反映问题，并且易于理解和调试，并且不强调效率问题时，可以采用递归;
    //在要求高性能的情况下尽量避免使用递归，递归调用既花时间又耗内存。




}
