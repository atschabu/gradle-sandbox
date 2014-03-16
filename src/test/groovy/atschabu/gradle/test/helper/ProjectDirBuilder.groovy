package atschabu.gradle.test.helper

import atschabu.gradle.test.helper.blocks.BuildScriptDependenciesBuilderBlock
import atschabu.gradle.test.helper.blocks.CustomBuilderBlock
import org.gradle.api.Transformer
import org.gradle.internal.UncheckedException
import org.gradle.internal.classloader.ClasspathUtil
import org.gradle.util.CollectionUtils
import org.gradle.util.TextUtil
import org.junit.rules.TemporaryFolder

class ProjectDirBuilder {

    final TemporaryFolder dir
    private final Map<String, GradleFile> files = [:]

    ProjectDirBuilder(TemporaryFolder dir) {
        this.dir = dir
        addSettingsFile()
    }

    /**
     * @param path
     * @return an instance of GradleFile which will be written to the given path
     * within the this builders TemporyFolder. Returns existing instance if a file
     * with the given path already exists.
     */
    GradleFile withFile(String path) {
        GradleFile file = files.get(path)
        if (!file) {
            file = new GradleFile(dir.newFile(path))
            files.put(path, file)
        }

        return file
    }

    void build() {
        files.values().each {
            it.build()
        }
    }

    private void addSettingsFile() {
        def wr = { w ->
            for (File file : getClasspathAsFiles()) {
                w.write("'${TextUtil.escapeString(file.absolutePath)}',\n")
            }
        }
        def buildScriptDependencies = "files(\n${wr})"

        withFile('settings.gradle').withBuilderBlocks(
                new CustomBuilderBlock('gradle.allprojects{\n'),
                new BuildScriptDependenciesBuilderBlock().withDependency(buildScriptDependencies),
                new CustomBuilderBlock('}\n')
        )
    }

    private List<File> getClasspathAsFiles() {
        List<URL> classpathUrls = ClasspathUtil.getClasspath(this.class.classLoader)
        return CollectionUtils.collect(classpathUrls, new ArrayList<File>(classpathUrls.size()), new Transformer<File, URL>() {
            public File transform(URL url) {
                try {
                    return new File(url.toURI())
                } catch (URISyntaxException e) {
                    throw UncheckedException.throwAsUncheckedException(e)
                }
            }
        })
    }
}
