package atschabu.gradle.test.helper.blocks

class AllProjectsBuilderBlock extends AbstractBuilderBlockContainer<AllProjectsBuilderBlock> {

    void build(Writer w) {
        w.write 'allprojects {\n'
        super.build(w)
        w.write('}\n')
    }
}
