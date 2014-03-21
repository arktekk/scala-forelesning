package forelesning

import org.scalatest.FunSuite
import org.scalatest.prop.Checkers
import org.scalacheck.Prop._

import scala.{List => SList}

class ListTest extends FunSuite with Checkers {

  def toLst[A](list: SList[A]) = list.foldRight(Nil: List[A])(Cons(_, _))

  test("dropWhile"){
    check{ (list:SList[Int], i:Int) =>
      toLst(list).dropWhile(_ < i) == toLst(list.dropWhile(_ < i))
    }
  }

  test("isEmpty") {
    check { list: SList[Int] =>
      toLst(list).isEmpty == list.isEmpty
    }
  }

  test("size") {
    check { list: SList[Int] =>
      toLst(list).size == list.size
    }
  }

  test("head") {
    intercept[NoSuchElementException]{
      Nil.head
    }

    check { list:SList[Int] =>
      !list.isEmpty ==> (toLst(list).head == list.head)
    }
  }

  test("tail"){
    intercept[NoSuchElementException]{
      Nil.tail
    }

    check{ list:SList[Int] =>
      !list.isEmpty ==> (toLst(list).tail == toLst(list.tail))
    }
  }

  test("isDefinedAt"){
    check{ (i:Int, list:SList[Int]) =>
      toLst(list).isDefinedAt(i) == list.isDefinedAt(i)
    }
  }

  test("apply"){
    check{ (i:Int, list:SList[Int]) =>
      list.isDefinedAt(i) ==> (list(i) == toLst(list)(i))
    }

    check{ (i:Int, list:SList[Int]) =>
      !list.isDefinedAt(i) ==> (try{
        toLst(list)(i)
        false
      } catch {
        case _:NoSuchElementException => true
      })
    }

  }

  test("map"){
    def f(i:Int) = i * 2

    check{ list:SList[Int] =>
      toLst(list).map(f) == toLst(list.map(f))
    }
  }

  test("append"){
    check{ (listA:SList[Int], listB:SList[Int]) =>
      toLst(listA).append(toLst(listB)) == toLst(listA ++ listB)
    }
  }


  test("flatMap"){
    check{ list:SList[Int] =>
      val lstResult = toLst(list).flatMap{i => Cons(i, Cons(i, Cons(i, Nil)))}
      val listResult = list.flatMap{ i => SList(i, i, i) }
      lstResult == toLst(listResult)
    }
  }

  test("filter"){
    def f(i:Int) = i % 2 == 0

    check{ list:SList[Int] =>
      toLst(list).filter(f) == toLst(list.filter(f))
    }
  }

  test("reverse"){
    check{ list:SList[Int] =>
      toLst(list).reverse == toLst(list.reverse)
    }
  }

  test("foldLeft"){
    check{ list:SList[Int] =>
      toLst(list).foldLeft(SList[Int]()){(a, b) => b :: a } == list.reverse
    }
  }

  test("foldRight"){
    check{ list:SList[Int] =>
      toLst(list).foldRight(SList[Int]()){_ :: _} == list
    }
  }

  test("sum"){
    check{ list:SList[Int] =>
      List.sum(toLst(list)) == list.sum
    }
  }

  test("List.apply"){
    check{ list:SList[Int] =>
      List(list :_*) == toLst(list)
    }
  }
}
