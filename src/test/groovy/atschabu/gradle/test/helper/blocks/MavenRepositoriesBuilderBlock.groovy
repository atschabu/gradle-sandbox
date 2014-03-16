package atschabu.gradle.test.helper.blocks

import atschabu.gradle.test.helper.BuilderBlock

class MavenRepositoriesBuilderBlock implements BuilderBlock {

    private repos = []

    /**
     * adds the given repo to the list of repositories
     * @param repo the url to the repository
     * @return itself
     */
    MavenRepositoriesBuilderBlock withRepository(repo) {
        repos.add(repo)
        return this
    }

    def reposWriter = { w ->
        repos.each {
            w.write "\t\turl('${it}')\n"
        }
    }

    void build(Writer w) {
        w.write """
repositories {
    maven {
$reposWriter
    }
}
"""
    }
}
