package com.cursee.example;

import net.fabricmc.api.ModInitializer;

public class ExampleFabric implements ModInitializer {

	@Override
	public void onInitialize() {

		Example.init();
	}
}