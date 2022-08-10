package com.kentdar.modgen.world.feature;

import com.kentdar.modgen.block.ModBlocks;
import com.kentdar.modgen.block.ModFluids;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<?, ?> OIL_LAKE
            = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "lake_oil",
            Feature.LAKE.withConfiguration(
                    new BlockStateFeatureConfig(ModFluids.CUM_BLOCK.get().getDefaultState())
            ).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(10))));

    public static final ConfiguredFeature<?, ?> BONUS_CHEST
            = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,"bonus_chest",
            Feature.BONUS_CHEST.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));

    private static final BlockClusterFeatureConfig COPPER_SPREAD_CONFIG
            = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.COPPER_BLOCK.get().getDefaultState()),
            new SimpleBlockPlacer())).tries(225).build();
    public static final ConfiguredFeature<?, ?> COPPER_BLOCK_SPREAD
            =Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "copper_spread",
            Feature.RANDOM_PATCH.withConfiguration(COPPER_SPREAD_CONFIG).withPlacement(Placement.CHANCE.configure(new ChanceConfig(200))));
}
