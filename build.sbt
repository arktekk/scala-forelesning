shellPrompt in ThisBuild := { s => Project.extract(s).currentProject.id + "> " }

