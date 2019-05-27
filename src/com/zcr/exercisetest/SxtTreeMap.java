package com.zcr.exercisetest;

import java.util.Map;
import java.util.TreeMap;

public class SxtTreeMap {
    //二叉树和红黑二叉树
    /*二叉树的定义
    二叉树是树形结构的一个重要类型。 许多实际问题抽象出来的数据结构往往是二叉树的形式，即使是一般的树也能简单地转换为二叉树，
    而且二叉树的存储结构及其算法都较为简单，因此二叉树显得特别重要。
    二叉树(BinaryTree)由一个节点及两棵互不相交的、分别称作这个根的左子树和右子树的二叉树组成。有五种不同基本形态的二叉树。
            (a) 为空树。
            (b) 为仅有一个结点的二叉树。
            (c)是仅有左子树而右子树为空的二叉树。
            (d) 是仅有右子树而左子树为空的二叉树。
            (e) 是左、右子树均非空的二叉树。
    注意事项
    二叉树的左子树和右子树是严格区分并且不能随意颠倒的，（c） 与 (d) 就是两棵不同的二叉树。

    ▪ 排序二叉树
    特性如下：
            (1) 左子树上所有节点的值均小于它的根节点的值。
            (2) 右子树上所有节点的值均大于它的根节点的值。
    排序二叉树本身实现了排序功能，可以快速检索。但如果插入的节点集本身就是有序的，要么是由小到大排列，要么是由大到小排列，
    那么最后得到的排序二叉树将变成普通的链表，其检索效率就会很差。
    比如上面的数据【14,12,23,4,16,13, 8,3】，我们先进行排序变成：【3,4,8,12,13,14,16,23】，然后存储到排序二叉树中，显然就变成了链表

    ▪ 平衡二叉树(AVL)
    为了避免出现上述一边倒的存储，科学家提出了“平衡二叉树”。
    在平衡二叉树中任何节点的两个子树的高度最大差别为1，所以它也被称为高度平衡树。 增加和删除节点可能需要通过一次或多次树旋转来重新平衡这个树。
    节点的平衡因子是它的左子树的高度减去它的右子树的高度(有时相反)。带有平衡因子1、0或 -1的节点被认为是平衡的。
    带有平衡因子 -2或2的节点被认为是不平衡的，并需要重新平衡这个树。
    比如，我们存储排好序的数据【3,4,8,12,13,14,16,23】，增加节点如果出现不平衡，则通过节点的左旋或右旋，重新平衡树结构，最终平衡二叉树如下图所示：
    平衡二叉树追求绝对平衡，实现起来比较麻烦，每次插入新节点需要做的旋转操作次数不能预知。

    ▪ 红黑二叉树
    红黑二叉树(简称：红黑树)，它首先是一棵二叉树，同时也是一棵自平衡的排序二叉树。
    红黑树在原有的排序二叉树增加了如下几个要求：
            1.每个节点要么是红色，要么是黑色。
            2.根节点永远是黑色的。
            3.所有的叶节点都是空节点(即 null)，并且是黑色的。
            4.每个红色节点的两个子节点都是黑色。(从每个叶子到根的路径上不会有两个连续的红色节点)
            5.从任一节点到其子树中每个叶子节点的路径都包含相同数量的黑色节点。
    这些约束强化了红黑树的关键性质：从根到叶子的最长的可能路径不多于最短的可能路径的两倍长。这样就让树大致上是平衡的。
    红黑树是一个更高效的检索二叉树，JDK 提供的集合类 TreeMap、TreeSet 本身就是一个红黑树的实现。
    红黑树的基本操作：插入、删除、左旋、右旋、着色。 每插入或者删除一个节点，可能会导致树不在符合红黑树的特征，
    需要进行修复，进行 “左旋、右旋、着色”操作，使树继续保持红黑树的特性。
    老鸟建议
    本节关于二叉树的介绍，仅限于了解。实际开发中，直接用到的概率非常低。普通企业面试中也较少。
    不过，极有可能出现在BAT等企业笔试中。建议，想进BAT等名企的童鞋，专门准备一下数据结构相关的知识。
    */
    //TreeMap的使用和底层实现
    /*TreeMap是红黑二叉树的典型实现。我们打开TreeMap的源码，发现里面有一行核心代码：
    private transient Entry<K,V> root = null;
    root用来存储整个树的根节点。我们继续跟踪Entry(是TreeMap的内部类)的代码：
    static final class Entry<K,V> implements Map.Entry<K,V>{
        K key;
        V value;
        Entry<K,V> left = null;
        Entry<K,V> right = null;
        Entry<K,V> parent;
        boolean color = BLACK;

    }
    可以看到里面存储了本身数据、左节点、右节点、父节点、以及节点颜色。 TreeMap的put()/remove()方法大量使用了红黑树的理论。本书限于篇幅，不再展开。
    需要了解更深入的，可以参考专门的数据结构书籍。
    TreeMap和HashMap实现了同样的接口Map，因此，用法对于调用者来说没有区别。
    HashMap效率高于TreeMap;在需要排序的Map时才选用TreeMap。*/
    public static void main(String[] args){
        Map<Integer,String> treemap1=new TreeMap<>();
        treemap1.put(20,"aa");
        treemap1.put(3,"bb");
        treemap1.put(6,"cc");
        for(Integer key:treemap1.keySet()){
            System.out.println(key+"....."+treemap1.get(key));//按照key递增的方式排序
        }

        Map<Employee1,String> treemap2=new TreeMap<>();
        treemap2.put(new Employee1(100,"张三",5000),"张三是一个好小伙");
        treemap2.put(new Employee1(200,"李四",500),"李四工作不积极");
        treemap2.put(new Employee1(150,"王五",6000),"王五工作还不错");
        for(Employee1 key:treemap2.keySet()){
            System.out.println(key+"....."+treemap2.get(key));
        }
    }



}
//自定义的类该如何排序呢？
class Employee1 implements Comparable<Employee1> {
    int id;
    String name;
    double salary;

    public Employee1(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    //构造,getset,tosring
    public int compareTo(Employee1 o) {//负数：小于  0：等于  正数：大于
        if (this.salary > o.salary) {//按照工资从低到高排序  //大于放右边
            return 1;
        } else if (this.salary < o.salary) {//小于放左边
            return -1;
        } else {
            if (this.id > o.id) {
                return 1;
            } else if (this.id < o.id) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}


