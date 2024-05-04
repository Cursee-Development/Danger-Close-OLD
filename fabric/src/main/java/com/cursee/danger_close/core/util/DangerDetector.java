package com.cursee.danger_close.core.util;

import com.cursee.danger_close.DangerClose;
import com.cursee.danger_close.DangerCloseFabric;
import com.cursee.danger_close.core.optional.SoulFired;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DangerDetector {
	
	private static final boolean shouldDetect = DangerClose.ENABLE_DANGER_CLOSE;
	
	private static final boolean shouldTorchImmolate = DangerClose.TORCHES_BURN;
	private static final boolean shouldSoulTorchImmolate = DangerClose.SOUL_TORCHES_BURN;
	private static final boolean shouldCampfireImmolate = DangerClose.CAMPFIRES_BURN;
	private static final boolean shouldSoulCampfireImmolate = DangerClose.SOUL_CAMPFIRES_BURN;
	
	private static final boolean shouldStonecutterCut = DangerClose.STONECUTTERS_CUT;
	private static final boolean shouldMagmaBlockImmolate = DangerClose.ENABLE_MAGMA_BLOCK_DAMAGE;
	
	private static final boolean shouldBlazeImmolate = DangerClose.ENABLE_BLAZE_DAMAGE;
	private static final boolean shouldMagmaCubeImmolate = DangerClose.ENABLE_MAGMA_CUBE_DAMAGE;
	
	private static void immolate(LivingEntity entity) {
		
//		entity.setSecondsOnFire(2);
		entity.setRemainingFireTicks(2*40);
	}
	
	// Soul Fire'd compatibility
	private static void immolate(LivingEntity entity, boolean isSoulFire) {
		
		if (DangerCloseFabric.SOULFIRED_INSTALLED) {
			if (isSoulFire) { SoulFired.setOnSoulFire(entity, 2); }
			else { SoulFired.setOnRegularFire(entity, 2); }
			return;
		}
		immolate(entity);
	}
	
	private static void spreadFire(LivingEntity pLiving0, LivingEntity pLiving1) {
		
		if (pLiving1.isOnFire() && !pLiving0.isOnFire()) {
			
			if (DangerCloseFabric.SOULFIRED_INSTALLED) {
//				SoulFired.setOnTypedFire(pLiving0, 2, ((FireTyped) pLiving1).getFireType());

				pLiving0.setRemainingFireTicks(2*40);
				return;
			}
			
			immolate(pLiving0);
		}
		else if (pLiving0.isOnFire() && !pLiving1.isOnFire()) {
			
			if (DangerCloseFabric.SOULFIRED_INSTALLED) {
//				SoulFired.setOnTypedFire(pLiving1, 2, ((FireTyped) pLiving0).getFireType());

				pLiving1.setRemainingFireTicks(2*40);
				return;
			}
			
			immolate(pLiving1);
		}
		
		if ((shouldBlazeImmolate && pLiving1 instanceof Blaze) || (shouldMagmaCubeImmolate && pLiving1 instanceof MagmaCube)) {
			immolate(pLiving0);
		}
	}
	
	public static void detect(ServerLevel level, LivingEntity entity) {
		
		if (shouldDetect && (level != null && entity != null)) {
			
			List<LivingEntity> nearby = level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, entity, entity.getBoundingBox());
			
			for (LivingEntity pLiving1 : nearby) {
				spreadFire(entity, pLiving1);
			}
			
			if (entity.onGround()) {
				
				BlockPos pos = entity.blockPosition();
				
				BlockState insideBlockState = level.getBlockState(pos);
				
				Stream<TagKey<Block>> insideBlockTagStream = insideBlockState.getTags();
				Stream<TagKey<Block>> belowBlockTagStream = level.getBlockState(pos.below()).getTags();
				
				List<TagKey<Block>> insideReStreamable = new ArrayList<>();
				List<TagKey<Block>> belowReStreamable = new ArrayList<>();
				
				insideBlockTagStream.forEach(insideReStreamable::add);
				belowBlockTagStream.forEach(belowReStreamable::add);
				
				if (shouldTorchImmolate && (insideReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.TORCH_BURN_DANGER)) || belowReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.TORCH_BURN_DANGER)))) {
					immolate(entity);
				}
				else if (shouldSoulTorchImmolate && (insideReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.SOUL_TORCH_BURN_DANGER)) || belowReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.SOUL_TORCH_BURN_DANGER)))) {
					immolate(entity, true);
				}
				
				
				if (shouldCampfireImmolate && (insideReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.CAMPFIRE_BURN_DANGER)) || belowReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.CAMPFIRE_BURN_DANGER)))) {
					if (insideBlockState.hasProperty(CampfireBlock.LIT) && insideBlockState.getValue(CampfireBlock.LIT)) {
						immolate(entity);
					}
				}
				else if (shouldSoulCampfireImmolate && (insideReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.SOUL_CAMPFIRE_BURN_DANGER)) || belowReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.SOUL_CAMPFIRE_BURN_DANGER)))) {
					if (insideBlockState.hasProperty(CampfireBlock.LIT) && insideBlockState.getValue(CampfireBlock.LIT)) {
						immolate(entity, true);
					}
				}
				
				if (shouldStonecutterCut && (insideReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.STONECUTTER_DANGER)) || belowReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.STONECUTTER_DANGER)))) {
					entity.hurt(level.damageSources().cactus(), 4.0F);
				}
				
				if (shouldMagmaBlockImmolate && (insideReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.MAGMA_BURN_DANGER)) || belowReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.MAGMA_BURN_DANGER)))) {
					if (!insideBlockState.getBlock().getDescriptionId().toLowerCase().contains("cauldron")) {
						immolate(entity);
					}
				}
			}
		}
	}
	
}