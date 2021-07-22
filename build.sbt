// ---------------------------------------------------------------------------
// SBT Build File for SBT EditSource Plugin
//
// Copyright (c) 2010-2018 Brian M. Clapper
//
// See accompanying license file for license information.
// ---------------------------------------------------------------------------

import sbt.io.Path.userHome
import sbt.librarymanagement.Patterns

lazy val commonSettings = Seq(
  ThisBuild / version := "2.0.0",
  ThisBuild / scalaVersion := "2.12.14",
  ThisBuild / organization := "org.clapper",
  ThisBuild / resolvers += "jitpack" at "https://jitpack.io"
)

lazy val LocalMavenResolverForSbtPlugins = {
  // remove scala and sbt versions from the path, as it does not work with jitpack
  val pattern  = "[organisation]/[module]/[revision]/[module]-[revision](-[classifier]).[ext]"
  val name     = "local-maven-for-sbt-plugins"
  val location = userHome / ".m2" / "repository"
  Resolver.file(name, location)(Patterns().withArtifactPatterns(Vector(pattern)))
}

lazy val root = (project in file(".")).settings(
  commonSettings,

  sbtPlugin := true,
  name := "sbt-editsource",
  description := "SBT plugin to edit files on the fly",
  licenses += ("BSD New", url("https://github.com/bmc/sbt-editsource/blob/master/LICENSE.md")),
  publishMavenStyle := false,
  // bintrayRepository := "sbt-plugins",
  // bintray / bintrayOrganization := None,

  publishArtifact := false,
  makePom / publishArtifact := true,

  resolvers += LocalMavenResolverForSbtPlugins,
  publishM2Configuration := publishM2Configuration.value.withResolverName(LocalMavenResolverForSbtPlugins.name),

  scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature"),
  // Note: To cross-build, use "^compile", not "+compile", and
  // "^publishLocalSigned" or "^publish"
  // crossSbtVersions := Seq("0.13.16", "1.5.5"),
  libraryDependencies += "org.clapper" %% "grizzled-scala" % "4.10.0"
)

