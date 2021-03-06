package pfds.queue

import org.scalatest.FunSuite

trait QueueSuite extends FunSuite {

  val Queue: {
    def apply[T](xs: T*): Queue[T]
    def empty: Queue[Nothing]
  }

  test("Can't get head of a empty Queue") {
    assertThrows[RuntimeException](Queue.empty.head)
  }

  test("Can't get tail of a empty Queue") {
    assertThrows[RuntimeException](Queue.empty.tail)
  }

  test("Empty queue should be empty") {
    assert(Queue.empty.isEmpty)
    assert(Queue().isEmpty)
    assert(Queue.empty.snoc(1).tail.isEmpty)
  }

  test("Queue with a item should not be empty") {
    assert(!Queue.empty.snoc(1).isEmpty)
    assert(!Queue(1).isEmpty)
  }

  test("Queue should preserve the order") {
    val q = Queue(1, 2)
    assert(q.head == 1)
    assert(q.tail.head == 2)
  }

  test("snoc method should append a item at the end of a queue") {
    val q = Queue(1, 2).snoc(3)
    assert(q.head == 1)
    assert(q.tail.head == 2)
    assert(q.tail.tail.head == 3)
  }
}

class NormalQueueSuite extends QueueSuite {
  val Queue = pfds.queue.NormalQueue
}

class BankerQueueSuite extends QueueSuite {
  val Queue = pfds.queue.BankerQueue
}

class PhysicistQueueSuite extends QueueSuite {
  val Queue = pfds.queue.PhysicistQueue
}

class BootstrappedQueueSuite extends QueueSuite {
  val Queue = pfds.queue.BootstrappedQueue
}
