package atschabu.gradle.test.helper

import org.gradle.tooling.BuildLauncher
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.ProgressEvent
import org.gradle.tooling.ProgressListener
import org.gradle.tooling.ProjectConnection

class BuildHandle {

    final BuildLauncher launcher

    final ByteArrayOutputStream standardOutput = new ByteArrayOutputStream()
    final ByteArrayOutputStream standardError = new ByteArrayOutputStream()

    static BuildHandle create(
            File projectDir,
            File userHome = new File(System.getProperty('user.home'))
    ) {
        GradleConnector connector = GradleConnector.newConnector()
        connector.useGradleUserHomeDir(userHome)
        connector.forProjectDirectory(projectDir)
        ProjectConnection connection = connector.connect()

        return new BuildHandle(connection.newBuild())
    }

    BuildHandle(BuildLauncher launcher) {
        this.launcher = launcher

        launcher.standardOutput = this.standardOutput
        launcher.standardError = this.standardError
    }

    void run() {
        launcher.run()
    }
}
