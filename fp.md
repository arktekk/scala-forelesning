# FP i Scala

--

## Kaare Nilsen

## Jon-Anders Teigen

--

## Agenda

* hva er FP
* hvorfor FP
* hvordan FP

--

## FP er programmering med funksjoner!

funksjoner uten sideeffekter <!-- .element class="fragment" -->
pure/rene funksjoner         <!-- .element class="fragment" -->

--

## Sideeffekt
* endre en variabel
* mutere en datastruktur
* sette et felt på et objekt
* kaste exceptions
* skrive og lese fra konsoll
* skrive og lese fra nettverk
* etc..

--

## referentially transparent
```scala
val a = "Hello"
val b = a + ", World"
val c = a + ", World"
```

substitution model
vi kan erstatte et uttrykk med resultatet

dette lærte du på barneskolen, ```2 + 3 = 5```

--

## referentially opaque
```scala
val a = new StringBuilder("Hello")
val b = a.append(", World").toString
val c = a.append(", World").toString
```
b og c ser ut som to like uttrykk, men refererer til ```a``` som har forskjellig verdi og muteres.
dette er utrolig komplekst og vanskelig!

--

## parametrisering
* verdien av å ikke vite

--

## rekursjon
<pre>
  hvis du har skjønt rekursjon: kult
  hvis ikke: se rekursjon
</pre>

--

## single-linket liste
```scala
sealed trait List[A]{
  def map[B](f:A => B):List[B] = this match {
    case Empty() => Empty()
    case Cons(a, t) => Cons(f(a), t map f)
  }
}
case class Cons[A](head:A, tail:List[A]) extends List[A]
case class Empty[A]() extends List[A]

val list = Cons(1, Cons(2, Cons(3, Empty())))

list.map(a => a + 1) == Cons(2, Cons(3, Cons(4, Empty())))
```

--

<!-- .slide: data-background="#fff" -->
![list](img/listmonster.png)

--

## hva er en pure funksjon ?
en funksjon fra A til B er en relasjon mellom
hver eneste verdi av typen A til nøyaktig 1 verdi av typen B

--

## f.eks
```scala
def stringy(i:Int) = "number " + i

Int => String
```

--

## scala
* hybrid - fp & oo
* kraftig typesystem
* jvm

--

## scala og fp
* første klasses funksjoner
* pattern matching
* parametrisk polymorfi
* declaration site varians
* algebraiske datatyper
* typeklasser

--

## parametrisk polymorfi

--

## funksjoner

--

## pattern matching

--

## varians

--

## algebraiske datatyper

--

## typeklasser

--

