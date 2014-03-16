package atschabu.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class DummyPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.task('dummy') << {
            println "being a dummy in software development is so much better, than in the car manufacturing industry"
        }
    }

}
