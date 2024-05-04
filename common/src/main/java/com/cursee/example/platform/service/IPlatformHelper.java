package com.cursee.example.platform.service;

public interface IPlatformHelper {

    String getModLoaderName();

    String getGameDirectory();

    boolean isModLoaded(String modId);

    boolean isDevelopmentEnvironment();

    boolean isClientSide();
}