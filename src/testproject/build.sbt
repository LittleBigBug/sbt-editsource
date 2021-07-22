
name := "sbt-editsource-test"

version := "0.4"

organization := "org.clapper"

libraryDependencies += "org.clapper" %% "grizzled-scala" % "4.10.0"

(EditSource / sources) ++= (baseDirectory.value / "src" * "*.txt").get ++
                            (baseDirectory.value / "src" * "*.md").get

//    (bd / "src" * "*.md")

EditSource / targetDirectory := baseDirectory.value / "target"

EditSource / variables += "organization" -> organization.value

EditSource / variables += "foo" -> "bar"

EditSource / flatten := true

EditSource / substitutions += sub("""build""".r, "Build")

EditSource / substitutions ++= Seq(
    sub("""(?i)\btest\b""".r, "TEST", SubAll),
    sub("""\b(?i)simple build tool\b""".r, "Scalable Build Tool")
)

Compile / compile := ((Compile / compile) dependsOn (EditSource / edit)).value
