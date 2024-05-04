package com.cursee.example;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(CommonConstants.MOD_ID)
public class ExampleNeoForge {

    public ExampleNeoForge(IEventBus modEventBus) {

        Example.init();
    }
}