package com.cursee.danger_close.platform;

import com.cursee.danger_close.platform.service.IPlatformHelper;

import java.util.ServiceLoader;

public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("[Danger Close] Failed to load service for " + clazz.getName() + "."));
    }
}