libraryDependencies <<= (scalaVersion, libraryDependencies) { (scv, deps) =>
    if (scv == "2.8.1")
        deps :+ "org.clapper" %% "sbt-editsource" % "0.4"
    else
        deps
}
