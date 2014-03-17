# Gradle Sandbox
while moving a legacy build to Gradle I came upon several issues. Foremost of course due to my lack of knowledge about Gradle, but some are caused by Gradle itself.

The idea is to collect all code snippets which troubled me over time in a single place (right now they are distributed over several projects, folders and machines ...)

Furthermore I will try to use this space for all future experiments.

## Testing custom Gradle plugins
While ProjectBuilder is a nice tool to create quickly (in terms of execution) a fake project which you can apply plugins to, it does not cover the actual execution of the build. As long as the plugin only uses build-in tasks this might be enough, as you can test if your configuration contains all your fake files and properties. Unfortunately for custom tasks this is no good.

### Gradle integration tests
Using org.gradle:gradle-tooling-api it is possible to easily launch a Gradle build within a well known space, and test the actual execution of all tasks defined in a plugin. The main challenge is to create the fake environment.

While it is easy to create any environment, I will need to be able to replicate the actual development environment (including all its legacy flaws) as close as possible, to make sure my custom tasks only fail when they are supposed to fail (incorrect usage, configuration) and let the user know why they fail, and how it can be fixed.

#### Jacoco for integration tests
while adding jacoco proved easy to do, it is not really usable. Because the created daemons will outlive the build, the code coverage will not be stored until manually stopping the daemons.
