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
  def fib(n:Int):Int = ???

  def formatFac(n:Int) =
    s"factorial av $n = ${factorial(n)}"

  def formatFib(n:Int) =
    s"fibonacci av $n = ${fib(n)}"

  def main(args:Array[String]){
    (1 to 10).foreach(n => println(formatFac(n)))
    println()
    (0 to 10).foreach(n => println(formatFib(n)))
  }
}