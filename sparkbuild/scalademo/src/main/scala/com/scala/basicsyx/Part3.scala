package com.scala.basicsyx

/**
  *
  * 动手实战 Scala 中的 trait
  * 动手实战 Scala 中的 apply 方法和单例对象
  *
  * Scala trait 类似于Java8中可带 defualt method 的接口
  *
  * Scala object 中定义的方法都是静态方法
  */
object Part3 extends App {

  /**
    * trait定义
    */
  trait Logger{
    def log(msg:String): Unit ={
      printf("log: %s%n",msg)
    }
  }

  /**
    * trait继承
    */
  trait MessageLogger extends Logger{
    override def log(msg: String): Unit = {
      printf("Log message is : %s%n",msg)
    }
  }

  /**
    * class继承trait
    */
  class ConcreteLogger extends Logger{
    def concreteLog: Unit ={
      log("It's me!")
    }
  }

  /**
    * 抽象类
    */
  abstract class Account{
    def save
  }

  /**
    * 混入trait
    */
  class MyAccount extends Account with Logger{
    override def save: Unit = {
      log("100000")
    }
  }

  object MyApply{

    def apply(): MyApply = new MyApply()

    def method: Unit = println("This is a static method !")

  }

  class MyApply{
    def test: Unit = println("test")
  }

  //使用trait
  new ConcreteLogger().concreteLog

  //对象中混入trait
  var acc = new MyAccount with MessageLogger
  acc.save

  //使用object方法可以"."调用
  MyApply.method

  //使用apply
  var a = MyApply()
  a.test
}
