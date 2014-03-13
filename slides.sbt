seq(webSettings :_*)

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-webapp" % "9.1.0.v20131115" % "container",
  "org.eclipse.jetty" % "jetty-plus"   % "9.1.0.v20131115" % "container")

val slides = taskKey[Unit]("shows slides")

slides := unfiltered.util.Browser.open(s"http://localhost:${(port in container.Configuration).value}")

slides <<= slides.dependsOn(start in container.Configuration)

webappResources in Compile := {
  val root = new java.io.File((thisProjectRef in LocalRootProject).value.build)
  Seq(root, root / "reveal")
}