package com.cursee.danger_close;

import com.cursee.danger_close.core.util.DangerDetector;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(CommonConstants.MOD_ID)
public class DangerCloseForge {

    public static boolean SOULFIRED_INSTALLED = false; // CommonServices.PLATFORM.isModLoaded("soulfired");
	
    public DangerCloseForge() {

        DangerClose.init();

        SOULFIRED_INSTALLED = false; // CommonServices.PLATFORM.isModLoaded("soulfired");
    }

    @Mod.EventBusSubscriber(modid = CommonConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class NeoEventBusListeners {

        @SubscribeEvent
        public static void onLivingTick(LivingEvent.LivingTickEvent event) {

            LivingEntity entity = event.getEntity();
            Level level = entity.level();

            if (!level.isClientSide()) {
                MinecraftServer server = level.getServer();
                if (server != null &&  server.getTickCount() % 2 == 0) {
                    if ((entity instanceof Mob || entity instanceof ServerPlayer)) {
                        DangerDetector.detect((ServerLevel) level, entity);
                    }
                }
            }
        }
    }
}