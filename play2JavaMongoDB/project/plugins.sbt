// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("play" % "sbt-plugin" % "2.0.4")
//addSbtPlugin("com.google.code.gson" % "gson" % "2.2.2")
//addSbtPlugin("org.mongodb" % "mongo-java-driver" % "2.9.1")