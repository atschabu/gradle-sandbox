package atschabu.gradle.test.helper

import org.gradle.tooling.BuildLauncher
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.ProjectConnection
import org.gradle.tooling.model.GradleProject
import org.junit.rules.TemporaryFolder

class BuildHandle {

    private final ProjectConnection connection
    private final BuildLauncher launcher

    final ByteArrayOutputStream standardOutput = new ByteArrayOutputStream()
    final ByteArrayOutputStream standardError = new ByteArrayOutputStream()

    static BuildHandle create(TemporaryFolder projectDir) {
        GradleConnector connector = GradleConnector.newConnector()
        connector.useGradleUserHomeDir(null)
        connector.forProjectDirectory(projectDir.root)
        ProjectConnection connection = connector.connect()

        return new BuildHandle(connection)
    }

    BuildHandle(ProjectConnection connection) {
        this.connection = connection

        launcher = connection.newBuild()

        launcher.standardOutput = this.standardOutput
        launcher.standardError = this.standardError
    }

    GradleProject getProject() {
        return connection.getModel(GradleProject)
    }

    /**
     * will set arguments, removing any previously set arguments
     * @param args
     * @return
     */
    BuildHandle withArguments(String ... args) {
        launcher.withArguments(args)
        return this
    }

    BuildHandle run(String... tasks) {
        launcher.forTasks(tasks)
        launcher.run()
        return this
    }

    void close() {
        connection.close()
    }

}
