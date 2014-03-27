shellPrompt in ThisBuild := { s => Project.extract(s).currentProject.id + "> " }

scalaVersion in ThisBuild := "2.10.3"

val eksempler = project

val web = project
