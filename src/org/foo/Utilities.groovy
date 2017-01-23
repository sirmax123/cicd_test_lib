package org.foo

import java.util.regex.Matcher
import java.util.regex.Pattern
import groovy.io.FileType


class Utilities {
  def method1(arg) {
    println(arg)
    println("method1 TEST TEST")
  }
//  def method2(args) {
//    println(args)
//  }


  def buildName(currentBuild, env) {
    println("set name")
        try {
      upstreamBuildName =  currentBuild.rawBuild.getCause(hudson.model.Cause$UpstreamCause).properties.upstreamProject
      upstreamBuildNumber =  currentBuild.rawBuild.getCause(hudson.model.Cause$UpstreamCause).properties.upstreamBuild
      println(upstreamBuildName)
      println(upstreamBuildNumber)
        
      currentBuild.displayName = "${env.BUILD_NUMBER}-UpstreamJob=${upstreamBuildName}-UpstreamBuildNumber=${upstreamBuildNumber}"
      currentBuild.description = currentBuild.displayName
    }
    catch (java.lang.NullPointerException e) {
      println("No upstream job found")    
    }



    try {
      def specificCause = currentBuild.rawBuild.getCause(hudson.model.Cause$UserIdCause).properties
      println(specificCause.userId)
      currentBuild.displayName = "${env.BUILD_NUMBER}-startedBy: ${specificCause.userId}"
      currentBuild.description = "${env.BUILD_NUMBER}-startedBy: ${specificCause.userId}"
    }     
    catch (java.lang.NullPointerException e) {
      println("No user found")
    }
  }

}