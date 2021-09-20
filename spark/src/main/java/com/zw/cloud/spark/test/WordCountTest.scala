package com.zw.cloud.spark.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCountTest {

  def main(args: Array[String]): Unit = {
    //建立和spark框架的连接
    val sparkConf = new SparkConf().setMaster("local").setAppName("wordCount")
    val sparkContext = new SparkContext(sparkConf)
    // 业务操作
    // 1 读取文件
    val lines: RDD[String] = sparkContext.textFile("datas")
    // 2 单词拆分
     val words: RDD[String] = lines.flatMap(_.split(" "))
    // 3 数据分组
    val wordGroup: RDD[(String, Iterable[String])] = words.groupBy(word=>word)
    // 4 风组数据转换统计
    val wordToCount = wordGroup.map{
      case (word,list) => {
        (word,list.size)
      }
    }
    // 5 转换结果打印
    val array:Array[(String,Int)] = wordToCount.collect()
    array.foreach(println)
    //关闭连接
    sparkContext.stop()
  }
}
