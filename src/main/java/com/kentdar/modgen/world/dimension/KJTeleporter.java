package com.kentdar.modgen.world.dimension;

import com.kentdar.modgen.block.CopperOre;
import com.kentdar.modgen.block.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import net.minecraft.entity.Entity;
import java.util.function.Function;

public class KJTeleporter implements ITeleporter {

    public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean insideDimension = true;

    public KJTeleporter(BlockPos pos, boolean insideDim){
        thisPos = pos;
        insideDimension = insideDim;
    }


    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destinationWorld, float yaw, Function<Boolean, Entity> repositionEntity){

        entity = repositionEntity.apply(false);
        double y = 61;
        if(!insideDimension){
            y = thisPos.getY();
        }
        BlockPos destinationPos = new BlockPos(thisPos.getX(), thisPos.getY(), thisPos.getZ());
        int tries = 0;
        while((destinationWorld.getBlockState(destinationPos).getMaterial() != Material.AIR) &&
                !destinationWorld.getBlockState(destinationPos).isReplaceable(Fluids.WATER) &&
                destinationWorld.getBlockState(destinationPos.up()).getMaterial() != Material.AIR &&
                !destinationWorld.getBlockState(destinationPos.up()).isReplaceable(Fluids.WATER) && tries < 25) {

            destinationPos = destinationPos.up(2);
            tries++;
        }

        entity.setPositionAndUpdate(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());

        if(insideDimension) {
            boolean doSetBlock = true;
            for(BlockPos checkPos : BlockPos.getAllInBoxMutable(destinationPos.down(10).west(10), destinationPos.up(10).east(10))){
                if(destinationWorld.getBlockState(checkPos).getBlock() instanceof CopperOre){ //Replace CopperOre with your "Teleporter BLock"
                    doSetBlock = false;
                    break;
                }
            }
            if(doSetBlock){
                destinationWorld.setBlockState(destinationPos, ModBlocks.COPPER_ORE.get().getDefaultState());
            }
        }

        return entity;
    }

}
