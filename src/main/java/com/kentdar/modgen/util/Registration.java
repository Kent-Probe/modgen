package com.kentdar.modgen.util;

import com.kentdar.modgen.ModGen;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
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

    //Entidades
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ModGen.MOD_ID);

    public static void init(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        FLUIDS.register(eventBus);
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
