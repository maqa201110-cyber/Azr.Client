package com.azr.client;

public enum ModuleCategory {
    COMBAT("Savaş"),
    MOVEMENT("Hareket"),
    PLAYER("Oyuncu"),
    VISUALS("Görsel"),
    CLIENT("Sistem"),
    SEARCH("Arama");

    private final String name;

    ModuleCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
