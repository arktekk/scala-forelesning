## Scala Forelesning

### Sbt
All kode/slides benytter byggeverktøyet [SBT](http://scala-sbt.org)

For å starte SBT, kjør kommandoen `./sbt` for linux/mac, eller `sbt.bat` for windows

Ønsker du å kjøre slides lokalt, start sbt og kjør kommandoen `slides`

Navigering til et sub-prosjekt gjør du i sbt med kommandoen `project <name>` der &lt;name&gt; er navnet på prosjektet man
vil navigere til.

`compile` for å kompilere kode

`test` for å kjøre tester

`run` for å kjøre applikasjonen

for flere kommandoer og mer informasjon om SBT, se [dokumentasjonen](http://scala-sbt.org)

### Eclipse
Last ned [scala-ide](http://scala-ide.org/)

start sbt, og kjør kommandoen `eclipse` for å generere eclipse prosjekt metadata. Åpne deretter prosjektet i eclipse

### IntelliJ
Åpne plugin manager og installer Scala-Plugin. Importer/Åpne deretter prosjekte i IntelliJ

### Emacs
Følg guiden på [https://github.com/aemoncannon/ensime](https://github.com/aemoncannon/ensime)

start sbt, og kjør kommandoen `ensime generate`, eval deretter `M-ensime` i emacs for å koble til ensime serveren

