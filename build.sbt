// ---------------------------------------------------------------------------
// SBT Build File for SBT EditSource Plugin
//
// Copyright (c) 2010-2018 Brian M. Clapper
//
// See accompanying license file for license information.
// ---------------------------------------------------------------------------

lazy val commonSettings = Seq(
  ThisBuild / version := "1.0.0",
  ThisBuild / scalaVersion := "2.12.14",
  ThisBuild / organization := "org.clapper"
)

lazy val root = (project in file(".")).settings(
  commonSettings,

  sbtPlugin := true,
  name := "sbt-editsource",
  description := "SBT plugin to edit files on the fly",
  licenses += ("BSD New", url("https://github.com/bmc/sbt-editsource/blob/master/LICENSE.md")),
  publishMavenStyle := false,
  // bintrayRepository := "sbt-plugins",
  // bintray / bintrayOrganization := None,

  scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature"),
  // Note: To cross-build, use "^compile", not "+compile", and
  // "^publishLocalSigned" or "^publish"
  // crossSbtVersions := Seq("0.13.16", "1.5.5"),
  libraryDependencies += "org.clapper" %% "grizzled-scala" % "4.10.0"
)

