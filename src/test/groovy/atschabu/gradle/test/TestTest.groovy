package atschabu.gradle.test

import atschabu.gradle.plugins.DummyPlugin
import atschabu.gradle.test.helper.BuildHandle
import atschabu.gradle.test.helper.ProjectDirBuilder
import atschabu.gradle.test.helper.blocks.CustomBuilderBlock
import atschabu.gradle.test.helper.blocks.PluginsBuilderBlock
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue

class TestTest {

    @Rule
    public TemporaryFolder dir = new TemporaryFolder()

    private ProjectDirBuilder builder
    private BuildHandle handle

    @Before
    void setup() {
        builder = new ProjectDirBuilder(dir)
        builder.withFile('build.gradle').withBuilderBlocks(
                new PluginsBuilderBlock().withPlugin('dummy')
        )

        handle = BuildHandle.create(dir)
    }

    @After
    void cleanup() {
        handle.close()
    }

    @Test
    void dummyPluginIsApplied() {
        builder.build()
        assertNotNull(handle.project.tasks.find { it.name == 'dummy' })
    }

    @Test
    void dummyPluginRunsSuccessful() {
        new DummyPlugin().printSomething()

        builder.build()
        handle.run('dummy')
    }

    @Test
    void addingCustomTask() {
        builder.withFile('build.gradle').withBuilderBlocks(
                new CustomBuilderBlock('''
task('foo') << {
//throw new RuntimeException('foo')
    println "frufru"
}
''')
        )
        builder.build()

//        handle.launcher.withArguments('--info')
        handle.run('foo')
        assertTrue(handle.standardOutput.toString().contains('frufru'))
    }
}
