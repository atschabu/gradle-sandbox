package atschabu.gradle.test.helper.blocks

import atschabu.gradle.test.helper.BuilderBlock

class MavenLocalRepositoryBuilderBlock implements BuilderBlock {

    void build(Writer w) {
        w.write """
repositories {
    mavenLocal()
}
"""
    }
}
