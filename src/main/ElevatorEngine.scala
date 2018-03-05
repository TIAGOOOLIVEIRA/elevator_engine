package com.engines.elevators

import com.sun.xml.internal.ws.handler.HandlerProcessor.Direction

import scala.collection.mutable.ArrayBuffer

object Direction extends Enumeration{
  val Up = Value("Up"),
  val Down = Value("Down")
}

abstract class FloorQueue{
  def get(): Int
  def put(x: Int)
}

class BasicFloorQueue extends FloorQueue{
  var direction: Direction = Direction.Up

  protected var currentFloor: Int = 0

  protected var buf = new ArrayBuffer[Int]

  override def get(): Int = buf.remove(0)

  override def put(x: Int): Unit = {buf += x}
}

trait FilteringElevator extends FloorQueue {
  abstract override def put(x: Int): Unit = {
    if (!buf.exists(_ == x)){
      super.put(x)
    }
  }
}

trait OrderElevator extends FloorQueue{
  abstract override def put(x: Int): Unit = {
    super.put(x)
    buf = buf.sorted(if (direction.Up) Ordering.Int else Ordering.Int.reverse)
  }
}

trait ElevatorControl {
  def status(): Seq[(Int, Int, Int)]
  def update(x:Int, y:Int, z:Int)
  def pickup(floor: Int, direction: Direction)
  def step()
}

class ElevatorEngine extends ElevatorControl{

  val elequeue = (new BasicFloorQueue with FilteringElevator with OrderElevator)

  def pickup(floor: Int, direction: Direction): Unit ={

    elequeue.direction = direction
    elequeue.put(floor)
  }

}

object ElevatorEngineApp extends App {

  def ElevatorEngineApp(): Unit ={

  }
}