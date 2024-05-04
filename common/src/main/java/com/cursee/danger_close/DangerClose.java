package com.cursee.danger_close;

import com.cursee.danger_close.core.util.CommonConfigIO;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.io.File;
import java.util.Map;

public class DangerClose {

    public static Boolean TORCHES_BURN;
    public static Boolean SOUL_TORCHES_BURN;
    public static Boolean CAMPFIRES_BURN;
    public static Boolean SOUL_CAMPFIRES_BURN;
    public static Boolean STONECUTTERS_CUT;
    public static Boolean ENABLE_BLAZE_DAMAGE;
    public static Boolean ENABLE_MAGMA_CUBE_DAMAGE;
    public static Boolean ENABLE_MAGMA_BLOCK_DAMAGE;

    public static Boolean ENABLE_DANGER_CLOSE;

    public static final TagKey<Block> TORCH_BURN_DANGER = TagKey.create(Registries.BLOCK, new ResourceLocation(CommonConstants.MOD_ID, "torch_burn_danger"));
    public static final TagKey<Block> SOUL_TORCH_BURN_DANGER = TagKey.create(Registries.BLOCK, new ResourceLocation(CommonConstants.MOD_ID, "soul_torch_burn_danger"));
    public static final TagKey<Block> CAMPFIRE_BURN_DANGER = TagKey.create(Registries.BLOCK, new ResourceLocation(CommonConstants.MOD_ID, "campfire_burn_danger"));
    public static final TagKey<Block> SOUL_CAMPFIRE_BURN_DANGER = TagKey.create(Registries.BLOCK, new ResourceLocation(CommonConstants.MOD_ID, "soul_campfire_burn_danger"));
    public static final TagKey<Block> MAGMA_BURN_DANGER = TagKey.create(Registries.BLOCK, new ResourceLocation(CommonConstants.MOD_ID, "magma_burn_danger"));
    public static final TagKey<Block> STONECUTTER_DANGER = TagKey.create(Registries.BLOCK, new ResourceLocation(CommonConstants.MOD_ID, "stonecutter_danger"));

    public static void init() {
        CommonConstants.load();

        if (new File(CommonConfigIO.FILE_PATH).isFile()) {
            System.out.println(CommonConfigIO.FILE_PATH + " was discovered!");
            Map<String, Boolean> LOADED_CONFIG = CommonConfigIO.readConfigurationFromFile();
            for (Map.Entry<String, Boolean> pair : LOADED_CONFIG.entrySet()) {
                switch (pair.getKey()) {
                    case "TORCHES_BURN" -> TORCHES_BURN = pair.getValue();
                    case "SOUL_TORCHES_BURN" -> SOUL_TORCHES_BURN = pair.getValue();
                    case "CAMPFIRES_BURN" -> CAMPFIRES_BURN = pair.getValue();
                    case "SOUL_CAMPFIRES_BURN" -> SOUL_CAMPFIRES_BURN = pair.getValue();
                    case "STONECUTTERS_CUT" -> STONECUTTERS_CUT = pair.getValue();
                    case "ENABLE_BLAZE_DAMAGE" -> ENABLE_BLAZE_DAMAGE = pair.getValue();
                    case "ENABLE_MAGMA_CUBE_DAMAGE" -> ENABLE_MAGMA_CUBE_DAMAGE = pair.getValue();
                    case "ENABLE_MAGMA_BLOCK_DAMAGE" -> ENABLE_MAGMA_BLOCK_DAMAGE = pair.getValue();
                    case "ENABLE_DANGER_CLOSE" -> ENABLE_DANGER_CLOSE = pair.getValue();
                }
            }
        } else {
            System.out.println(CommonConfigIO.FILE_PATH + " was not found!");
            System.out.println("Setting configuration values to default, and initializing the configuration file!");
            blurb();
            TORCHES_BURN = false;
            SOUL_TORCHES_BURN = false;
            CAMPFIRES_BURN = true;
            SOUL_CAMPFIRES_BURN = true;
            STONECUTTERS_CUT = true;
            ENABLE_BLAZE_DAMAGE = true;
            ENABLE_MAGMA_CUBE_DAMAGE = false;
            ENABLE_MAGMA_BLOCK_DAMAGE = true;
            ENABLE_DANGER_CLOSE = true;
            CommonConfigIO.initializeConfiguration();
        }
    }

    private static void blurb() {
        System.out.println("TORCHES_BURN set to" + " false!");
        System.out.println("SOUL_TORCHES_BURN set to" + " false!");
        System.out.println("CAMPFIRES_BURN set to" + " true!");
        System.out.println("SOUL_CAMPFIRES_BURN set to" + " true!");
        System.out.println("STONECUTTERS_CUT set to" + " true!");
        System.out.println("ENABLE_BLAZE_DAMAGE set to" + " true!");
        System.out.println("ENABLE_MAGMA_CUBE_DAMAGE set to" + " false!");
        System.out.println("ENABLE_MAGMA_BLOCK_DAMAGE set to" + " true!");
        System.out.println("ENABLE_DANGER_CLOSE set to" + " true!");
    }
}
