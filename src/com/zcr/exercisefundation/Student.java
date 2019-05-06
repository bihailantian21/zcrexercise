package com.zcr.exercisefundation;

/**
 * @author zcr
 * @date 2019/5/6-20:35
 */
public class Student implements Comparable<Student>{
    private String name;
    private int age;

    public Student() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Student(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Student s) {
        //return -1; //-1表示放在红黑树的左边,即逆序输出
        //return 1;  //1表示放在红黑树的右边，即顺序输出
        //return o;  //表示元素相同，仅存放第一个元素

        //主要条件 姓名的长度,如果姓名长度小的就放在左子树，否则放在右子树
        int num=this.name.length()-s.name.length();//姓名短的在前，长的在后

        //姓名的长度相同，不代表内容相同,如果按字典顺序此 String 对象位于参数字符串之前，则比较结果为一个负整数。
        //如果按字典顺序此 String 对象位于参数字符串之后，则比较结果为一个正整数。
        //如果这两个字符串相等，则结果为 0
        int num1=num==0?this.name.compareTo(s.name):num;//姓名字母小的在前，字母大的在后

        //姓名的长度和内容相同，不代表年龄相同，所以还要判断年龄
        int num2=num1==0?this.age-s.age:num1;//年龄小的在前，大的在后
        return num2;
    }
}
