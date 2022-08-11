package com.kentdar.modgen.block;

import com.kentdar.modgen.sound.ModSoundsEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class CopperOre extends Block {

    public CopperOre(Properties properties) {
        super(properties);
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
