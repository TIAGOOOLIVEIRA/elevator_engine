import scala.collection.mutable.ArrayBuffer

object ScalaTest{
  def main(args: Array[String]){
    var i = 0
    for(i <- 1 to 10)
      println(i)


    val s = scala.collection.SortedSet(10, 4, 8, 2)

    val positions = List(300, 20, 10, 289, 30)

    val result2 = positions.sorted(Ordering.Int.reverse)


    val sllid1 = result2 sliding(1)

    result2.map(a=> println(a))

    println("-------")

    println(sllid1.next())
    println(sllid1.next())
    println(sllid1.next())
    println((result2 slice(1,result2.length)).length)

  }

}
