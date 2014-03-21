package forelesning

sealed trait List[+A]{
  def dropWhile(f:A => Boolean):List[A] = this match {
    case Cons(x, xs) if f(x) => xs.dropWhile(f)
    case _                   => this
  }

  def isEmpty:Boolean = ???

  def size:Int = ???

  def head:A = ???

  def tail:List[A] = ???

  def isDefinedAt[B >: A](b:B):Boolean = ???

  def apply(i:Int):A = ???

  def map[B](f:A => B):List[B] = ???

  def append[B >: A](next: List[B]):List[B] = ???

  def flatMap[B](f:A => List[B]):List[B] = ???

  def filter(f:A => Boolean):List[A] = ???

  def reverse:List[A] = ???

  def foldLeft[B](z:B)(f:(B, A) => B):B = ???

  def foldRight[B](z:B)(f:(A, B) => B):B = ???
}
case class Cons[+A](override val head:A, override val tail:List[A]) extends List[A]
case object Nil extends List[Nothing]

object List {

  def apply[A](as:A*):List[A] = ???

  def sum(ints:List[Int]):Int = ints match {
    case Nil         => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds:List[Double]):Double = ds match {
    case Nil          => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs)  => x * product(xs)
  }
}