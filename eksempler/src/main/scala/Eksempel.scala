import annotation.tailrec

object Eksempel {

  // vanlig rekursjon
  def fac(n:Int):Int =
    if(n <= 0) 1 else n * fac(n - 1)

  //
  def factorial(n:Int) = {
    @tailrec
    def go(n:Int, acc:Int):Int =
      if(n <= 0) acc else go(n - 1, n * acc)
    go(n, 1)
  }

  // Oppgave 1
  def fib(n:Int):Int = {
    @tailrec
    def go(previous:Int, current:Int, n:Int):Int =
      if(n == 0) previous
      else go(current, previous + current, n - 1)
    go(0, 1, n)
  }

  def fib0(n:Int):Int =
    if(n == 0 || n == 1) n
    else fib0(n - 1) + fib(n - 2)

  def formatFac(n:Int) =
    s"factorial av $n = ${factorial(n)}"

  def formatFib(n:Int) =
    s"fibonacci av $n = ${fib(n)}"

  def main(args:Array[String]){
    println(formatFac(10))

    println(formatFib(45))

    println(format("fibonacci", fib, 10))

    println(format("fib", fib0, 10))

    println(findFirstM(Array("a", "b", "c"), "b"))

    println(findFirstP[Int](Array(1,2,3,4), a => a > 2))
  }

  def format(name:String, f:Int => Int, n:Int) =
    s"$name av $n = ${f(n)}"

  def findFirstM(ss:Array[String], s:String) = {
    @tailrec
    def go(n:Int):Int =
      if(n >= ss.length) -1
      else if(ss(n) == s) n
      else go(n + 1)
    go(0)
  }

  def findFirstP[A](as:Array[A], p:A => Boolean) = {
    @tailrec
    def go(n:Int):Int =
      if(n >= as.length) -1
      else if(p(as(n))) n
      else go(n + 1)
    go(0)
  }

  def isSorted[A](as:Array[A], gt:(A, A) => Boolean) = {
    def go(n:Int):Boolean =
      n == as.length || (gt(as(n - 1), as(n)) && go(n + 1))
    go(0)
  }
}