package com.scala.basicsyx

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
  var listAny:List[Any] = List("","","","")                                                 //携变
  var listString:List[_ >: String] = List()                        //逆变

  class GeneTypeP[+T](val head : T ,val tail : GeneTypeP[T]){                             //携变
    printf("携变 %n")
    def prepend[U >: T](newHead : U):GeneTypeP[U] = new GeneTypeP(newHead,this)
  }
  var gt : GeneTypeP[Any] = new GeneTypeP[String]("FOO",null)
  gt=gt.prepend("sss")
  printf("泛型协变结果 head:%s tail:%s%n",gt.head,gt.tail)

  class GeneTypeL[-T]{                                                                      //携变
    printf("逆变 %n")
  }
  var lt:GeneTypeL[String] = new GeneTypeL[Any]()

  var jListP : java.util.List[_ <: Any] = new java.util.ArrayList[String]()                 //携变
  var jListL : java.util.List[_ >: String] =new util.ArrayList[Any]()                       //逆变


  /**
    *  Scala 隐式转换
    *
    *     在Scala中解决第三方函数库扩展问题时可以使用，Scala 的 implicit 关键词，
    *  该关键词可以修饰类、方法和参数，主要有两个作用：
    *     1.自动进行某些数据类型的隐式转换
    *     2.定义隐式参数定义默认常量
    *
    *     Scala 的 implicit 的使用规则：
    *     1）标记规则
    *     只有那些使用implicit关键字的定义才是可以使用的隐式定义。关键字implicit用
    *  来标记一个隐式定义，编译器可以选择它作为隐式变化的候选项。你可以使用implicit
    *  来标记任何变量、函数或者对象
    *     2）范围规则
    *     编译器在选择备选implicit定义时，只会选取当前作用域中的定义，比如，编译器
    *  不会去调用someVariable.convert。如果你需要使用someVariable.convert，则必须把
    *  someVariable引入到当前作用域。也就是说，编译器在选择备选implicit时，只有当
    *  convert是当前作用域中的单个标识符时才会作为备选implicit
    *     3）一次规则
    *     编译器在需要使用implicit定义时，只会尝试转换一次，依旧是编译器永远不会把x+y
    *  改写为convert1(convert2(x))+y
    *     4）优先规则
    *     编译器不会在x+y已经是合法的情况下去调用implicit规则
    *     5）命名规则
    *     你可以为implicit定义任意的名称。在通常情况下你可以任意命名，implicit的名称
    *  只有两种情况下有用：一是想在一个方法中明确指明；二是想把哪一个引人到当前作用域。
    *
    *
    */
  //隐式转换
  implicit def strToInt(str:String) = str.toInt
  implicit def doubleToInt(x:Double) =x.toInt

  var num:Int = "100"+100
  var num2:Int = 3.5
  printf("num: %s%n",num+100)
  printf("num2: %s%n",num2)

  //隐式对象转换(分数四则运算)  自定义运算符  转换被方法调用的对象
  class Rational (n:Int,d:Int){
    require(d!=0)
    private  val g = gcd(n.abs,d.abs)
    val numer = n/g
    val denom = d/g

    private def gcd(a: Int, b: Int):Int ={
      if(b==0) a else  gcd(b,a%b)
    }

    def + (that:Rational):Rational=
      new Rational(
        numer*that.denom + that.numer *denom,
        denom*that.denom
      )

    def + (i: Int):Rational=
      new Rational(numer + 1*denom ,denom)

    def * (that:Rational):Rational=
      new Rational(numer*that.numer,denom*that.denom)


    override def toString: String = numer.toString + "/" + denom
  }

  implicit def int2Rational(x:Int):Rational=new Rational(x,1)

  var oneHalf = new Rational(1,2)

  printf("二分之一： %s%n",oneHalf)
  printf("二分之一加一： %s%n",oneHalf+1)
  printf("二分之一加二分之一： %s%n",oneHalf+oneHalf)

  //隐式参数
  //(1)例子1
  implicit val name = "哈哈哈"
  def implicitParam(implicit name:String): Unit ={
    printf("implicitParam: %s%n",name)
  }
  implicitParam

  //（2）例子2
  class PreferredPrompt(val preference:String)
  class PreferredDrink (val preference:String)

  object JamesPrefs{
    implicit val prompt = new PreferredPrompt("Yes , master>")
    implicit val drink = new PreferredDrink("coffee")
  }

  object Greeter{
    def greet(name:String)(implicit prompt: PreferredPrompt,drink: PreferredDrink): Unit ={
      printf("Welcome , %s . The System is ready %n",name)
      printf("But while you work, why not enjoy a cup of %s ? %n",drink.preference)
      printf("%s%n",prompt.preference)
    }
  }

  import JamesPrefs._

  Greeter.greet("James")

  /**
    *  Scala 隐式转换的view限定
    */
  //（3）例子3
  /*
        这个函数是求取一个顺序列表的最大值。但这个函数有个限制，它要求类型T是Ordered[T]的
    一个子类，因此这个函数无法求一个整数列表的最大值
   */
  def maxListUpBound[T<:Ordered[T]](element:List[T]):T =
    element match {
      case List() => throw new IllegalArgumentException("Empty List !")
      case List(x) => x
      case x::rest =>{
        val maxRest = maxListImpParam(rest)
        if (x > maxRest) x else maxRest
      }
    }
  /*
        我们可以使用隐式参数来解决这个问题。我们可以再定义一个隐式参数，类型为一个函数类型，
     可以把一个类型T转换成为Ordered[T]
        编译器看到 x > maxRest 发现类型不匹配，它不会马上停止编译；相反，它会检查是否有合适
     的隐式转换来修补代码。
   */
  def maxListImpParam[T] (element:List[T])(implicit orderer:T => Ordered[T]):T =
    element match {
      case List() => throw new IllegalArgumentException("Empty List !")
      case List(x) => x
      case x::rest =>{
        val maxRest = maxListImpParam(rest)
        if (x > maxRest) x else maxRest
      }
    }
  /*
         由于在Scala中这种用法非常普遍，所有Scala专门定义了一种简化的写法--View限定
   */
  def maxList[T <% Ordered[T]](element:List[T]):T =
    element match {
      case List() => throw new IllegalArgumentException("Empty List !")
      case List(x) => x
      case x::rest =>{
        val maxRest = maxListImpParam(rest)
        if (x > maxRest) x else maxRest
      }
    }

  printf("maxListImpParam(List(1,2,3,4,5)) : %s%n",maxListImpParam(List(1,2,3,4,5)))
  printf("maxList(List(1,2,3,4,5)) : %s%n",maxList(List(1,2,3,4,5)))
  printf("List(1,2,3,4,5).max : %s%n",List(1,2,3,4,5).max)
  printf("List(1,2,3,4,5)::List(6,7,8,9,10) : %s%n",1::List(6,7,8,9,10))


  /**
    *     有时在当前作用域中可能存在多个符合条件的隐式转换，在大多数情况下，Scala编译
    * 器会拒绝自动插入转换代码。隐式转换只在转换非常明显的情况下工作，编译器只需要例行
    * 公事地插入所需要的转换代码及可。如果在当前作用域中存在多个选项，那么编译器不知道
    * 优先选择哪一个使用，运行报错 error: type mismatch 。此时应该明确指明使用哪个转换
    * 器
    */
  implicit def intToDigist(i:Int):List[Int]= i.toString.toList.map(_.toInt)
  implicit def intToRange(i:Int):Range=1 to i

  def printLength(seq:Seq[Int]) = println(seq.length)

  printLength(intToDigist(12))
  printLength(intToRange(12))
}
