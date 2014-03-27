package music

import java.io.File

object Main {
  def main(args:Array[String]){
    Database.from(new File("web/data.json")).fold(println, songs =>
      unfiltered.jetty
        .Http(8080)
        .resources(new File("www").toURI.toURL)
        .plan(new Music(songs))
        .run()
    )
  }
}

object Database {
  import argonaut._, Argonaut._
  def from(from:File) = {
    implicit val tag     = casecodec2(Tag, Tag.unapply)("tag", "value")
    implicit val similar = casecodec2(Similar, Similar.unapply)("track", "similarity")
    implicit val songs   = casecodec5(Song, Song.unapply)("id", "title", "artist", "tags", "similars")

    val source = io.Source.fromFile(from)
    try
      source.getLines().mkString("\n").decodeEither[List[Song]]
    finally
      source.close()
  }
}
