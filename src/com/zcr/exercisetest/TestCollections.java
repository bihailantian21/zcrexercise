package com.zcr.exercisetest;

import com.zcr.exercisefundation.Car;
import com.zcr.exercisefundation.Student;

import java.util.*;

/**
 * @author zcr
 * @date 2019/5/6-21:26
 */
public class TestCollections {
    public static void main(String args[]) {
        /*数组就是一种容器，可以在其中放置对象或基本类型数据。
        数组的优势：是一种简单的线性序列，可以快速地访问数组元素，效率高。如果从效率和类型检查的角度讲，数组是最好的。
        数组的劣势：不灵活。容量需要事先定义好，不能随着需求的变化而扩容。
        容器的接口层次结构图：
        集合(Collection)
                Set->HashSet
                List->ArrayList\LinkedList
        Map         ->HashMap*/
        //泛型Generics
        /*泛型是JDK1.5以后增加的，它可以帮助我们建立类型安全的集合。在使用了泛型的集合中，遍历时不必进行强制类型转换。
        JDK提供了支持泛型的编译器，将运行时的类型检查提前到了编译时执行，提高了代码可读性和安全性。

        泛型的本质就是“数据类型的参数化”。 我们可以把“泛型”理解为数据类型的一个占位符(形式参数)，
        即告诉编译器，在调用泛型时必须传入实际类型。

        容器相当于一个大桶，到容器中放东西时。
        泛型相当于在容器上贴个标签，规定每个容器中放什么东西。放的时候放什么，取得时候就取什么。*/
        //自定义泛型
        /*我们可以在类的声明处增加泛型列表，如：<T,E,V,K>。
        此处，字符可以是任何标识符，一般采用这3个字母。
        */
        //定义的类不加泛型的时候
        MyCollection1 mc1 = new MyCollection1();
        mc1.set("zcr", 0);
        mc1.set(8888, 1);
        Integer a1 = (Integer) mc1.get(1);
        String b2 = (String) mc1.get(0);
        //定义的类加上泛型之后
        MyCollection<String> mc = new MyCollection<String>();// 这里的”String”就是实际传入的数据类型；
        mc.set("aaa", 0);
        mc.set("bbb", 1);
        String str = mc.get(1); //加了泛型，直接返回String类型，不用强制转换;
        System.out.println(str);

        //容器中使用泛型
        /*容器相关类都定义了泛型，我们在开发和工作中，在使用容器类时都要使用泛型。
        这样，在容器的存储数据、读取数据时都避免了大量的类型判断，非常便捷。
        通过阅读源码，我们发现Collection、List、Set、Map、Iterator接口都定义了泛型
        public interface List<E> extends Collection<E>{
        public interface Set<E> extends Collection<E>{
        public interface Map<K,V>{
        public interface Collection<E> extends Iterable<E>{
        public iterface Iterable<E>{
        因此，我们在使用这些接口及其实现类时，都要使用泛型。
        */
        // 以下代码中List、Set、Map、Iterator都是与容器相关的接口;
        List<String> list1 = new ArrayList<String>();
        Set<Man> mans = new HashSet<Man>();
        Map<Integer, Man> maps = new HashMap<Integer, Man>();
        Iterator<Man> iterator = mans.iterator();

        //Collection接口
        /*Collection 表示一组对象，它是集中、收集的意思。Collection接口的两个子接口是List、Set接口。
        Collection接口中定义的方法：
        add(Object o) remove(Object o) contains(Object o) size isEmpty clear iterator
        containAll(Collection c) addAll(Collection c) removeAll(Collection c) retainAll(Collection c) toArray
        由于List、Set是Collection的子接口，意味着所有List、Set的实现类都有上面的方法。我们下一节中，通过ArrayList实现类来测试上面的方法。*/

        //List
        /*List是有序、可重复的容器。
        有序：List中每个元素都有索引标记。可以根据元素的索引标记(在List中的位置)访问元素，从而精确控制这些元素。
        可重复：List允许加入重复的元素。更确切地讲，List通常允许满足 e1.equals(e2) 的元素重复加入容器。

        除了Collection接口中的方法，List多了一些跟顺序(索引)有关的方法：
        add(int Index,Object o) set(int Index,Object o) get(int Index) indexOf(Object o) lastIndexOf(Object o)

        List接口常用的实现类有3个：ArrayList、LinkedList和Vector。*/
        List l1 = new ArrayList();
        l1.add(0, 12);
        l1.add(1, "123");
        Car c19 = new Car();
        l1.add(c19);
        System.out.println(l1);
        l1.remove(2);
        System.out.println(l1);
        l1.set(0, 13);
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
        while (!l2.isEmpty()) {
            System.out.println(l2.removeFirst());//模拟栈
        }
        System.out.println("===========================================ArrayList linkedList");

        //List常用方法
        List<String> list = new ArrayList<String>();
        System.out.println(list.isEmpty()); // true,容器里面没有元素
        list.add("高");
        System.out.println(list.isEmpty()); // false,容器里面有元素
        list.add("小七"); //存的是地址
        list.add("小八");
        System.out.println(list);
        System.out.println("list的大小：" + list.size());
        System.out.println("是否包含指定元素：" + list.contains("小七"));
        list.remove("高");//删的时候删的是地址，而人家本身对象还是存在的
        System.out.println(list);
        //转化成object数组
        Object[] objs = list.toArray();
        System.out.println("转化成Object数组：" + Arrays.toString(objs));
        list.clear();
        System.out.println("清空所有元素：" + list);

        //两个List之间的元素处理
        List<String> list3 = new ArrayList<String>();
        list3.add("高淇");
        list3.add("高小七");
        list3.add("高小八");
        List<String> list2 = new ArrayList<String>();
        list2.add("高淇");
        list2.add("张三");
        list2.add("李四");
        System.out.println(list3.containsAll(list2)); //false list是否包含list2中所有元素
        System.out.println(list3);
        list3.addAll(list2); //将list2中所有元素都添加到list中，list中的元素可以重复 是有序的
        System.out.println(list3);
        list3.removeAll(list2); //从list中删除同时在list和list2中存在的元素
        System.out.println(list3);
        list3.retainAll(list2); //取list和list2的交集
        System.out.println(list3);
        //List中操作索引的常用方法
        List<String> list4 = new ArrayList<String>();
        list4.add("A");
        list4.add("B");
        list4.add("C");
        list4.add("D");
        System.out.println(list4); // [A, B, C, D]
        list4.add(2, "高");
        System.out.println(list4); // [A, B, 高, C, D]
        list4.remove(2);
        System.out.println(list4); // [A, B, C, D]
        list4.set(2, "c");
        System.out.println(list4); // [A, B, c, D]
        System.out.println(list4.get(1)); // 返回：B
        list4.add("B");
        System.out.println(list4); // [A, B, c, D, B]
        System.out.println(list4.indexOf("B")); // 1 从头到尾找到第一个"B"
        System.out.println(list4.lastIndexOf("B")); // 4 从尾到头找到第一个"B"
        //ArrayList特点和底层实现
        /*ArrayList底层是用数组实现的存储。
        特点：查询效率高（ArrayList），增删效率低（LinkedList），线程不安全（Vector）。
        我们一般使用它。查看源码：
        public class ArrayList<E> extends AbstractList<E> implements List<E>,RandomAccess,Cloneable,java.io.Serializable{
            private transient Object[] elementData;
            private int size;
        }
        我们可以看出ArrayList底层使用Object数组来存储元素数据。所有的方法，都围绕这个核心的Object数组来开展。
        我们知道，数组长度是有限的，而ArrayList是可以存放任意数量的对象，长度不受限制，那么它是怎么实现的呢?
        本质上就是通过定义新的更大的数组，将旧数组中的内容拷贝到新数组，来实现扩容。
        ArrayList的Object数组初始化长度为10，如果我们存储满了这个数组，需要存储第11个对象，
        就会定义新的长度更大的数组，并将原数组内容和新的元素一起加入到新数组中，源码如下：
        private void grow(int minCapacity){
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - minCapacity < 0) {
                newCapacity = minCapacity;
            }
            if (newCapacity - MAX_ARRAY_SIZE > 0) {
                newCapacity = hugeCapacity(minCapacity);
                elementData = Arrays.copyOf(elementData,newCapacity);
            }
        }
        private static int hugeCapacity(int minCapacity){
            if (minCapacity < 0)
                throw new OutOfMemoryError();
            return (minCapacity > MAX_ARRAY_SIZE)?Integer.MAX_VALUE;
        }

        //删除元素的源码(原理就是拷贝)
        public E remove(int index){
            rangeCheck(index);

            modCount++;
            E oldValue = elementData(index);

            int numMoved = size - index - 1;
            if(numMoved > 0)
                System.arraycopy(elementData,index+1,elementData,index,numMoved);
            elementData[--size] = null;

            return oldValuee;
        }

        //清空列表的源码
        public void clear(){
            modcount++;

            for(int i= 0 ;i<size;i++){
                elementData[i] = null;
            }
            size = 0;
        }
        */

        //LinkedList
        /*LinkedList底层用双向链表实现的存储。
        特点：查询效率低，增删效率高，线程不安全。
        双向链表也叫双链表，是链表的一种，它的每个数据节点中都有两个指针，分别指向前一个节点和后一个节点。
        所以，从双向链表中的任意一个节点开始，都可以很方便地找到所有节点。
        每个节点都应该有3部分内容：
        class  Node {
            Node  previous;     //前一个节点
            Object  element;    //本节点保存的数据
            Node  next;         //后一个节点
        }
        删除和增加很快！
        查看LinkedList的源码，可以看到里面包含了双向链表的相关代码：

        public class LinkedList<E>
            extends AbstractSequentialList<E>
            implements List<E>, Deque<E>, Cloneable, java.io.Serializable
        {
            transient int size = 0;
            transient Node<E> first;
            transient Node<E> last;
        }

        private static class Node<E> {
            E item;
            Node<E> next;
            Node<E> prev;
            Node(Node<E> prev, E element, Node<E> next) {
                this.item = element;
                this.next = next;
                this.prev = prev;
            }
        }
        */

        //Vetor向量
        /*Vector底层是用数组实现的List，相关的方法都加了同步检查，因此“线程安全,效率低”。 比如，indexOf方法就增加了synchronized同步标记。
        检查我们有没有获得对象锁
        public synchronized int indexOf(Object o,int index){
            if (o == null){
                for (int i = index; i < elementCount; i++) {
                    return i;
                }
            }else {
                for (int i = index; i < elementCount; i++) {
                    if (o.equals(elementCount))
                        return i;
                }
            }
            return -1;
        }*/

        /*老鸟建议如何选用ArrayList、LinkedList、Vector?
        1.需要线程安全时，用Vector。
        2.不存在线程安全问题时，并且查找较多用ArrayList(一般使用它)。
        3.不存在线程安全问题时，增加或删除元素较多用LinkedList。*/

        System.out.println("===========================================ArrayList");

        //Set接口
        /*Set接口继承自Collection，Set接口中没有新增方法，方法和Collection保持完全一致。我们在前面通过List学习的方法，在Set中仍然适用。
        因此，学习Set的使用将没有任何难度。

        Set容器特点：无序、不可重复。
        无序指Set中的元素没有索引，我们只能遍历查找;
        不可重复指不允许加入重复的元素。
        更确切地讲，新元素如果和Set中某个元素通过equals()方法对比为true，则不能加入;甚至，Set中也只能放入一个null元素，不能多个。

        Set常用的实现类有：HashSet、TreeSet等，我们一般使用HashSet。
        */
        Set s1 = new HashSet();//无序
        s1.add(5);
        s1.add(1);
        s1.add(2);
        s1.add(2);//相同的元素不会被加入
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
                return num == 0 ? o1.compareTo(o2) : num;
            }
        });
        s4.add("zjhsakhd");
        s4.add("dnfekjh");
        s4.add("ac");
        s4.add("dre");
        System.out.println(s4);
        Set<Student> s5 = new TreeSet<Student>();
        //创建元素对象
        Student ss1 = new Student(1, "z");
        Student ss2 = new Student(2, "w");
        Student ss3 = new Student(3, "l");
        Student ss4 = new Student(4, "ww");
        Student ss5 = new Student(5, "we");
        Student ss6 = new Student(6, "x");
        //将元素对象添加到集合对象中
        s5.add(ss1);
        s5.add(ss2);
        s5.add(ss3);
        s5.add(ss4);
        s5.add(ss5);
        s5.add(ss6);
        //遍历
        for (Student s : s5) {
            System.out.println(s.getName() + "-----------" + s.getAge());
        }

        System.out.println("===========================================Set");

        //三种遍历方法
        //使用Iterator迭代器遍历容器元素(List/Set/Map)
        /*迭代器为我们提供了统一的遍历容器的方式，参见以下示例代码：
        在这里插入图片描述
                有没有下一个元素
        获得下一个元素
        for (Iterator iter = aList.iterator(); iter.hasNext()😉 {
            iter.next();
        }
        //迭代器遍历List
        List<String> aList = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            aList.add("a" + i);
        }
        System.out.println(aList);
        for (Iterator<String> iter = aList.iterator(); iter.hasNext();) {
            String temp = iter.next();
            System.out.print(temp + "\t");
            if (temp.endsWith("3")) {// 删除3结尾的字符串
                iter.remove();
            }
        }
        System.out.println();
        System.out.println(aList);
        老鸟建议：如果遇到遍历容器时，判断删除元素的情况，使用迭代器遍历!
        //迭代器遍历Set
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < 5; i++) {
            set.add("a" + i);
        }
        System.out.println(set);
        for (Iterator<String> iter = set.iterator(); iter.hasNext();) {
            String temp = iter.next();
            System.out.print(temp + "\t");
        }
        System.out.println();
        System.out.println(set);
        //迭代器遍历Map
        Map<String, String> map = new HashMap<String, String>();
        map.put("A", "高淇");
        map.put("B", "高小七");
        Set<Entry<String, String>> ss = map.entrySet();
        for (Iterator<Entry<String, String>> iterator = ss.iterator(); iterator.hasNext();) {
            Entry<String, String> e = iterator.next();
            System.out.println(e.getKey() + "--" + e.getValue());
        }
        我们也可以通过map的keySet()、valueSet()获得key和value的集合，从而遍历它们。
        //迭代器遍历Map二
        Map<String, String> map = new HashMap<String, String>();
        map.put("A", "高淇");
        map.put("B", "高小七");
        Set<String> ss = map.keySet();
        for (Iterator<String> iterator = ss.iterator(); iterator.hasNext();) {
            String key = iterator.next();
            System.out.println(key + "--" + map.get(key));
        }


        */
        Iterator i1 = s2.iterator();
        while (i1.hasNext()) {
            System.out.println(i1.next());
        }
        i1.remove();
        System.out.println(s2);

        for (Object i : s2) {//增强for循环只用来遍历
            System.out.println(i);
        }

        for (int i = 0; i < l1.size(); i++) {
            System.out.println(l1.get(i));
        }

        //遍历集合的方法总结
        List<String> listt = new ArrayList<String>();
        listt.add("遍历list1");
        listt.add("遍历list2");
        listt.add("遍历list3");
        listt.add("遍历list4");
        System.out.println(listt);
        //遍历List方法一：普通for循环
        for (int i = 0; i < listt.size(); i++) {//list为集合的对象名
            String temp = (String) listt.get(i);
            System.out.println(temp);
        }
        //遍历List方法二：增强for循环(使用泛型!)
        for (String temp : listt) {
            System.out.println(temp);
        }
        //遍历List方法三：使用Iterator迭代器(1)
        for (Iterator iter = listt.iterator(); iter.hasNext(); ) {
            String temp = (String) iter.next();
            System.out.println(temp);
        }
        //遍历List方法四：使用Iterator迭代器(2)
        Iterator iter = listt.iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            iter.remove();//如果要遍历时，删除集合中的元素，建议使用这种方式！
            System.out.println(obj);
        }

        Set<String> set = new HashSet<String>();
        set.add("遍历set1");
        set.add("遍历set2");
        set.add("遍历set3");
        System.out.println(set);
        //遍历Set方法一：增强for循环
        for (String temp : set) {
            System.out.println(temp);
        }
        //遍历Set方法二：使用Iterator迭代器
        for (Iterator iter2 = set.iterator(); iter2.hasNext(); ) {
            String temp = (String) iter2.next();
            System.out.println(temp);
        }

        Map<Integer, Man> mapss = new HashMap<Integer, Man>();
        mapss.put(1, new Man(12, "12111"));
        mapss.put(2, new Man(23, "23222"));
        System.out.println(mapss);
        //遍历Map方法一：根据key获取value
        Set<Integer> keySet = mapss.keySet();
        for (Integer id : keySet) {
            System.out.println(mapss.get(id).name);
        }
        //遍历Map方法二：使用entrySet
        Set<Map.Entry<Integer, Man>> ss = mapss.entrySet();
        for (Iterator iterator3 = ss.iterator(); iterator3.hasNext(); ) {
            Map.Entry e = (Map.Entry) iterator3.next();
            System.out.println(e.getKey() + "--" + e.getValue());
        }

        System.out.println("===========================================三种遍历方法");

        //Map接口
        /*Map就是用来存储“键(key)-值(value) 对”的。 Map类中存储的“键值对”通过键来标识，所以“键对象”不能重复。
        Map 接口的实现类有HashMap、TreeMap、HashTable、Properties等。
        Map接口中常用的方法：
        put(Object key,Object value) get(Object key) remove(Object key) containsKey(Object key) containsValue(Object value)
            size() isEmpty() putAll(Map t) clear()*/

        //HashMap和HashTable
        /*HashMap采用哈希算法实现，是Map接口最常用的实现类。 由于底层采用了哈希表存储数据，我们要求键不能重复，
        如果发生重复，新的键值对会替换旧的键值对。 HashMap在查找、删除、修改方面都有非常高的效率。*/
        Map<Integer, String> mm1 = new HashMap<Integer, String>();
        Map<Integer, String> mm2 = new HashMap<Integer, String>();
        mm1.put(1, "one");
        mm1.put(2, "two");
        mm1.put(3, "three");
        mm2.put(1, "一");
        mm2.put(2, "二");
        System.out.println(mm1.size());//3
        System.out.println(mm1.containsKey(1));//true
        System.out.println(mm2.containsValue("two"));//false
        mm1.put(3, "third"); //键重复了，则会替换旧的键值对。重复是根据equals()判断的
        Map<Integer, String> mm3 = new HashMap<Integer, String>();
        mm3.putAll(mm1);
        mm3.putAll(mm2);
        System.out.println("mm1:" + mm1);//m1:{1=one,2=two,3=third}
        System.out.println("mm2:" + mm2);//m2:{1=一，2=二}
        System.out.println("mm3:" + mm3);//m3:{1=一，2=二，3=third}

        /*HashTable类和HashMap用法几乎一样，底层实现几乎一样，只不过HashTable的方法添加了synchronized关键字确保线程同步检查，效率较低。
        HashMap与HashTable的区别
        1.HashMap: 线程不安全，效率高。允许key或value为null。
        2.HashTable: 线程安全，效率低。不允许key或value为null。*/
        Employee e1 = new Employee(1001, "xiaosa", 500);
        Employee e2 = new Employee(1002, "xiaods", 500);
        Employee e3 = new Employee(1003, "xiaoafaf", 500);
        Map<Integer, Employee> map = new HashMap<Integer, Employee>();
        map.put(1001, e1);
        map.put(1002, e2);
        map.put(1003, e3);
        Employee emp = map.get(1001);
        System.out.println(emp.getEname());
        System.out.println(emp);


        Map m1 = new HashMap();//无序
        m1.put("name", "zcr");
        m1.put("age", 1);
        m1.put("city", "beijign");
        m1.put("gender", "woman");
        System.out.println(m1.get("name"));
        m1.remove("age");
        System.out.println(m1);
        m1.put("name", "wx");
        System.out.println(m1);

        Map m2 = new LinkedHashMap();//有序
        m2.put(1, "zcr");
        m2.put(2, "sd");
        m2.put(3, "eee");
        m2.put(4, "ds");
        System.out.println(m2);

        Map m3 = new TreeMap();//有序
        m3.put(1, "12");
        m3.put(2, "234");
        m3.put(3, "122");
        System.out.println(m3);

        System.out.println("======================================================Map");

        Set ms1 = m1.keySet();
        System.out.println(ms1);
        for (Object i : ms1) {
            System.out.println(i + "---" + m1.get(i));
        }

       /* Set ms2 = m1.entrySet();//不写Map.Entry<String,String>不行
        System.out.println(ms2);
        for(Object i : ms2){
            System.out.println();
        }*/
        Set<Map.Entry<String, String>> ms2 = m1.entrySet();
        System.out.println(ms2);
        for (Map.Entry<String, String> i : ms2) {
            System.out.println(i.getKey() + "---" + i.getValue());
        }

        Iterator<Map.Entry<String, String>> it = m1.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }

        System.out.println("===================================================Set EntrySett");

        //Collections工具类
        /*类 java.util.Collections 提供了对Set、List、Map进行排序、填充、查找元素的辅助方法。
        1.void sort(List) //对List容器内的元素排序，排序的规则是按照升序进行排序。
        2.void shuffle(List) //对List容器内的元素进行随机排列。
        3.void reverse(List) //对List容器内的元素进行逆续排列 。
        4.void fill(List, Object) //用一个特定的对象重写整个List容器。
        5.int binarySearch(List, Object)//对于顺序的List容器，采用折半查找的方法查找特定对象。*/
        List<String> aList = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            aList.add("a" + i);
        }
        System.out.println(aList);

        Collections.shuffle(aList); // 随机排列
        System.out.println(aList);
        Collections.reverse(aList); // 逆续
        System.out.println(aList);
        Collections.sort(aList); // 排序
        System.out.println(aList);
        System.out.println(Collections.binarySearch(aList, "a2"));
        Collections.fill(aList, "hello");
        System.out.println(aList);

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
        List<String> ll2 = new ArrayList();
        ll2.add("ab");
        ll2.add("aaa");
        ll2.add("sss");
        ll2.add("bdjd");
        ll2.add("asdff");
        System.out.println(ll2);
        Collections.sort(ll2);
        System.out.println(ll2);

        double[] d1 = new double[3];
        d1[0] = 3.3;
        d1[1] = 1.2;
        d1[2] = 2.1;
        System.out.println(Arrays.toString(d1));
        Arrays.sort(d1);
        System.out.println(Arrays.toString(d1));
        System.out.println(Arrays.binarySearch(d1, 1.2));
        System.out.println(Arrays.toString(d1));

        System.out.println("===================================================Collections工具类");
        //表格数据存储_map和list结合存储整张表
        //方式一：每一行数据使用一个map,整个表格使用一个list
        //map表示一行数据，多行数据是多个map，将多个map放到list中
        Map<String, Object> row1 = new HashMap<>();
        row1.put("id", 1001);
        row1.put("name", "张三");
        row1.put("薪水", 2000);
        row1.put("入职日期", 20180505);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("id", 1002);
        row2.put("name", "李四");
        row2.put("薪水", 3000);
        row2.put("入职日期", 20050505);

        List<Map<String, Object>> table1 = new ArrayList<>();
        table1.add(row1);
        table1.add(row2);
        for (Map<String, Object> row : table1) {
            Set<String> keysett = row.keySet();
            for (String key : keysett) {
                System.out.println(key + ":" + row.get(key));
            }
        }
        System.out.println("练习结束");
        //表格数据存储_javabean和list结合存储整张表
        //每一行数据使用一个javabean对象，整个表格使用一个list
        //(int id, String name, double salary, String hiredate)
        User user1 = new User(1001,"张三",20000,"2018.5.5");
        User user2 = new User(1002,"李四",30000,"2005.5.5");
        User user3 = new User(1003,"王五",40000,"2001.5.5");
        List<User> listtt = new ArrayList<User>();
        listtt.add(user1);
        listtt.add(user2);
        listtt.add(user3);
        for(User u:listtt){
            System.out.println(u);
            Map<Integer,User> mapp=new HashMap<Integer,User>();
            mapp.put(1001,user1);
            mapp.put(1002,user2);
            mapp.put(1003,user3);
            Set<Integer> keyset=mapp.keySet();
            for(Integer key:keyset){
                System.out.println(key+"==="+map.get(key));
            }
        }


        //总结
        /*
        1。List中的元素有顺序，可重复。常用的实现类有ArrayList、LinkedList和 vector。
            Ø ArrayList特点：查询效率高，增删效率低，线程不安全。
            Ø LinkedList特点：查询效率低，增删效率高，线程不安全。
            Ø vector特点：线程安全,效率低,其它特征类似于ArrayList。
        2。Set中的元素没有顺序，不可重复。常用的实现类有HashSet和TreeSet。
            Ø HashSet特点：采用哈希算法实现,查询效率和增删效率都比较高。
            Ø TreeSet特点：内部需要对存储的元素进行排序。因此，我们对应的类需要实现Comparable接口。这样，才能根据compareTo()方法比较对象之间的大小，才能进行内部排序。
        3.实现Map接口的类用来存储键(key)-值(value) 对。Map接口的实现类有HashMap和TreeMap等。
        Map类中存储的键-值对通过键来标识，所以键值不能重复。
        4.Iterator对象称作迭代器，用以方便的实现对容器内元素的遍历操作。
        5.类 java.util.Collections 提供了对Set、List、Map操作的工具方法。
        6.如下情况，可能需要我们重写equals/hashCode方法：
        要将我们自定义的对象放入HashSet中处理。
        要将我们自定义的对象作为HashMap的key处理。
        放入Collection容器中的自定义对象后，可能会调用remove、contains等方法时。
        7.JDK1.5以后增加了泛型。泛型的好处：
        向集合添加数据时保证数据安全。
        遍历集合元素时不需要强制转换。*/


    }
}

//不加泛型的时候
class MyCollection1 {
    Object[] objs = new Object[5];

    public Object get(int index){
        return objs[index];
    }

    public void set(Object obj,int index){
        objs[index] = obj;
    }
}

//泛型类的声明
class MyCollection<E> {// E:表示泛型;
    Object[] objs = new Object[5];

    public E get(int index) {// E:表示泛型;
        return (E) objs[index];
    }
    public void set(E e, int index) {// E:表示泛型;
        objs[index] = e;
    }
}

class Employee{
    private int id;
    private String ename;
    private double salary;
    //构造方法、get/set方法、tostring

    public Employee(int id, String ename, double salary) {
        this.id = id;
        this.ename = ename;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", ename='" + ename + '\'' +
                ", salary=" + salary +
                '}';
    }
}


class User{
    private int id;
    private String name;
    private double salary;
    private String hiredate;
    //构造方法、setget、tostring

    public User(int id, String name, double salary, String hiredate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.hiredate = hiredate;
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

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", hiredate='" + hiredate + '\'' +
                '}';
    }
}
