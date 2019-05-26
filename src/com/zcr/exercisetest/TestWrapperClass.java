package com.zcr.exercisetest;

public class TestWrapperClass {
    public static void main(String[] args) {
        //包装类
        /*Java是面向对象的语言，但并不是“纯面向对象”的。
        但是我们在实际应用中经常需要将基本数据转化成对象，以便于操作。
        比如：将基本数据类型存储到Object[]数组或集合中的操作等等。

        为了解决这个不足，Java在设计类时为每个基本数据类型设计了一个对应的类进行代表，
        这样八个和基本数据类型对应的类统称为包装类(Wrapper Class)。

        包装类均位于java.lang包，八种包装类和基本数据类型的对应关系如：
        byte-Byte boolean-Boolean short-Short char-Character int-Integer long-Long float-Float double-Double
        (BigDecimal BigInteger)
        在这八个类中，除了Character和Boolean以外，其他的都是“数字型”，“数字型”都是java.lang.Number的子类。Number类是抽象类，
        因此它的抽象方法，所有子类都需要提供实现。Number类提供了抽象方法：intValue()、longValue()、floatValue()、doubleValue()，
        意味着所有的“数字型”包装类都可以互相转型。
        */
        Integer i = new Integer(10);
        Integer j = new Integer(50);

        //包装类的用途
        /*1.作为和基本数据类型对应的类型存在，方便涉及到对象的操作，如Object[]、集合等的操作。
        2.包含每种基本数据类型的相关属性如最大值、最小值等，以及相关的操作方法
        (这些操作方法的作用是在基本数据类型、包装类对象、字符串之间提供相互之间的转化!)。
        方法
        valueOf 基本数据类型转成包装类对象
        intValue 把包装类对象转成基本数据类型
        parseInt 字符串转化成包装类Integer对象
        toString 包装类 Integer对象转化成字符串
        MAX_VALUE
        */
        //基本数据类型转成包装类对象
        Integer a = new Integer(3);
        Integer b = Integer.valueOf(30);//官方推荐这种写法
        //把包装类对象转成基本数据类型
        int c = b.intValue();
        double d = b.doubleValue();
        //把字符串转成包装类对象
        Integer e = new Integer("9999");
        Integer f = Integer.parseInt("999888");
        //把包装类对象转成字符串
        String str = f.toString();//
        //常见的常量
        System.out.println("int类型最大的整数"+Integer.MAX_VALUE);

        //自动装箱和拆箱
        /*自动装箱和拆箱就是将基本数据类型和包装类之间进行自动的互相转换。JDK1.5后，Java引入了自动装箱(autoboxing)/拆箱(unboxing)。

        自动装箱：
        基本类型的数据处于需要对象的环境中时，会自动转为“对象”。
        我们以Integer为例：在JDK1.5以前，这样的代码 Integer i = 5 是错误的，必须要通过Integer i = new Integer(5)
        这样的语句来实现基本数据类型转换成包装类的过程;而在JDK1.5以后，Java提供了自动装箱的功能，因此只需Integer i = 5这样的语句就能实现基本数据类型转换成包装类，
        这是因为JVM为我们执行了**Integer i = Integer.valueOf(5)**这样的操作，这就是Java的自动装箱。

        自动拆箱：
        每当需要一个值时，对象会自动转成基本数据类型，没必要再去显式调用**intValue()、doubleValue()**等转型方法。
        如 Integer i = 5;int j = i; 这样的过程就是自动拆箱。

        我们可以用一句话总结自动装箱/拆箱：
        自动装箱过程是通过调用包装类的valueOf()方法实现的，而自动拆箱过程是通过调用包装类的 xxxValue()方法实现的
            (xxx代表对应的基本数据类型，如intValue()、doubleValue()等)。
        所以自动装箱与拆箱的功能是所谓的“编译器蜜糖(Compiler Sugar)”，虽然使用这个功能很方便，但在程序运行阶段您得了解Java的语义。
        自动装箱与拆箱的功能事实上是编译器来帮的忙，编译器在编译时依据您所编写的语法，决定是否进行装箱或拆箱动作，如下：*/
        Integer a1 = 234;//自动装箱。相当于编译器自动为您做以下的语法编译。Integer a = Integer.valueOf(234);
        int b1 = a1;//自动拆箱。相当于编译器自动为您做以下的语法编译。int b = a.intValue();
        Integer c1 = null;//null表示i没有指向任何对象的实体，但作为对象名称是合法的(不管这个对象名称存是否指向了某个对象的实体)。
        // 由于实际上i并没有指向任何对象的实体，所以也就不可能操作intValue()方法，这样上面的写法在运行时就会出现NullPointerException错误。
        //int d1 = c1;//包装类空指针异常问题。空指针错误是对象为空，但是你调用了它的方法。因为c1是对象，你自动调用了c1的intValue()方法。所以我们这里判断一下。
        if (c1 != null) {
            int d1 = c1;//自动拆箱。调用了：c.intValue()
        }

        //包装类的缓存问题
        /*整型、char类型所对应的包装类，在自动装箱时，对于-128~127之间的值会进行缓存处理，其目的是提高效率。

        缓存处理的原理为：如果数据在-128~127这个区间，那么在类加载时就已经为该区间的每个数值创建了对象，并将这256个对象存放到一个名为cache的数组中。
        每当自动装箱过程发生时(或者手动调用valueOf()时)，就会先判断数据是否在该区间，如果在则直接获取数组中对应的包装类对象的引用，
        如果不在该区间，则会通过new调用包装类的构造方法来创建对象。

        下面我们以Integer类为例，看一看Java为我们提供的源码，加深对缓存技术的理解
        这段代码中我们需要解释下面几个问题：
        1.IntegerCache类为Integer类的一个静态内部类，仅供Integer类使用。
        2.一般情况下 IntegerCache.low为-128，IntegerCache.high为127，IntegerCache.cache为内部类的一个静态属性，如下所示。
        Integer类相关源码如下：
        public static Integer valueOf(int i) {
            if (i >= IntegerCache.low && i <= IntegerCache.high)
                return IntegerCache.cache[i + (-IntegerCache.low)];
            return new Integer(i);
        }
        IntegerCache类相关源码如下：
        由下面的源码我们可以看到，静态代码块的目的就是初始化数组cache的，这个过程会在类加载时完成。
        private static class IntegerCache {
            static final int low = -128;
            static final int high;
            static final Integer cache[];
            static {
                // high value may be configured by property
                int h = 127;
                String integerCacheHighPropValue =
                        sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
                if (integerCacheHighPropValue != null) {
                    try {
                        int i = parseInt(integerCacheHighPropValue);
                        i = Math.max(i, 127);
                        // Maximum array size is Integer.MAX_VALUE
                        h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
                    } catch( NumberFormatException nfe) {
                        // If the property cannot be parsed into an int, ignore it.
                    }
                }
                high = h;
                cache = new Integer[(high - low) + 1];
                int j = low;
                for(int k = 0; k < cache.length; k++)
                    cache[k] = new Integer(j++);

                // range [-128, 127] must be interned (JLS7 5.1.7)
                assert IntegerCache.high >= 127;
            }
            private IntegerCache() {}
        }
        */
        //缓存[-128,127]之间的数字。实际就是系统初始的时候，创建了[-128,127]之间的一个缓存数组。
        //当我们调用valueOf()的时候。首先检查是否在[-128,127]之间。如果在这个范围则直接从缓存数组中拿出已经创
        //创建好的对象。如果不在这个范围内，则创建新的Integer对象。
        Integer in1 = -128;
        Integer in2 = -128;
        System.out.println(in1 == in2);//true 因为123在缓存范围内
        System.out.println(in1.equals(in2));//true比较的是值

        Integer in3 = 1234;
        Integer in4 = 1234;//这两个是独立的对象，所以用等号的话，他们是不相等的
        System.out.println(in3 == in4);//false 因为1234不在缓存范围内
        System.out.println(in3.equals(in4));//true，equals()方法是比较数值
        /*1.JDK1.5以后，增加了自动装箱与拆箱功能，如：Integer i = 100; int j = new Integer(100);
        2.自动装箱调用的是valueOf()方法，而不是new Integer()方法。
        3.自动拆箱调用的xxxValue()方法。
        4.包装类在自动装箱时为了提高效率，对于-128~127之间的值会进行缓存处理。
        超过范围后，对象之间不能再使用==进行数值的比较，而是使用equals方法*/



    }
}
