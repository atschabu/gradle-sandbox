package atschabu.gradle.test.helper.blocks

import atschabu.gradle.test.helper.BuilderBlock

class BuildScriptDependenciesBuilderBlock implements BuilderBlock {

    private dependencies = []

    /**
     * adds the given dependency notation to the list of buildscript classpath dependencies
     * @param dependency notation
     * @return itself
     */
    BuildScriptDependenciesBuilderBlock withDependency(dependency) {
        dependencies.add(dependency)
        return this
    }

    def depsWriter = { w ->
        dependencies.each {
            w.write "\t\tclasspath ${it}\n"
        }
    }

    void build(Writer w) {
        w.write """
buildscript {
    dependencies {
$depsWriter
    }
}
"""
    }
}
