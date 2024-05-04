package com.cursee.danger_close.platform.service;

public interface IPlatformHelper {

    String getModLoaderName();

    String getGameDirectory();

    boolean isModLoaded(String modId);

    boolean isDevelopmentEnvironment();

    boolean isClientSide();
}