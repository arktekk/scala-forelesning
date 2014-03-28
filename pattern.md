# Pattern matching

--

```scala
sealed trait Tree
case class Leaf(value:Int) extends Tree
case class Branch(left:Tree, right:Tree) extends Tree

def sum(tree:Tree):Int = tree match {
  case Leaf(value) => value
  case Branch(left, right) => sum(left) + sum(right)
}

val tree = Branch(
  Branch(Leaf(1), Leaf(2)),
  Branch(Leaf(3), Leaf(4)))

sum(tree)
```

--

## tuple

```scala
val pair = ("Hello", 5)

pair._1 // "Hello"
pair._2 // 5

pair match {
  case (a, b) =>
    // a == "Hello"
    // b == 5
}
```

--

## extractors

```scala
object ... {
  def unapply(a:A):Boolean
  def unapply(a:A):Option[..]
  def unapplySeq(a:A):Option[Seq[..]]
}
```

--

```scala
sealed trait Tree
object Branch {
  private class Impl(val l:Tree, r:Tree) extends Tree
  def apply(l:Tree, r:Tree):Tree = new Impl(l, r)
  def unapply(t:Tree) = t match {
    case i:Impl => Some(i.l, i.r)
    case _ => None
  }
}
object Leaf {
  private class Impl(val v:Int) extends Tree
  def apply(v:Int):Tree = new Impl(v)
  def unapply(t:Tree) = t match {
    case i:Impl => Some(i.v)
    case _ => None
  }
}
```

--

## fungerer også med java-klasser

```scala
object Path {
  def unapply(req: HttpServletRequest) = Some(req.getRequestURI)
}

object GET {
  def unapply(req: HttpServletRequest) = if(req.getMethod.equalsIgnoreCase("GET")) Some(req) else None
}

request match {
  case GET(Path("/foo")) => //create response
}
```