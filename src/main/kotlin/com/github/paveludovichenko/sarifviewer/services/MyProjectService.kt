package com.github.paveludovichenko.sarifviewer.services

import com.intellij.openapi.project.Project
import com.github.paveludovichenko.sarifviewer.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
