package music

import unfiltered.filter.Plan
import unfiltered.request._
import unfiltered.response._
import scala.xml.NodeSeq
import unfiltered.response.Html5

/**
 * Oppgaver
 *
 * - List ut alle artister
 * - List ut alle sanger for en artist
 * - List ut alle tags
 * - List ut alle sanger for en gitt tag
 * - List ut alle artister for en gitt tag
 * - Søk på artist
 * - Søk på sang
 * - Hvilke sanger er de mest vanlige (mest 'similar' til andre sanger)
 */
object Music {
  def page(title:String)(body:NodeSeq) = Html5(
    <html>
      <head>
        <title>{title}</title>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css"/>
      </head>
      <body>
        {body}
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"><!-- --></script>
      </body>
    </html>)
}

case class Song(id:String, title:String, artist:String, tags:List[Tag], similars:List[Similar])
case class Similar(to:String, how:Double)
case class Tag(name:String, value:String)

class Music(songs:List[Song]) extends Plan {
  def intent = {
    case GET(Path("/")) => Ok ~> Music.page("Hello World"){
      <a href="/artists">Artists</a>
    }
    case GET(Path("/artists")) => Ok ~> Music.page("songs"){
      <ul>{songs.map(_.artist).distinct.sorted.map{ artist =>
        <li>{artist}</li>
      }}</ul>
    }
  }
}
