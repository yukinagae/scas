#!/usr/bin/env scala
import scala.io.Source

// config map
val ms = Map("M01"->2, "M02"->5)

// read file
val s = Source.fromFile("a.csv")
val lines2 = s.getLines.toList
val lines = lines2.tail

// append values
for(line <- lines) {
  val l = line.split(",", 6)
  val m = l(1)
  val step = ms.getOrElse(m, 1)
  val r = calc(step, l.toList.drop(2))
  println(r)
}

def may(step: Int, pair: (String, String)): String = {
  pair match {
    case ("", _) => ""
    case (_, x) if x != "" => pair._2
    case _ => (pair._1.toInt + step).toString
  }
}

def calc(step: Int, lis: List[String]): List[String] = {
  lis(0) +: (lis.init zip lis.tail).map(x => may(step, x))
}
