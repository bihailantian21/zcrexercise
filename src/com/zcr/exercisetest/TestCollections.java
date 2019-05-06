package com.zcr.exercisetest;

import com.zcr.exercisefundation.Car;
import com.zcr.exercisefundation.Student;

import java.util.*;

/**
 * @author zcr
 * @date 2019/5/6-21:26
 */
public class TestCollections {
    public static void  main(String args[]){
        List l1 = new ArrayList();
        l1.add(0,12);
        l1.add(1,"123");
        Car c19 = new Car();
        l1.add(c19);
        System.out.println(l1);
        l1.remove(2);
        System.out.println(l1);
        l1.set(0,13);
        System.out.println(l1);
        System.out.println(l1.get(1));

        LinkedList l2 = new LinkedList();
        l2.addFirst(1);
        l2.addLast(34);
        l2.add(3);
        l2.add(4);
        l2.add(5);
        l2.addLast(56);
        System.out.println(l2);
        System.out.println(l2.getFirst());
        System.out.println(l2.getLast());
        l2.removeFirst();
        System.out.println(l2);
        l2.removeLast();
        System.out.println(l2);
        l2.pop();
        System.out.println(l2);
        System.out.println(l2.pop());
        l2.push(10);
        System.out.println(l2);//栈，先进先出
        while(!l2.isEmpty()){
            System.out.println(l2.removeFirst());//模拟栈
        }

        System.out.println("===========================================");


        Set s1 = new HashSet();//无序
        s1.add(5);
        s1.add(1);
        s1.add(2);
        s1.add(2);
        s1.add(3);
        s1.add(4);
        s1.add(4);
        System.out.println(s1);
        Set s2 = new LinkedHashSet();//有序
        s2.add(56);
        s2.add(56);
        s2.add(50);
        s2.add(51);
        s2.add(52);
        System.out.println(s2);
        Set s3 = new TreeSet();//有序
        s3.add(1);
        s3.add(1);
        s3.add(2);
        s3.add(3);
        s3.add(4);
        System.out.println(s3);
        Set s4 = new TreeSet(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //长度小的在前
                int num = o1.length() - o2.length();
                return num == 0 ? o1.compareTo(o2):num;
            }
        });
        s4.add("zjhsakhd");
        s4.add("dnfekjh");
        s4.add("ac");
        s4.add("dre");
        System.out.println(s4);
        Set<Student> s5 = new TreeSet<Student>();
        //创建元素对象
        Student ss1=new Student("zhangsan",20);
        Student ss2=new Student("anna",22);
        Student ss3=new Student("bobo",24);
        Student ss4=new Student("chenliu",26);
        Student ss5=new Student("zhangsan",22);
        Student ss6=new Student("qianqi",24);
        //将元素对象添加到集合对象中
        s5.add(ss1);
        s5.add(ss2);
        s5.add(ss3);
        s5.add(ss4);
        s5.add(ss5);
        s5.add(ss6);
        //遍历
        for(Student s:s5){
            System.out.println(s.getName()+"-----------"+s.getAge());
        }

        System.out.println("===========================================");

        //三种遍历方法
        Iterator i1 = s2.iterator();
        while (i1.hasNext()){
            System.out.println(i1.next());
        }
        i1.remove();
        System.out.println(s2);

        for (Object i : s2){//增强for循环只用来遍历
            System.out.println(i);
        }

        for (int i = 0; i < l1.size() ; i++) {
            System.out.println(l1.get(i));

        }

        System.out.println("===========================================");

        Map m1 = new HashMap();//无序
        m1.put("name","zcr");
        m1.put("age",1);
        m1.put("city","beijign");
        m1.put("gender","woman");
        System.out.println(m1.get("name"));
        m1.remove("age");
        System.out.println(m1);
        m1.put("name","wx");
        System.out.println(m1);

        Map m2 = new LinkedHashMap();//有序
        m2.put(1,"zcr");
        m2.put(2,"sd");
        m2.put(3,"eee");
        m2.put(4,"ds");
        System.out.println(m2);

        Map m3 = new TreeMap();//有序
        m3.put(1,"12");
        m3.put(2,"234");
        m3.put(3,"122");
        System.out.println(m3);

        Set ms1 = m1.keySet();
        System.out.println(ms1);
        for(Object i : ms1){
            System.out.println(i+"---"+m1.get(i));
        }

       /* Set ms2 = m1.entrySet();//不写Map.Entry<String,String>不行
        System.out.println(ms2);
        for(Object i : ms2){
            System.out.println();
        }*/
        Set<Map.Entry<String,String>> ms2 = m1.entrySet();
        System.out.println(ms2);
        for(Map.Entry<String,String> i : ms2){
            System.out.println(i.getKey()+"---"+i.getValue());
        }

        Iterator<Map.Entry<String,String>> it = m1.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> entry = it.next();
            System.out.println(entry.getKey()+"---"+entry.getValue());
        }

        System.out.println("===========================================");

        Collections.shuffle(l1);
        System.out.println(l1);
        List<Integer> ll1 = new ArrayList<Integer>();
        ll1.add(2);
        ll1.add(1);
        ll1.add(3);
        ll1.add(19);
        ll1.add(6);
        System.out.println(ll1);
        Collections.sort(ll1);
        System.out.println(ll1);
        List<String> ll2 = new ArrayList<String>();
        ll2.add("ab");
        ll2.add("aaa");
        ll2.add("sss");
        ll2.add("bdjd");
        ll2.add("asdff");
        System.out.println(ll2);
        Collections.sort(ll2);
        System.out.println(ll2);

        double[] d1 = new double[3];
        d1[0]=3.3;
        d1[1]=1.2;
        d1[2]=2.1;
        System.out.println(Arrays.toString(d1));
        Arrays.sort(d1);
        System.out.println(Arrays.toString(d1));
        System.out.println(Arrays.binarySearch(d1,1.2));
        System.out.println(Arrays.toString(d1));





    }
}
