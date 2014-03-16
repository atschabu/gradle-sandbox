package atschabu.gradle.test.helper.blocks

import atschabu.gradle.test.helper.BuilderBlock

class CustomBuilderBlock implements BuilderBlock {

    private content
    CustomBuilderBlock(content) {
        this.content = content
    }

    void build(Writer w) {
        w.write(content)
    }
}
