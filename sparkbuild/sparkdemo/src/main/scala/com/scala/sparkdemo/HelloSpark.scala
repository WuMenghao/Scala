package com.scala.sparkdemo

import org.apache.spark.{SparkConf, SparkContext}

object HelloSpark {

  def main(args: Array[String]): Unit = {
    if (args.length !=2){
      System.err.println("Usage: HelloSpark <Input> <Output>")
      System.exit(1)
    }

    val conf = new SparkConf()
      .setAppName("HelloSpark")

    val sc = new SparkContext(conf)

    sc.textFile(args(0))
      .map(_.split("\t"))
      .map(x => (x(2).replace("[","").replace("]",""),1))
      .reduceByKey(_+_)
      .map(x => (x._2,x._1))
      .sortByKey(false)
      .map(x => (x._2,x._1))
      .saveAsTextFile(args(1))
  }

}