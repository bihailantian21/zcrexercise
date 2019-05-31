package com.zcr.exercisetest.collections;



public class SxtLinkedList<E>{
    private Node first;
    private Node last;
    private int size;
    //[]
    //["a"]
    //["a","b"]

    /*//没有泛型
    public void add(Object obj){
        Node node=new Node(obj);
        if(first==null){
            node.previous=null;
            node.next=null;
            first=node;
            last=node;
        }else{
            node.previous=last;
            node.next=null;
            last.next=node;
            last=node;
        }
        size++;
    }*/

    //有泛型
    public void add(E element){
        Node node=new Node(element);
        if(first==null){
            node.previous=null;
            node.next=null;
            first=node;
            last=node;
        }else{
            node.previous=last;
            node.next=null;
            last.next=node;
            last=node;
        }
        size++;
    }

    /*这种写法很浪费时间
    public Object get(int index){
        //["a","b","c"]   2
        //first.next.next调用2次next
        if(index<0||index>size-1){
            throw new RuntimeException("索引数字不合法"+index);
        }
        Node temp=first;
        for(int i=0;i<index;i++){//0   1
            temp=temp.next;
        }
        return temp.element;
    }*/

    /*封装之前的代码
    public Object get(int index){
        //["a","b","c"]   2
        //first.next.next调用2次next
        *//*if(index<0||index>size-1){
            throw new RuntimeException("索引数字不合法"+index);
        }*//*
        checkRange(index);
        Node temp=null;

        if(index<=(size>>1)){//小于等于长度的一半，从前往后找
            temp=first;
            for(int i=0;i<index;i++){//0   1
                temp=temp.next;
            }
        }else{
            temp=last;
            for(int i=size-1;i>index;i--){//从后往前找
                temp=temp.previous;
            }
        }
        return temp.element;
    }*/

    //封装之后的代码
    public E get(int index){
        //["a","b","c"]   2
        //first.next.next调用2次next
        checkRange(index);
        Node temp=getNode(index);

        return temp!=null?(E)temp.element:null;
    }



    public Node getNode(int index){//封装一下查找节点
        checkRange(index);
        Node temp=null;

        if(index<=(size>>1)){//小于等于长度的一半，从前往后找
            temp=first;
            for(int i=0;i<index;i++){//0   1
                temp=temp.next;
            }
        }else{
            temp=last;
            for(int i=size-1;i>index;i--){//从后往前找
                temp=temp.previous;
            }
        }
        return temp;
    }


    public void remove(int index){
        checkRange(index);
        Node temp=getNode(index);
        if(temp!=null){
            Node up=temp.previous;
            Node down=temp.next;
            if(up!=null){
                up.next=down;
            }
            if(down!=null){
                down.previous=up;
            }
            if(index==0){//被删除的元素是第一个元素
                first=down;
            }
            if(index==size-1){//被删除的元素是最后一个元素
                last=up;
            }
            size--;
        }
    }

    /*//没有泛型
    public void add(int index,Object obj) {
        Node newNode = new Node(obj);
        Node temp = getNode(index);
        if (temp != null) {
            Node up = temp.previous;
            up.next = newNode;
            newNode.previous = up;
            newNode.next = temp;
            temp.previous = newNode;
        }
    }*/

    //有泛型
    public void add(int index,E element) {
        checkRange(index);
        Node newNode = new Node(element);
        Node temp = getNode(index);
        if (temp != null) {
            Node up = temp.previous;
            up.next = newNode;
            newNode.previous = up;
            newNode.next = temp;
            temp.previous = newNode;
        }
    }

    private void checkRange(int index){//所有用到索引的地方
        if(index<0||index>size-1){
            throw new RuntimeException("索引数字不合法"+index);
        }
    }


    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        Node temp = first;
        while (temp!=null){
            sb.append(temp.element+",");
            temp=temp.next;
        }
        sb.setCharAt(sb.length()-1,']');
        return sb.toString();
    }

    public static void main(String[] args){
        SxtLinkedList list=new SxtLinkedList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        System.out.println(list);//["a","b","c"]

        System.out.println(list.get(2));//c

        list.remove(4);
        System.out.println(list);

        list.add(2,"sd");
        System.out.println(list);
    }

}

class Node {
    Node previous;
    Node next;
    Object element;

    public Node(Node previous, Node next,Object element) {
        super();
        this.previous = previous;
        this.next = next;
        this.element = element;
    }

    public Node(Object element) {
        super();
        this.element = element;
    }
}


