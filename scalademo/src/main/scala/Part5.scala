package main.scala

import java.util

/**
  * 手动实战 Scala 中的函数
  * 手动实战 Scala 中的集和
  * 手动实战 Scala 中的泛型
  * 手动实战 Scala 中的隐式转换
*/
object Part5 extends App {

  /**
    * Scala 的集和主要有 List Set Tuple Map
    */

  var list = List(1,2,3,4,5,6)
  var set = Set(1,2,3,4,5,6,1)
  var tuple = ("哈哈哈",234,list)
  var map = (1 -> "哈哈哈",2 -> "啦啦啦")

  printf("List : %s%n",list)
  printf("Set : %s%n",set)
  printf("Tuple : %s%n",tuple)
  printf("Map : %s%n",map)


  /**
    * Scala 高阶函数
    */
  val newList1 = list.map((x:Int) => 2*x)  //map函数：可以对集和或数组中每一个元素进行制定操作
  val newList2 = list.filter(_ > 3)        //filter函数：可以过滤集和或数组中符合条件的元素
  var rs = list.reduce(_*_)       //reduce函数：可以对集合中的元素第归的进行某项操作返回计算结果

  printf("map()函数的结果 : %s%n",newList1)
  printf("filter()函数的结果 : %s%n",newList2)
  printf("reduce()函数的结果 : %s%n",rs)


  /**
    * Scala 范型具有 携变 与 逆变 特性
    */
  class GeneType[+T](val head : T ,val tail : GeneType[T]){
    def prepend[U >: T](newHead : U):GeneType[U] = new GeneType(newHead,this)
  }

  var gt : GeneType[Any] = new GeneType[String]("FOO",null)

  printf("泛型协变结果 %s%n",gt.head)


}
