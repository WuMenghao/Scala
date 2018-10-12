package com.scala.basicsyx

/**
  * Scala 函数特性编程
  * Scala 中的表达式实战
  */
object Part1 {

  /**
    * 主函数
    * @param args
    */
  def main(args: Array[String]): Unit= {
    println("Hello scala!")
    printf("%s%n",hello("Wu Menghao"))
    printf("1 + 2 =  %s%n",add(1,2))
    printf("1 + 2 =  %s%n",add2(1)(2))
    printf("1 + 2 =  %s%n",sum(1,2))
    variableParameter("I","Love","Spark")
    printf("gtThanZero : %s%n",gtThanZero(10))
    printf("2 ^ 8 = %s%n",power(2)(8))
    printf("2 ^ 8 = %s%n",power(2,8))
  }

  /**
    *  hello 函数
    * @param name
    * @return
    */
  def hello(name : String): String ={
    "Hello" + name
  }

  /**
    * add 函数
    * @return
    */
  def add = (x : Int ,y : Int) => x + y

  /**
    * add 柯里化函数 （特性）
    * 柯里化（Currying）是把接受多个参数的函数变换成接受一个单一参数(最初函数的第一个参数)的函数
    * @param x
    * @param y
    * @return
    */
  def add2(x:Int)(y:Int):Int = x + y

  /**
    * sum 函数变量
    */
  var sum = (x:Int,y:Int) => x + y

  /**
    * variableParameter 可变参数
    * @param s
    */
  def variableParameter(s:String*):Unit={
    s.foreach(x => print(x))
  }

  /**
    * if else语句
    * @param num
    * @return
    */
  def gtThanZero(num:Int):Int= if(num > 1) 1 else  0

  /**
    * while 语句 幂计算
    * @param base
    * @param exponential
    * @return
    */
  def power(base:Int)(exponential:Int):Int = {
    var (n,r) = (base,exponential)
    while (r>0){
      n = n * base
      r = r - 1
    }
    n
  }

  /**
    * for 语句 幂计算
    */
  var power = (base:Int ,exponential:Int) =>{
    var n = base
    for (i <- 1 to exponential){
      n = n * base
    }
    n
  }
}
