package atschabu.gradle.test

import atschabu.gradle.test.helper.BuildHandle
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.testng.annotations.BeforeMethod
import org.testng.annotations.BeforeTest

import static org.junit.Assert.*

class TestTest {
    @Rule
    public TemporaryFolder dir = new TemporaryFolder()

    @Before
    void setup() {
        dir.newFile('build.gradle') << '''
task('foo') << {
//throw new RuntimeException('foo')
    println "frufru"
}
'''
    }

    @Test
    void testSomething() {
        BuildHandle handle = BuildHandle.create(dir.getRoot())
        handle.launcher.forTasks('foo')
//        handle.launcher.withArguments('--info')
        handle.run()
        assertTrue(handle.standardOutput.toString().contains('frufru'))
    }
}
