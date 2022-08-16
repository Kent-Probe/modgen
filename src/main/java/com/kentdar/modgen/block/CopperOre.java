package com.kentdar.modgen.block;

import com.kentdar.modgen.sound.ModSoundsEvents;
import com.kentdar.modgen.world.dimension.KJTeleporter;
import com.kentdar.modgen.world.dimension.ModDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class CopperOre extends Block {

    public CopperOre(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            if(!player.isCrouching()){
                MinecraftServer server = worldIn.getServer();
                if(server != null){
                    if(worldIn.getDimensionKey() == ModDimensions.KJDim){
                        ServerWorld overWorld = server.getWorld(World.OVERWORLD);
                        if(overWorld != null){
                            player.changeDimension(overWorld, new KJTeleporter(pos, false));
                        }
                    }else {
                        ServerWorld kjDim = server.getWorld(ModDimensions.KJDim);
                        if(kjDim != null){
                            player.changeDimension(kjDim, new KJTeleporter(pos, true));
                        }
                    }
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {

        if(worldIn.isRemote()){
            //assert Minecraft.getInstance().player != null; --> no es necesario
            Minecraft.getInstance().player.playSound(ModSoundsEvents.SMALL_EXPLOSION.get(),
                    1f, 1f);
        }
        super.onPlayerDestroy(worldIn, pos, state);
    }


}
