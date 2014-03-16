package atschabu.gradle.test.helper.blocks

import atschabu.gradle.test.helper.BuilderBlock

class PluginsBuilderBlock implements BuilderBlock {

    private plugins = []

    /**
     * adds the given plugin to the list of plugins to be applied
     * @param plugin
     * @return itself
     */
    PluginsBuilderBlock withPlugin(plugin) {
        plugins.add(plugin)
        return this
    }

    void build(Writer w) {
        plugins.each{
            w.write("apply(plugin: '${it}')\n")
        }
    }
}
