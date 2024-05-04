package com.cursee.danger_close.core.optional;

//import crystalspider.soulfired.api.FireManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public final class SoulFired {
	public static void setOnRegularFire(Entity entity, int seconds) {
//		FireManager.setOnFire(entity, seconds, FireManager.DEFAULT_FIRE_TYPE);
		entity.setRemainingFireTicks(2 * 20);
	}
	public static void setOnSoulFire(Entity entity, int seconds) {
//		FireManager.setOnFire(entity, seconds, FireManager.SOUL_FIRE_TYPE);
		entity.setRemainingFireTicks(2 * 20);
	}
	
	public static void setOnTypedFire(Entity entity, int seconds, ResourceLocation fireType) {
//		FireManager.setOnFire(entity, seconds, fireType);
		entity.setRemainingFireTicks(2 * 20);
	}
}
