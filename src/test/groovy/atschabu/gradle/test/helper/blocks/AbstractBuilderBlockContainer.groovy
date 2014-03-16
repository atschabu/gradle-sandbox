package atschabu.gradle.test.helper.blocks

import atschabu.gradle.test.helper.BuilderBlock

abstract class AbstractBuilderBlockContainer<T extends AbstractBuilderBlockContainer> implements BuilderBlock {
    private List<BuilderBlock> blocks = []

    T withBuilderBlocks(BuilderBlock... blocks) {
        this.blocks.addAll(blocks)
        return this
    }

    void build(Writer w) {
        blocks.each {
            it.build(w)
        }
    }
}
