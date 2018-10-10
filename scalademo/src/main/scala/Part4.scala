package main.scala

import java.awt.event.{ActionEvent, ActionListener}

import javax.swing.JButton

import scala.collection.GenSeq

/**
  * 手动实战 Scala 中函数的定义
  * 手动实战 Scala 中值函数和匿名函数
  * 手动实战 Scala 中的闭包
  * 手动实战 Scala 中的 SAM 和 Curry（柯里化）
  */
object Part4 extends App {

  /**
    *     函数式编程是一种编程范式(programming paradigm),它属于结构化编程
    * 的一种，主要思想是把运算过程尽量写成一系列嵌套的函数。
    *
    *     函数式编程具有5个鲜明的特点：
    *
    *     1）函数是“第一等公民”： 指的是函数与其他数据类型一样，处于平等地位，
    * 可以赋值给其他变量，也可以作为参数传入另一个函数，或者作为别的函数的返回
    * 值；
    *
    *     2）只用表达式，不用语句： 表达式(expression)是一个单纯的运算过程，
    * 总是有返回值；语句(statement)是执行某种操作，没有返回值。函数式编程要求
    * 只使用表达式，不使用语句。也就是说，每一步都是单纯的运算，而且都有返回值。
    *     原因是函数式子编程的开发动机，一开始就是为了处理运算(computation)，
    * 不考虑系统读写(I/O)。语句属于对系统的读写操作，所以被排斥在外。
    *     当然，在实际应用中，不做I/O三不可能的。因此，在编程过程中，函数式编程
    * 只要求把I/O限制到最小，不要有不必要的读写行为，保持计算过错的单纯性。
    *
    *     3）没有“副作用”： “副作用”(side effect)，指的函数内部与外部互动(
    * 最典型的情况就算修改全局变量的值)，产生运算以外的其他结果。函数式编程强调
    * 没有“副作用”，意味着函数要保持独立，所有功能就算返回一个新的值，没有其他行
    * 为，尤其是不得修改外部变量的值
    *
    *     4）不修改状态： 函数式编程只是返回新的值，不修改系统变量。因此，不修改
    * 系统变量，无法使用变量保存状体，只能使用递归使用参数保存状态。由于使用了递归
    * ，函数式语言的运行速度比较慢，这是它长期不能在业界推广的主要原因。
    *
    *     5）引用透明： 指的是函数允许的状态不依赖于外部变量或状态，只依赖于输入
    * 的参数，任何时候只要参数相同，引用函数所得到的返回值就总是相同的。
    */


  /**
    * 使用递归进行字符串倒转
    * @param str
    */
  def reverse(str:String): String ={
    if (str.length() == 0)  str else reverse(str.substring(1,str.length()))+str.substring(0,1)
  }

  printf("abcdefghizk reverse is  %s%n",  reverse("abcdefghizk"))


  /**
    * 返回两数中更小的数值
    * @param num1
    * @param num2
    * @return
    */
  def min(num1:Int,num2:Int):Int ={
    if (num1>=num2) num1 else num2
  }

  printf("11 与 25 中更小的是 ：  %s%n",min(11,25))

  /**
    * 匿名函数
    */
  var rs = (x : Int) => x * 3

  printf("(x : Int) => x * 3 的 结果是：%s%n",rs(3))


  /**
    * 闭包  实质就是代码与用到的非局部变量的混合
    */
  var y = (x : Int) => x * 3
  var sum = (x:Int ,y:Int) => x + y

  printf("闭包计算结果: %s%n",sum(1,y(2)))


  /**
    * SAM(Single Abstract Method)
    * 只有单个抽象方法的接口 如: ActionListener
    */

  var counter = 0
  val button = new JButton("Increment")
  button.addActionListener((event : ActionEvent) => counter += 1)

  implicit def makeAction(actrion : (ActionEvent) => Unit)={
    new ActionListener {
      override def actionPerformed(event: ActionEvent): Unit = { actrion(event)}
    }
  }


  /**
    * Curry 柯里化方法 corresponds
    */
  val a = Array("Hello","world")
  val b = Array("Hello","world")
  printf(
    "a 与 b 数组中的字符串忽略大小写是否一致 %s%n",
    a.corresponds(b)(_.equalsIgnoreCase(_))
  )

  /**
    * 控制抽象
    * 在Scala中，可以将一系列语句归组成不带参数也没有返回值的函数
    *
    * @param block
    */
  def runInThread(block: => Unit): Unit ={
    new Thread{
      override def run(): Unit = block
    }.start()
  }
  runInThread{
    println("Hi");Thread.sleep(1000);println("Bye")
  }


  /**
    * 使用控制抽象定义的函数使用起来类似于关键字
    *
    * @param condition
    * @param block
    */
  def until(condition: => Boolean)(block: => Unit): Unit ={
    if (!condition){
      block
      until(condition)(block)
    }
  }

  var x=10
  until(x == 0){
    x -= 1
    printf("  %s",x)
  }

}