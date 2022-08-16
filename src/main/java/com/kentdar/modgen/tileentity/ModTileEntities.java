package com.kentdar.modgen.tileentity;

import com.kentdar.modgen.block.ModBlocks;
import com.kentdar.modgen.util.Registration;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class ModTileEntities {
     public static final RegistryObject<TileEntityType<ElectrifierTile>> ELECTRIFIER_TILE_ENTITY
             = Registration.TILE_ENTITY_TYPES.register("electrifier_tile_entity",
             () -> TileEntityType.Builder.create(() -> new ElectrifierTile(),
                     ModBlocks.ELECTRIFIER.get()).build(null));

     public static final RegistryObject<TileEntityType<BigChestTile>> BIG_CHEST_TILE_ENTITY
             = Registration.TILE_ENTITY_TYPES.register("big_chest_tile_entity",
             () -> TileEntityType.Builder.create(() -> new BigChestTile(),
                     ModBlocks.BIG_CHEST.get()).build(null));

     public static void register(){ }
}
