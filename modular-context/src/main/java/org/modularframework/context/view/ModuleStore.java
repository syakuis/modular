package org.modularframework.context.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 23.
 */
public final class ModuleStore implements Serializable {
    private final Map<String, Module> store;
    private final List<Module> modules;

    public ModuleStore(List<Module> modules) {
        this.modules = Lists.newArrayList(modules);
        this.store = new HashMap<>();

        for (Module module : this.modules) {
            this.store.put(module.getModuleId(), module);
        }
    }

    public List<Module> values() {
        return modules;
    }

    public Module getModule(String moduleId) {
        if (!this.store.containsKey(moduleId)) {
            throw new IllegalArgumentException("module not found");
        }
        return this.store.get(moduleId);
    }

    public String getModuleNameByModuleId(String moduleId) {
        return this.getModule(moduleId).getModuleName();
    }
}
