package dev.anullihate;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginLoadOrder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PyPlugins extends PluginBase {

    private static PyPlugins instance = null;

    public static PyPlugins getInstance() {
        return instance;
    }

    public void onLoad() {
        if (!new File("lib/jython-standalone-2.7.1.jar").exists()) {
            getServer().getLogger().critical("Could not find lib/jython-standalone-2.7.1.jar!");
            getServer().getPluginManager().disablePlugin(this);
        } else {
            getServer().getLogger().info("lib/jython-standalone-2.7.1.jar found!");
        }
    }

    public void onEnable() {
        this.getServer().getPluginManager().registerInterface(PyPluginLoader.class);
        List<String> loaders = new ArrayList<>();
        loaders.add(PyPluginLoader.class.getName());
        this.getServer().getPluginManager().loadPlugins(new File(this.getServer().getPluginPath()), loaders, true);
        this.getServer().enablePlugins(PluginLoadOrder.STARTUP);
    }
}
