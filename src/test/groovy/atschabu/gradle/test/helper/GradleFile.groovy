package atschabu.gradle.test.helper

import atschabu.gradle.test.helper.blocks.AbstractBuilderBlockContainer

class GradleFile extends AbstractBuilderBlockContainer<GradleFile> {

    final File file

    public GradleFile(File file) {
        this.file = file
    }

    void build() {
        file.withWriter { w ->
            super.build(w)
        }
    }

}
