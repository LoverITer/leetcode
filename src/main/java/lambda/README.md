###  Java8新特性——Lambda表达式

#### 1、Lambda表达式简介

Lambda 表达式是一种匿名函数，简单地说，它是没有声明的方法，也没有访问修饰符、返回值声明和名字。

你可以将其想做一种速记，在你需要使用某个方法的地方写上它。当某个方法只使用一次，而且定义很简短，使用这种速记替代之尤其有效，这样，你就不必在类中费力写声明与方法了。

![](http://image.easyblog.top/15944430236789033877e-1619-41b5-a683-12bbbdd13a7c.png)

**（1）Lambda表达式的结构**

Lambda表达式是java8中提供的一种新的特性，它支持[Java](http://lib.csdn.net/base/17)也能进行简单的“函数式编程”。 它是一个匿名函数，Lambda表达式基于数学中的λ演算得名，直接对应于其中的lambda抽象(lambda abstraction)，是一个匿名函数，即没有函数名的函数。java8中lambda表达式有三部分组成：

* **第一部分为一个括号内用逗号分隔的形式参数，参数是函数式接口里面方法的参数**；
* **第二部分为一个箭头符号：->**；
* **第三部分为方法体，可以是表达式和代码块**。

语法如下：

```java
()->{expression}
//或
(param)->{expression}
```

以下是一些lambda表达式的例子：

```java
(int a, int b) -> {  return a + b; }

() -> System.out.println("Hello World");

(String s) -> { System.out.println(s); }

() -> 42

() -> { return 3.1415 };
```

关于lambda表达式的语法需要特别注意以下特性：

* 一个 Lambda 表达式可以有零个或多个参数
* 参数的类型既可以明确声明，也可以根据上下文来推断。例如：`(int a)`与`(a)`效果相同
* 所有参数需包含在圆括号内，参数之间用逗号相隔。例如：`(a, b)` 或 `(int a, int b)` 或 `(String a, int b, float c)`
* 空圆括号代表参数集为空。例如：`() -> 42`
* 当只有一个参数，且其类型可推导时，圆括号（）可省略。例如：`a -> return a*a`
* Lambda 表达式的主体可包含零条或多条语句
* 如果 Lambda 表达式的主体只有一条语句，花括号{}可省略。匿名函数的返回类型与该主体表达式一致
* 如果 Lambda 表达式的主体包含一条以上语句，则表达式必须包含在花括号{}中（形成代码块）。匿名函数的返回类型与代码块的返回类型一致，若没有返回则为空


#### 2、Lambda表达式的函数式接口

java8的**lambda表达式实质是是以匿名内部类的形式的实现的**。看下面代码。代码中我们定义了一个叫opt的Lambda表达式，看返回值它是一个IntBinaryOperator实例。 

```java
public static void main(String[] args) {
    //本质就是实现了IntBinaryOperator这个接口的唯一的一个方法applyAsInt，然后返回了一个对象实例
    IntBinaryOperator opt = (a, b) -> a * b;
    //调用他的方法applyAsInt计算结果
    int ans = opt.applyAsInt(5, 3);         
    System.out.println(ans);     //15       
}                                           
```

那么你有想过为什么匿名内部类可以这个简写吗？我们来看看IntBinaryOperator的定义：

![](http://image.easyblog.top/1594444345288097d1c07-f4b7-44ef-948a-7e63b363ffc4.png)

从源码可以看到，IntBinaryOperator这个接口被一个叫**@FuncationalInterface**的注解修饰了，并且在这个解口中只有一个抽象方法，在Java8以后我们**把使用@FunctionalInterface标注了的并且只有一个抽象方法的接口称作这是一个函数式接口**。

同样地，**java.lang.Runnable**也是一个函数式接口，在 Runnable 接口中只声明了一个方法` void run()`，我们使用匿名内部类来实例化函数式接口的对象，有了 Lambda 表达式，这一方式可以得到简化。下图所示是Runnable接口的定义。

![](http://image.easyblog.top/159444465929747900067-b1ad-4af3-b15d-bfa4d5abc1ab.png)

了解到这里，我觉得我们应该对函数式接口有个更深层次的认识。

**函数式接口(Functional Interface)是JAVA 8对一类特殊类型的接口的称呼。这类接口只定义了唯一的抽象方法的接口（除了隐含的Object对象的公共方法，因此最开始也就做SAM类型的接口（Single Abstract Method）。定义函数式接口的原因是在Java Lambda的实现中,开发组不想再为Lambda表达式单独定义一种特殊的Structural函数类型,称之为箭头类型（arrow type,依然想采用Java既有的类型(class, interface, method等).原因是增加一个结构化的函数类型会增加函数类型的复杂性，破坏既有的Java类型，并对成千上万的Java类库造成严重的影响。权衡利弊,因此最终还是利用SAM 接口作为 Lambda表达式的目标类型.另外对于函数式接口来说@FunctionalInterface并不是必须的,只要接口中只定义了唯一的抽象方法的接口那它就是一个实质上的函数式接口,就可以用来实现Lambda表达式。**



**常用的函数式接口**

 在java 8中已经为我们定义了很多常用的函数式接口它们都放在**java.util.function包**下面,一般有以下常用的四大核心接口：

| 函数式接口                | 参数类型 | 返回类型    | 用途                                                        |
| ------------------------- | -------- | ----------- | ----------------------------------------------------------- |
| **Consumer(消费型接口)**  | **T**    | **void**    | **对类型为T的对象应用操作。void accept(T t)**               |
| **Supplier(供给型接口)**  | **无**   | **T**       | **返回类型为T的对象。 T get();**                            |
| **Function(函数型接口)**  | **T**    | **R**       | **对类型为T的对象应用操作并返回R类型的对象。R apply(T t);** |
| **Predicate(断言型接口)** | **T**    | **boolean** | **确定类型为T的对象是否满足约束。boolean test(T t);**       |

##### Consumer 消费型接口

**消费型接口就是有输入没有返回的接口，有进无出所以叫消费者Consumer**，如果要定义一个有参的无返回值的抽象方法的接口时，可以直接使用Consumer<T>。

![](http://image.easyblog.top/15944459632863cdae6a3-caee-4103-be12-1fa7d14d4ceb.png)

可以看到在Consumer接口中有两个方法，但是只有一个accept抽象方法并且使用@FunctionalInterface修饰了，因此他符合函数式接口的定义。其中accept方法用于指定一个消费者的消费行为的，需要传入一个参数，参数的类型由泛型决定，默认方法andThen作用是合并2个消费者生成一个新的消费者，先执行第一个消费者的accept方法，再执行第二个消费者的accept方法　
用法示例参考ConsumerTest


##### Supplier 生产者接口

**生产者接口无需传递参数但是有返回值，他返回一个泛型参数指定类型的对象实例，无进有出所以叫生产者或提供者Supplier**。如果要定义一个无参的有Object返回值的抽象方法的接口时，可以直接使用Supplier<T>,不用自己定义接口了。

![](http://image.easyblog.top/159444690531869e6f89d-d894-4953-830b-f869f6f5c820.png)

用法示例参考SupplierTest

##### Function 函数型接口

**Function 接口用来根据一个类型的数据得到另一个类型的数据，前者称为前置条件，后者称为后置条件。有进有出，所以称为“函数Function”。**

![](http://image.easyblog.top/1594447752551bb0a6b32-2b26-4dfe-8abf-b4caae5a1c4d.png)

**该接口可以理解成一个数据工厂，用来进行数据转换，将一种数据类型的数据转换成另一种数据.  泛型参数T：要被转换的数据类型（原料），泛型参数R：想要装换成的数据类型（产品）。**
用法示例参考FunctionTest


##### Predicate 断言接口

**Predicate接口主要是对某种类型的数据进行判断，返回一个boolean型结果。可以理解成用来对数据进行筛选。**

![](http://image.easyblog.top/15944479739507745479a-4eee-4ec7-980e-34d393ccd480.png)

当需要定义一个有参并且返回值是boolean型的方法时，可以直接使用Predicate接口中的抽象方法
用法示例参考PredicateTest

