package com.cursee.danger_close.services;

import com.cursee.danger_close.util.GlobalFabricObjects;
import com.cursee.danger_close.platform.service.IPlatformHelper;
import net.fabricmc.api.EnvType;

public class FabricPlatformHelper implements IPlatformHelper {
    @Override
    public String getModLoaderName() {
        return "Fabric";
    }

    @Override
    public String getGameDirectory() {
        return GlobalFabricObjects.fabricLoader.getGameDir().toString();
    }

    @Override
    public boolean isModLoaded(String modId) {
        return GlobalFabricObjects.fabricLoader.isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return GlobalFabricObjects.fabricLoader.isDevelopmentEnvironment();
    }

    @Override
    public boolean isClientSide() {
        return GlobalFabricObjects.fabricLoader.getEnvironmentType() == EnvType.CLIENT;
    }
}
