package com.zcr.exercisetest;

public class SxtHashMap<K,V> {
    public static void main(String[] args) {
        //HashMap底层实现详解
        /*HashMap底层实现采用了哈希表，这是一种非常重要的数据结构。
        对于我们以后理解很多技术都非常有帮助(比如：redis数据库的核心技术和HashMap一样)，因此，非常有必要让大家理解。
        数据结构中由数组和链表来实现对数据的存储，他们各有特点。
        (1) 数组：占用空间连续。 寻址容易，查询速度快。但是，增加和删除效率非常低。
        (2) 链表：占用空间不连续。 寻址困难，查询速度慢。但是，增加和删除效率非常高。
        那么，我们能不能结合数组和链表的优点(即查询快，增删效率也高)呢? 答案就是“哈希表”。 哈希表的本质就是“数组+链表”。

        老鸟建议
        对于本章中频繁出现的“底层实现”讲解，建议学有余力的童鞋将它搞通。刚入门的童鞋如果觉得有难度，可以暂时跳过。
        入门期间，掌握如何使用即可，底层原理是扎实内功，便于大家应对一些大型企业的笔试面试。

        Hashmap基本结构讲解
        哈希表的基本结构就是“数组+链表”。我们打开HashMap源码，发现有如下两个核心内容：
        public class HashMap<K,V>
            extends AbstractMap<K,V>
            implements Map<K,V>, Cloneable, Serializable {

            private static final long serialVersionUID = 362498820763181265L;
        static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
        static final int MAXIMUM_CAPACITY = 1 << 30;
        static final float DEFAULT_LOAD_FACTOR = 0.75f;

        其中的Entry[] table 就是HashMap的核心数组结构，我们也称之为“位桶数组”。我们再继续看Entry是什么，源码如下：
        static class Node<K,V> implements Map.Entry<K,V> {
            final int hash;
            final K key;
            V value;
            Node<K,V> next;

            Node(int hash, K key, V value, Node<K,V> next) {
                this.hash = hash;
                this.key = key;
                this.value = value;
                this.next = next;
            }

            public final K getKey()        { return key; }
            public final V getValue()      { return value; }
            public final String toString() { return key + "=" + value; }

            public final int hashCode() {
                return Objects.hashCode(key) ^ Objects.hashCode(value);
            }

            public final V setValue(V newValue) {
                V oldValue = value;
                value = newValue;
                return oldValue;
            }

            public final boolean equals(Object o) {
                if (o == this)
                    return true;
                if (o instanceof Map.Entry) {
                    Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                    if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                        return true;
                }
                return false;
            }
        }
        一个Entry对象存储了：
        1.key：键对象 value：值对象
        2.next:下一个节点
        3.hash: 键对象的hash值
        显然每一个Entry对象就是一个单向链表结构，我们使用图形表示一个Entry对象的典型示意：
        hash key value next->hash key value next->hash key value next->hash key value next
        然后，我们画出Entry[]数组的结构(这也是HashMap的结构)：
        Entry[] table
        0hash key value next->hash key value next->hash key value next
        1
        2hash key value next->hash key value next->hash key value next
        3
        4hash key value next
        5
        6hash key value next->hash key value next
        .
        .
        15数组默认初始大小16，限于篇幅省略


        存储数据过程put(key,value)
        明白了HashMap的基本结构后，我们继续深入学习HashMap如何存储数据。此处的核心是如何产生hash值，该值用来对应数组的存储位置。
        我们的目的是将”key-value两个对象”成对存放到HashMap的Entry[]数组中。参见以下步骤：
        (1) 获得key对象的hashcode
        首先调用key对象的hashcode()方法，获得hashcode。获得地址
        (2) 根据hashcode计算出hash值(要求在[0, 数组长度-1]区间)
        hashcode是一个整数，我们需要将它转化成[0, 数组长度-1]的范围。我们要求转化后的hash值尽量均匀地分布在[0,数组长度-1]这个区间，减少“hash冲突”
        i. 一种极端简单和低下的算法是：
        hash值 = hashcode/hashcode;
        也就是说，hash值总是1。意味着，键值对对象都会存储到数组索引1位置，这样就形成一个非常长的链表。相当于每存储一个对象都会发生“hash冲突”，
        HashMap也退化成了一个“链表”。
        ii. 一种简单和常用的算法是(相除取余算法)：
        hash值 = hashcode%数组长度
        这种算法可以让hash值均匀的分布在[0,数组长度-1]的区间。 早期的HashTable就是采用这种算法。但是，这种算法由于使用了“除法”，效率低下。
        JDK后来改进了算法。首先约定数组长度必须为2的整数幂，这样采用位运算即可实现取余的效果：hash值 = hashcode&(数组长度-1)。
        iii. 如下为我们自己测试简单的hash算法：
        (3) 生成Entry对象
        如上所述，一个Entry对象包含4部分：key对象、value对象、hash值、指向下一个Entry对象的引用。我们现在算出了hash值。下一个Entry对象的引用为null。
        (4) 将Entry对象放到table数组中
        如果本Entry对象对应的数组索引位置还没有放Entry对象，则直接将Entry对象存储进数组;如果对应索引位置已经有Entry对象，
        则将已有Entry对象的next指向本Entry对象，形成链表。

        总结如上过程：
        当添加一个元素(key-value)时，首先计算key的hash值，以此确定插入数组中的位置，但是可能存在同一hash值的元素已经被放在数组同一位置了，
        这时就添加到同一hash值的元素的后面，他们在数组的同一位置，就形成了链表，同一个链表上的Hash值是相同的，所以说数组存放的是链表。
        JDK8中，当链表长度大于8时，链表就转换为红黑树，这样又大大提高了查找的效率。

        ▪ 取数据过程get(key)
        我们需要通过key对象获得“键值对”对象，进而返回value对象。明白了存储数据过程，取数据就比较简单了，参见以下步骤：
        (1) 获得key的hashcode，通过hash()散列算法得到hash值，进而定位到数组的位置。
        (2) 在链表上挨个比较key对象。 调用equals()方法，将key对象和链表上所有节点的key对象进行比较，直到碰到返回true的节点对象为止。
        (3) 返回equals()为true的节点对象的value对象。
        明白了存取数据的过程，我们再来看一下hashcode()和equals方法的关系：
        Java中规定，两个内容相同(equals()为true)的对象必须具有相等的hashCode。因为如果equals()为true而两个对象的hashcode不同;那在整个存储过程中就发生了悖论。

        ▪ 扩容问题
        HashMap的位桶数组，初始大小为16。实际使用时，显然大小是可变的。如果位桶数组中的元素达到(0.75*数组 length)， 就重新调整数组大小变为原来2倍大小。
        扩容很耗时。扩容的本质是定义新的更大的数组，并将旧数组内容挨个拷贝到新数组中。

        ▪ JDK8将链表在大于8情况下变为红黑二叉树
        JDK8中，HashMap在存储一个元素时，当对应链表长度大于8时，链表就转换为红黑树，这样又大大提高了查找的效率。
        下一节，我们简单介绍一个二叉树。同时，也便于大家理解TreeMap的底层结构。

        */

        int h = 25860399;
        int length = 16;//length为2的整数次幂,则h&(length-1)相当于对length取模
        System.out.println(myHash(h, length));

        //手工实现HashMap_基本结构_put存储键值对
        SxtHashMap m1=new SxtHashMap ();
        m1.put(1,"one");
        m1.put(2,"two");
        m1.put(3,"three");
        //m1.put(3,"third");//这两个错误有待解决,总说是有空指针异常
        /*m1.put(17,"onoene");
        m1.put(18,"twotwo");
        m1.put(19,"sdddd");*/
        System.out.println(m1);
        for(int i=10;i<100;i++){
            System.out.println(myHash(i,16));
        }
        System.out.println(m1.get(3));


    }
    Node2[] table;//位桶数组
    int size;//存放的键值对的个数
    public SxtHashMap(){
        table=new Node2[16];//长度一般定义为2的整数幂
    }
    public void put(K key,V value){
        //定义了新的节点对象
        Node2 newNode=new Node2();
        newNode.hash=myHash(key.hashCode(),table.length);
        newNode.key=key;
        newNode.value=value;
        newNode.next=null;

        Node2 temp=table[newNode.hash];
        boolean keyRepeat = false;//是否有重复
        Node2 iterLast=null;//正在遍历的最后一个元素
        if(temp==null){
            table[newNode.hash]=newNode;//此处数组元素为空，则直接将新节点放进去
            size++;
        }else{
            //此处数组元素不为空，则遍历对应链表
            while (temp!=null){
                //判断Key如果重复则覆盖
                if (temp.key.equals(key)){
                    keyRepeat = true;
                    temp.value = value;//只是覆盖value即可，其它的值保持不变
                    break;
                }else {
                    //key不重复，则遍历下一个
                    iterLast = temp;
                    temp = temp.next;
                }
            }
            if(keyRepeat){//如果key不重复，则添加到链表最后
                iterLast.next=newNode;
                size++;
            }
        }

    }
    /**
     * @param h  任意整数
     * @param length 长度必须为2的整数幂
     * @return
     */
    public static  int myHash(int h,int length){
        //System.out.println(h&(length-1));//length为2的整数幂情况下，和取余的值一样
        //System.out.println(h%length);//取余数
        return h&(length-1);//位运算
        /*运行如上程序，我们就能发现直接取余(h%length)和位运算(h&(length-1))结果是一致的。
        事实上，为了获得更好的散列效果，JDK对hashcode进行了两次散列处理(核心目标就是为了分布更散更均匀)，源码如下：
        static int hash(int h){
            h^=(h>>>20)^(h>>>12);
            return h^(h>>>7)^(h>>>4);
        }*/

    }

    public String toString(){
        //(10:aa,20:bb)
        StringBuilder sb=new StringBuilder("{");
        //遍历buket数组
        for(int i=0;i<table.length;i++){
            Node2 temp=table[i];
            //遍历链表
            while(temp!=null){
                sb.append(temp.key+":"+temp.value+",");
                temp=temp.next;
            }
        }
        sb.setCharAt(sb.length()-1,'}');
        return sb.toString();
    }

    public V get(K key){
        //获得相应的hash值，然后挨个比较
        int hash=myHash(key.hashCode(),table.length);
        V value=null;
        if(table[hash]!=null){
            Node2 temp=table[hash];
            while(temp!=null){
                if(temp.key.equals(key)){
                    value = (V) temp.value;
                    break;
                }else{
                    temp=temp.next;
                }
            }
        }
        return value;
    }

}
class Node2{
    int hash;
    Object  key;
    Object value;
    Node2 next;
}