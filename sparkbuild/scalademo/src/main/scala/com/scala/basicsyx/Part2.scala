package com.scala.basicsyx

/**
  * 动手实战 Scala 中类的使用
  * 动手实战 Scala 中的继承
  * 动手实战 Scala 中的抽象类
  *
  */
object Part2 {

  /**
    * 主函数
    * @param args
    */
  def main(args: Array[String]): Unit = {

    val p =new Person
    p.name = "Wu Menghao"
    printf("%s : %s%n",p.name,p.age)


    val p2 = new Person2("Wu Menghao",27,"男")
    printf("%s : %s : %s%n",p.name,p.age,p2.gender)
    println(p2)

    new Student().speak
  }

  /**
    * Person类 默认空参构造
    */
  class Person{

    println("This is the primary constructor!")

    //字段
    var name : String = _
    var age : Int = 27

    //有参构造
    def this(name : String,age : Int){
      this
      this.name=name
      this.age=age
    }

  }

  /**
    * Person2类 默认有参构造
    * @param name
    * @param age
    */
  class Person2(var name:String,var age:Int){

    println("This is the primary constructor!")

    //字段
    var gender:String = _

    //有参构造
    def this(name:String,age:Int,gender:String){
      this(name,age)
      this.gender=gender
    }

    //重写toString()函数
    override def toString = s"Person2($gender, $name, $age)"
  }

  /**
    * Person3抽象类
    */
  abstract class Person3{

    var name:String

    var age:Int

    def speak

  }

  /**
    * Student继承了Person3
    */
  class Student extends Person3{

    var name: String = "张三"
    var age: Int = 11

    override def speak: Unit = printf("老子叫 %s ,今年 %s 岁",name,age)
  }


}
