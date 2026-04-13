package com.azr.client;

public abstract class Module {
    private String name;
    private String description;
    private int key;
    private ModuleCategory category;
    private boolean enabled;

    public Module(String name, String description, int key, ModuleCategory category) {
        this.name = name;
        this.description = description;
        this.key = key;
        this.category = category;
        this.enabled = false;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }

    public boolean isEnabled() { return enabled; }
    public String getName() { return name; }
    // Gerekirse onTick ekleyelim
    public void onTick() {}
}
