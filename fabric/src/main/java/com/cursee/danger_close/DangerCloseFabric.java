package com.cursee.danger_close;

import com.cursee.danger_close.core.util.DangerDetector;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

public class DangerCloseFabric implements ModInitializer {

	public static boolean SOULFIRED_INSTALLED = false; //CommonServices.PLATFORM.isModLoaded("soulfired");

	@Override
	public void onInitialize() {

		DangerClose.init();

		SOULFIRED_INSTALLED = false; //CommonServices.PLATFORM.isModLoaded("soulfired");

		ServerTickEvents.START_SERVER_TICK.register(new ServerTickHandler());
	}

	public static class ServerTickHandler implements ServerTickEvents.StartTick {
		@Override
		public void onStartTick(MinecraftServer server) {

			if (server.getTickCount() % 2 != 0) {
				return;
			}

			server.getAllLevels().forEach((level -> {
				for (Entity generic : level.getAllEntities()) {
					if (generic instanceof LivingEntity entity) {
						if (!level.isClientSide() && (entity instanceof Mob || entity instanceof ServerPlayer)) {
							DangerDetector.detect(level, entity);
						}
					}
				}
			}));
		}
	}

}