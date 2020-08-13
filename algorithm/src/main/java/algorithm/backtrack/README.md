回溯算法
====

#### 1、回溯算法模板
任何算法的核⼼都是穷举，回溯算法就是⼀个暴⼒穷举算法，回溯算法框架：
```java
List result=new ArrayList<>();
int backtrack(选择列表，路径){
   
    if(满足条件){
        reslut.add(结果)
    }
  
    for(int i=0;i<选择列表.length;i++){
          做选择
          backtrack(选择列表，路径) 
          撤销选择
    } 
}
```