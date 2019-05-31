package com.zcr.exercisetest.collections;

public class SxtArrayList<E> {
    private Object[] elementData;
    private int size;
    private static final int DEFALT_CAPACITY=10;

    public SxtArrayList(){
        elementData=new Object[DEFALT_CAPACITY];
    }

    public SxtArrayList(int capacity){
        /*
        if(capacity<0){
            throw new RuntimeException("容器的容量不能为负数");
        }else if(capacity == 0){
            elementData = new Object[Defalt_CAPACITY];
        }else{
             elementData=new Object[capacity];
        }
         */
        elementData=new Object[capacity];
    }

    public E get(int index){
        check(index);
        return (E)elementData[index];
    }

    public void set(E element,int index){
        //索引合法判断[0,size)
        if(index<0||index>size-1){
            throw new RuntimeException("索引不合法！"+index);
        }
        elementData[index]=element;
    }

    //加个索引越界方法，让别的用得到的方法去调用它
    public void check(int index){
        //索引合法判断[0,size)
        if(index<0||index>size-1){
            throw new RuntimeException("索引不合法！"+index);
        }
    }

    public void remove(E element){
        //element，将它和所有元素挨个比较，获得第一个比较为true的，返回
        for(int i=0;i<size;i++){
            if(element.equals(get(i))){//容器中所有的比较操作都用eauals而不是==
                //将该元素从此处移除
                remove(i);
            }
        }
    }

    public void remove(int index){
        //0 1 2 3 4 5 6 7 length index
        //a,b,c,d,e,f,g,h  8      3
        //a,b,c,e,f,g,h    7
        //相当于把后四位拷贝到了向前一位的位置 8-3-1=4 length-index-1
        int numMoved=elementData.length-index-1;
        if(numMoved>0){
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
            elementData[size-1]=null;//这两行合并为一行elementData[--size]=null
            size--;
        }else{//其实这个else可以不要
            //最后一个，直接删掉
            elementData[size-1]=null;
            size--;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0?true:false;
    }






    /*//没有使用泛型
    public void add(Object obj){
        elementData[size++]=obj;
    }*/

    /*//使用泛型
    public void add(E element){
        elementData[size++]=element;
    }*/

    //数组扩容
    public void add(E element){
       if(size < elementData.length){//如果目前集合的空间不够了，我们再去扩容。
            Object[] newArray=new Object[elementData.length+(elementData.length>>1)];//10+10/2
            System.arraycopy(elementData,0,newArray,0,elementData.length);//把原数组拷贝进了现在的数组
            elementData=newArray;

            elementData[size++]=element;
       }
    }





    public String toString(){
        StringBuilder sb=new StringBuilder ();
        sb.append("[");
        for(int i=0;i<size;i++){
            sb.append(elementData[i]+",");
        }
        sb.setCharAt(sb.length()-1,']');
        //sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args){
        SxtArrayList s1=new SxtArrayList (20);
        s1.add("aa");
        s1.add("bb");
        System.out.println(s1);//[aa,bb]

        for (int i = 0; i < 30; i++) {
            s1.add("zcr"+i);
        }
        System.out.println(s1);
        s1.set("12222",4);
        System.out.println(s1.get(4));

        s1.remove(9);
        System.out.println(s1);
        s1.remove("zcr20");
        System.out.println(s1);

    }



}
