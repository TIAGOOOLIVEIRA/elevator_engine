package com.engines.elevators

import scala.collection.mutable.ArrayBuffer

abstract class FloorQueue{
  def get(): Int
  def put(x: Int)
}

trait BasicFloorQueue extends FloorQueue{
  //true -> up
  //false -> down
  protected var direction: Boolean = true

  protected var currentFloor: Int = 0

  protected var buf = new ArrayBuffer[Int]

  override def get(): Int = buf.remove(0)

  override def put(x: Int): Unit = {buf += x}
}

trait Filtering extends BasicFloorQueue {
  abstract override def put(x: Int): Unit = {
    if (!buf.exists(_ == x)){
      super.put(x)
    }
  }
}

trait OrderElevator extends BasicFloorQueue{
  abstract override def put(x: Int): Unit = {
    super.put(x)
    buf = buf.sorted(if (direction) Ordering.Int else Ordering.Int.reverse)
  }
}

trait ElevatorControl {
  def status(): Seq[(Int, Int, Int)]
  def update(x:Int, y:Int, z:Int)
  def pickup(floor: Int, direction: Boolean)
  def step()
}

class ElevatorEngine {

}
