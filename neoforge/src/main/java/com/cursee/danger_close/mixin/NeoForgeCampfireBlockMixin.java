package com.cursee.danger_close.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CampfireBlock.class)
public class NeoForgeCampfireBlockMixin {
	
	@Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
	private void injected(BlockState $$0, Level $$1, BlockPos $$2, Entity $$3, CallbackInfo ci) {
		ci.cancel();
		// if (!CommonServices.PLATFORM.isModLoaded("soulfired")) {
		// 	ci.cancel(); // if soulfired isn't installed, cancel vanilla method
		// }
	}
	
	// @Shadow @Final private int fireDamage;
	//
	// /**
	//  * @author Jason13
	//  * @reason Soulfire'd Compatibility, leave redirected hurt() in place if 'soulfired' mod is loaded
	//  */
	// @Overwrite
	// public void entityInside(BlockState $$0, Level $$1, BlockPos $$2, Entity $$3) {
	// 	if ($$0.getValue(CampfireBlock.LIT) && $$3 instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)$$3)) {
	// 		if (CommonServices.PLATFORM.isModLoaded("soulfired")){
	// 			$$3.hurt($$1.damageSources().inFire(), (float) fireDamage);
	// 		}
	// 	}
	// }
}
