package com.kentdar.modgen.util;

import com.kentdar.modgen.ModGen;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    //Bloque
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, ModGen.MOD_ID);
    //Item
    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, ModGen.MOD_ID);

    //Fluidos
    public static final DeferredRegister<Fluid> FLUIDS
            = DeferredRegister.create(ForgeRegistries.FLUIDS, ModGen.MOD_ID);

    //Entidades de azulejo
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ModGen.MOD_ID);

    //GUI
    public static final DeferredRegister<ContainerType<?>> CONTAINER
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, ModGen.MOD_ID);

    //Mob
    public static final DeferredRegister<EntityType<?>> ENTITIES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, ModGen.MOD_ID);

    //Biomes
    public static final DeferredRegister<Biome> BIOMES
            = DeferredRegister.create(ForgeRegistries.BIOMES, ModGen.MOD_ID);

    //Constructor de superficies
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDER
            = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, ModGen.MOD_ID);

    //Evento de sonido
    public static final DeferredRegister<SoundEvent> SOUND_EVENT
            = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ModGen.MOD_ID);

    //Encantamiento
    public static final DeferredRegister<Enchantment> ENCHANTMENT
            = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ModGen.MOD_ID);


    public static void init(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        FLUIDS.register(eventBus);
        TILE_ENTITY_TYPES.register(eventBus);
        CONTAINER.register(eventBus);
        ENTITIES.register(eventBus);
        SURFACE_BUILDER.register(eventBus);
        BIOMES.register(eventBus);
        SOUND_EVENT.register(eventBus);
        ENCHANTMENT.register(eventBus);
    }
}
