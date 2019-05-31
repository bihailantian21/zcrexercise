package com.zcr.exercisetest.collections;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class SxtHashSet {
    //HashSet底层实现
    /*HashSet是采用哈希算法实现，底层实际是用HashMap实现的(HashSet本质就是一个简化版的HashMap)，
    因此，查询效率和增删效率都比较高。我们来看一下HashSet的源码：
    pulic class HashSet<E> implements Set<E>,Cloneable,java.io.Serializable{
        private transient HashMap<E,Object> map;
        private static final Object PRESENT = new Object();
        public HashSet(){
            map = new HashMap<>();
        }
        public boolean add(E e){
            return map.put(e,PRESENT) == null;
        }

    }
    我们发现里面有个map属性，这就是HashSet的核心秘密。我们再看add()方法，发现增加一个元素说白了就是在map中增加一个键值对，
    键对象就是这个元素，值对象是名为PRESENT的Object对象。
    说白了，就是“往set中加入元素，本质就是把这个元素作为key加入到了内部的map中”。*/
    HashMap map;
    private static final Object PRESENT=new Object();
    public SxtHashSet(){
        map=new HashMap();//由于map中key都是不可重复的，因此，Set天然具有“不可重复”的特性。
    }
    public int size(){
        return map.size();
    }
    public void add(Object o){
        map.put(o,PRESENT);
    }

    //TreeSet的使用和底层实现
    /*TreeSet底层实际是用TreeMap实现的，内部维持了一个简化版的TreeMap，通过key来存储Set的元素。
    TreeSet内部需要对存储的元素进行排序，因此，我们对应的类需要实现Comparable接口。
    这样，才能根据compareTo()方法比较对象之间的大小，才能进行内部排序。*/
    /*使用TreeSet要点：
            (1) 由于是二叉树，需要对元素做内部排序。 如果要放入TreeSet中的类没有实现Comparable接口，则会抛出异常：java.lang.ClassCastException。
            (2) TreeSet中不能放入null元素。*/



    public static void main(String[] args) {
        User1 u1 = new User1(1001, "高淇", 18);
        User1 u2 = new User1(2001, "高希希", 5);
        Set<User1> set = new TreeSet<User1>();
        set.add(u1);
        set.add(u2);
        System.out.println(set);
    }


}

class User1 implements Comparable<User1> {
    int id;
    String uname;
    int age;

    public User1(int id, String uname, int age) {
        this.id = id;
        this.uname = uname;
        this.age = age;
    }
    /**
     * 返回0 表示 this == obj 返回正数表示 this > obj 返回负数表示 this < obj
     */
    @Override
    public int compareTo(User1 o) {
        if (this.id > o.id) {
            return 1;
        } else if (this.id < o.id) {
            return -1;
        } else {
            return 0;
        }
    }
}


