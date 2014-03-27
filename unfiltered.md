# unfiltered

--

* enkelt
* lite (core er 1376 CLOC)
* HTTP orientert

--

## Hva ?

* http api
* servlet filters (2.3)
* async servlet filters (3.0)
* netty
* websockets
* uploads
* oauth
* ++

--

```
GET / HTTP/1.1
User-Agent: curl/7.19.7
Host: localhost:8080
Accept: */*

HTTP/1.1 200 OK
Content-Type: text/html; charset=utf-8
Content-Length: 357
Server: Jetty(7.6.0.v20120127)
```

```scala
import unfiltered.filter.Plan
import unfiltered.response._
import unfiltered.request._

class Example extends Plan {
  def intent = {
    case GET(Path("/")) => OK ~> Html5(<html>...</html>)
  }
}```

--

## Matching Requests ( Path parameters )

```
GET /item/5 HTTP/1.1
User-Agent: curl/7.19.7
Host: localhost:8080
Accept: */*
```

```scala
def intent = {
  case Path(Seg(List("item", number))) => // number == "5"
    Ok ~> Html5(...)

  case Path(Seg(List("you", can, "have", many))) =>
    Ok ~> Html5(...)
}
```

--

## Matching Requests (Query parameters)

```
GET /item?filter=foo&filter=bar&monkey=donkey HTTP/1.1
User-Agent: curl/7.19.7
Host: localhost:8080
Accept: */*
```

```scala
def intent = {
  case Path("/item") & QueryParams(q) =>
    val filter = q("filter") // Seq("foo", "bar")
    val monkey = q("monkey") // Seq("donkey")
    val nope   = q("nope")   // Seq()
    Ok ~> Html5(...)
}
```

--

## Compose a Response

```
HTTP/1.1 503 Service Unavailable
Content-Type: text/xml; charset=utf-8
Content-Length: 28
Server: Jetty(7.6.0.v20120127)

<error>not available</error>
```

```scala
def intent = {
  case Path("/") =>
    ServiceUnavailable ~> TextXmlContent ~>
      ResponseString(<error>not available</error>)
}
```

--

## Templating ?

```scala
<ul>{ Seq(1,2,3).map{ i => 
  <li>{i}</li> }}
</ul>
```

```html
<ul>
  <li>1</li>
  <li>2</li>
  <li>3</li>
</ul>
```

--

## Eksempel

```scala
def form(msg:String) = Html5(
  <div>
    <p>{msg}</p>
    <form action="/" method="post">
      <input type="text" name="name"/>
      <input type="submit"/>
    </form>
  </div>)

def intent = {
  case req @ Path("/") => req match {
    case GET(_) =>
      form("")
    case POST(Params(p)) =>
      p("name").headOption match {
        case Some(name) => Html5(<hi>{name}</hi>)
        case _          => form("name is required")
      }
    case _ => MethodNotAllowed
  }
}
```