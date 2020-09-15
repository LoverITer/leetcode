### IntStream详解



IntStream是特殊的Stream，但有一些操作符是IntStream独有的；话不多说，开始玩转IntStream吧。

### 理论讲解

#### 构造IntStream

IntStream这个接口里提供了如下方法用于构造一个流：

```java
  IntStream.generate() //产生一个无限流，这里需要传入一个IntSupplier函数式接口实例 。
  ```

```java
  IntStream.range() //产生指定区间的有序IntStream，这里需要传入一个区间（左闭右开），产生的元素不包含最后一个。
  ```

```java
  IntStream.rangeClosed() //产生指定区间的有序IntStream，与IntStream.range()不同的是，产生的元素包含最后一个，即左闭右闭。
  ```

```java
  IntStream.of()//填充一个或多个int元素构造流。
  ```

```java
  IntStream.empty()  //产生一个空元素的流。
  ```

```java
  IntStream.builder()  //会产生一个builder用于构建stream，通过builder的add方法添加元素，build方法构造流。
  ```

```java
  IntStream.iterate()//产生一个有序的无限流，需要传入初始值，对元素操作的函数。
  ```

```java
  IntStream.concat() //将两个流合并成一个流。
  ```

#### 操作IntStream

##### 过滤操作

```
  filter() //根据条件过滤元素
  ```

##### 转换操作

```java
  map() //产生的仍然是IntStream，可以对元素进行数学上的操作，加减乘除等等。
  ```

```java
  mapToObj()//转成对象流，例如String等。
  ```

```java
  mapToLong()//转成long类型流。
  ```

```java
  mapToDouble()  //转成double类型流。
  ```

```java
  asLongStream()//快速转成Long类型的Stream。
  ```

```java
  asDoubleStream()  //快速转成Double类型的Stream。
  ```

##### 拍扁操作

```java
  flatMap() //拍平元素，产生更多的元素。
  ```

##### 去重操作

```java
  distinct()//元素去重，底层用的还是equals。
  ```

##### 排序操作

```java
  sorted() //元素排序，自然顺序排序。
  ```

##### 查看元素

```java
  peek() //需传入一个IntConsumer 实例。
  ```

##### 限流操作

```java
  limit() //截取前面多少个元素。
  ```

##### 跳过操作

```java
  skip() //跳过元素。
  ```

##### 遍历操作

```java
  forEach() //传入IntConsumer实例。
  ```

```java
  forEachOrdered()  //与forEach相比，对元素进行有序遍历。
  ```

##### 数组操作

```java
  toArray()//转成int数组。
  ```

##### 规约操作

```java
  reduce() //将所有元素规约聚合成一个，需传入一个IntBinaryOperator实例，返回一个optionalInt，结果不一定有值。
  ```

```java
  reduce()  //重载reduce，需要传入初始值和IntBinaryOperator实例，最终的结果一定有值。
  ```

##### 收集操作

```
  collect()需要传入一个结果容器，元素累加器，组合器
  1
  ```

```
  collect()是重载方法，可以传入Collectors的实例。
  1
  ```

##### 数学操作

```java
  sum()  //求和操作，底层用reduce实现。
  ```

```java
  max()  //求最大值，底层用reduce实现。
  ```

```java
  min()  //求最小值，底层用reduce实现。
  ```

```java
  count()  //统计元素个数。
  ```

```java
  average()  //求平均值。
  ```

```java
  summaryStatistics()  //汇总统计，计算sum、max、min、count、average。
  ```

##### 匹配操作

```java
  anyMatch() //任何一个元素符合条件，传入一个IntPredicate实例。
  ```

```java
  allMatch() //所有元素都符合条件。
  ```

```java
  noneMatch()  //所有元素都不符合条件。
  ```

##### 查询操作

```java
  findFirst()//获取流中的第一个元素，可能没有。
  ```

```java
  findAny()//随机获取流中的任意一个元素，可能没有。
  ```

##### 装箱操作

```java
  boxed() //将元素装箱。
  ```

### 实践出真知

#### 构造IntStream

1. generate()会产生一个无限的流，这里需要使用limit限制。

```java
@Test
 public void testGenerate() {
  // 传入IntSupplier ，这里永远返回1
  int sum = IntStream.generate(() -> 1).limit(1).sum();
  Assert.assertEquals(1, sum);
  // 当然还可以这样
  IntSupplier intSupplier = () -> 1;
  sum = IntStream.generate(intSupplier).limit(1).sum();
  Assert.assertEquals(1, sum);
 }
```

2. range()产生一个区间内的有序流。

```java
 @Test
 public void testRange() {
  int sum = IntStream.range(0, 10).sum();//注意了，不包含最后一个元素
  Assert.assertEquals(45, sum);
 }
```

3. rangeClosed()产生一个区间内的有序流，包含区间最后一个元素。

```java
@Test
 public void testRangeClosed() {
  int sum = IntStream.rangeClosed(0, 10).sum();
  Assert.assertEquals(55, sum);
 }
```

4. of()快速使用值创建流。

```java
 @Test
 public void testOf() {
  int sum = IntStream.of(1).sum();
  Assert.assertEquals(1,sum);
  // of的重载方法，传入一个不定参数
  sum = IntStream.of(1,2,3,4).sum();
  Assert.assertEquals(10,sum);
 }
```

5. empty()创建一个空流。

```java
 @Test
 public void testEmpty() {
  int sum = IntStream.empty().sum();
  Assert.assertEquals(0, sum);
 }
```

6. builder()构造流。

```java
 @Test
 public void testBuilder() {
  int sum = IntStream.builder().add(1).add(2).add(3).build().sum();
  Assert.assertEquals(6, sum);
 }
```

7. iterate()创建一个无限流。

```java
@Test
 public void testIterate() {
  //这里会一直乘以2，输出10个元素：1、2、4、8、16、32、64、128、256、512
  IntStream.iterate(1, (e) -> e * 2).limit(10).forEach(System.out::println);
 }
```

8. concat()合并两个流。

```java
 @Test
 public void testConcat() {
  IntStream a = IntStream.range(10, 20);
  IntStream b = IntStream.range(40, 50);
  long count = IntStream.concat(a, b).count();
  // 两个流合并后总元素为20
  Assert.assertEquals(20, count);
 }
```

#### 操作IntStream

##### 过滤操作

1. filter()过滤不满足条件的元素。

```java
@Test
 public void testFilter() {
  //这里只输出5以上的元素
  IntStream.of(1, 5, 3, 7, 8, 3, 5, 6).filter(e -> e >= 5).forEach(System.out::println);
 }
```

##### 转换操作

1. map()

```java
 @Test
 public void testMap() {
//这里我将每个元素都变成之前的2倍
  IntStream.of(1, 2, 3).map(e -> e * 2).forEach(System.out::println);
 }

```

2. mapToObj()

```java
@Test
 public void testMapObject() {
// 这里转成string对象
  IntStream.of(1, 2, 3).mapToObj(String::valueOf).map(Object::getClass).forEach(System.out::println);
 }
```

3. mapToLong()

```java
@Test
 public void testMapToLong() {
// 这里其实还可以进行数学上的一些操作，例如:e -> e*2
  IntStream.of(1, 2, 3).mapToLong(e -> e).forEach(System.out::println);
 }
```

4. mapToDouble()

```java
 @Test
 public void testMapToDouble() {
  // 和mapToLong类似
  IntStream.of(1, 2, 3).mapToDouble(e -> e).forEach(System.out::println);
 }
```

5. asLongStream()

```java
@Test
 public void testAsLongStream() {
// 如果转换过程不需要其他操作，可以直接用这个，更方便。
  long[] array = IntStream.range(10, 20).asLongStream().toArray();
  Assert.assertEquals(10, array.length);
 }
```

6. asDoubleStream()快速转成Double类型的Stream。

```java
 @Test
 public void testAsDoubleStream() {
// 和asLongStream类似
  double[] array = IntStream.range(10, 20).asDoubleStream().toArray();
  Assert.assertEquals(10, array.length);
 }
```

##### 拍扁操作

1. flatMap()

```java
@Test
 public void testFlatMap() {
// 这里根据上游的元素扩展出了更多的元素
  IntStream.of(1, 2, 3).flatMap(e -> IntStream.rangeClosed(0, e)).forEach(System.out::println);
 }
```

##### 去重操作

1. distinct()

```java
 @Test
 public void testDistinct() {
  long count = IntStream.of(1, 2, 2, 3).distinct().count();
  Assert.assertEquals(3,count);
 }
```

##### 排序操作

1. sorted()

```java
@Test
 public void testSorted() {
// 输出结果：-6、-1、0、2、3、5、6、7
  IntStream.of(5, 6, 3, 2, 7, -1, -6, 0).sorted().forEach(System.out::println);
 }
```

##### 查看元素

1. peek()

```java
 @Test
 public void testPeek() {
  IntStream.of(1, 2, 3, 4, 5)
 .filter(e -> e >= 3)
 .peek(value -> System.out.printf("filter element: %d\n", value))
 .mapToObj(String::valueOf)
 .forEach(System.out::println);
 }
```

##### 限流操作

1. limit()

```java
 @Test
 public void testLimit() {
// 这里截取前15个
  IntStream.range(0, 100000).limit(15).forEach(System.out::println);
 }
```

##### 跳过操作

1. skip()

```java
 @Test
 public void testSkip() {
//跳过前5个元素，输出结果为:5、6、7、8、9
  IntStream.range(0, 10).skip(5).forEach(System.out::println);
 }
```

##### 遍历操作

1. forEach()

```java
@Test
 public void testForEach() {
  IntStream.of(1,5,-9,0,-5,2,5,8).forEach(System.out::println);
 }
```

2. forEachOrdered()

```java
 @Test
 public void testForEachOrdered() {
  IntStream.of(1,5,-9,0,-5,2,5,8).parallel().forEach(System.out::println);
  System.out.println("===================================================");
// 在并行遍历时，forEachOrdered将顺序遍历元素
  IntStream.of(1,5,-9,0,-5,2,5,8).parallel().forEachOrdered(System.out::println);
 }
```

##### 数组操作

1. toArray()

```java
 @Test
 public void testToArray() {
  int[] array = IntStream.range(0, 100).toArray();
  Assert.assertEquals(100, array.length);
 }
```

##### 规约操作

1. reduce()

```java
@Test
 public void testReduce() {
// 规约操作一定有值
  int sum = IntStream.range(0, 1000).reduce(0, (v1, v2) -> v1 + v2);
  System.out.println(sum);
  // 规约操作返回 optionalInt，不一定有值
  IntStream.range(0, 1000).reduce((v1, v2) -> v1 + v2).ifPresent(System.out::println);
 }
```

##### 收集操作

1. collect()自定义逻辑。

```java
 @Test
 public void testCollect() {
// 需要提供容器工厂、元素收集器、容器组合器
  ArrayList<Integer> list = IntStream.range(0, 100).boxed().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
  Assert.assertEquals(100,list.size());
 }
```

2. collect()传入Collectors。

```java
@Test
public void testCollectWithCollectors() {
 // 使用Collectors的toList
 ArrayList<Integer> list = IntStream.range(0, 100).boxed().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
 list.forEach(System.out::println);
 IntStream.range(0, 100).boxed().collect(Collectors.toList());
}
```

##### 数学操作

1. sum()

```java
@Test
public void testSum() {
 int sum = IntStream.rangeClosed(0, 10).sum();
 Assert.assertEquals(55, sum);
}
```

2. max()

```java
 @Test
 public void testMax() {
  OptionalInt max = IntStream.of(0, -1, 2, -9, 10, 9).max();
  Assert.assertTrue(max.isPresent());
  Assert.assertEquals(10, max.getAsInt());
 }
```

3. min()

```java
@Test
 public void testMin() {
  OptionalInt min = IntStream.of(0, -1, 2, -9, 10, 9).min();
  Assert.assertTrue(min.isPresent());
  Assert.assertEquals(-9, min.getAsInt());
 }
```

4. count()

```java
@Test
 public void testCount() {
  long count = IntStream.of(0, -1, 2, -9, 10, 9).count();
  Assert.assertEquals(6, count);
 }
```

5. average()

```java
@Test
 public void testAverage() {
  OptionalDouble average = IntStream.of(-2, 2, -9, 10, 9).average();
  Assert.assertEquals(2.0, average.getAsDouble(), 0.0);
 }
```

6. summaryStatistics()

```java
@Test
 public void testSummaryStatistics() {
  IntSummaryStatistics summaryStatistics = IntStream.of(-2, 2, -9, 10, 9).summaryStatistics();
  Assert.assertEquals(10, summaryStatistics.getSum());
  Assert.assertEquals(10, summaryStatistics.getMax());
  Assert.assertEquals(-9, summaryStatistics.getMin());
  Assert.assertEquals(5, summaryStatistics.getCount());
  Assert.assertEquals(2.0, summaryStatistics.getAverage(), 0.0);
 }
```

##### 匹配操作

1. anyMatch()

```java
 @Test
 public void testAnyMatch() {
  boolean result = IntStream.of(-2, 2, -9, 10, 9).anyMatch(e -> e > 0);
  Assert.assertTrue(result);
 }
```

2. allMatch()

```java
@Test
 public void testAllMatch() {
  boolean result = IntStream.of(5, 5, 5, 5, 5).anyMatch(e -> e > 0);
  Assert.assertTrue(result);
 }
```

3. noneMatch()

```java
 @Test
 public void testNoneMath() {
  boolean result = IntStream.of(4, 5, 5, 5).noneMatch(e -> e == 4);
  Assert.assertFalse(result);
 }
```

##### 查询操作

1. findFirst()

```java
@Test
 public void testFindFirst() {
  int element = IntStream.of(4, 5, 5, 5).findFirst().getAsInt();
  Assert.assertEquals(4, element);
 }
```

2. findAny()

```java
@Test
 public void testFindAny() {
  IntStream.range(0, 18).findAny().ifPresent(System.out::println);
 }
```

##### 装箱操作

1. boxed() 将元素装箱。

```java
@Test
 public void testBoxed() {
// 将基本类型转成对象类型
  boolean result = IntStream.range(0, 10).boxed().allMatch((e -> e instanceof Integer));
  Assert.assertTrue(result);
 }
```