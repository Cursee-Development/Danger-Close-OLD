package com.cursee.example.platform;

import com.cursee.example.platform.service.IPlatformHelper;

import java.util.ServiceLoader;

public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("[ExampleMod] Failed to load service for " + clazz.getName() + "."));
    }
}