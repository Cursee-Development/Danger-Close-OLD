package com.cursee.danger_close;

import com.cursee.danger_close.core.util.DangerDetector;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

@Mod(CommonConstants.MOD_ID)
public class DangerCloseNeoForge {

    public static boolean SOULFIRED_INSTALLED = false; // CommonServices.PLATFORM.isModLoaded("soulfired");

    public DangerCloseNeoForge(IEventBus modEventBus) {

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

                    if ((entity instanceof Mob | entity instanceof ServerPlayer)) {
                        DangerDetector.detect((ServerLevel) level, entity);
                    }
                }
            }
        }
    }
}