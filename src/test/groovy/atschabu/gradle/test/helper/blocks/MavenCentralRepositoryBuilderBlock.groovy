package atschabu.gradle.test.helper.blocks

import atschabu.gradle.test.helper.BuilderBlock

class MavenCentralRepositoryBuilderBlock implements BuilderBlock {

    void build(Writer w) {
        w.write """
repositories {
    mavenCentral()
}
"""
    }
}
