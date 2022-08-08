package com.kentdar.modgen.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.trees.Tree;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.InterModComms;

import java.util.Random;
import java.util.function.Supplier;

public class RedwoodSapling extends BushBlock implements IGrowable {

    public static final IntegerProperty STAGE = BlockStateProperties.STAGE_0_1;
    private final Supplier<Tree> tree;

    public RedwoodSapling(Supplier<Tree> TreeIn, Properties properties) {
        super(properties);
        this.tree = TreeIn;
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return (double)worldIn.rand.nextFloat() < 0.5d;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        if(!worldIn.isAreaLoaded(pos, 1)) return;
        if(worldIn.getLight(pos.up()) >= 9 && rand.nextInt(7) == 0)
            this.grow(worldIn, rand, pos, state);
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        if(state.get(STAGE) == 0){
            worldIn.setBlockState(pos, state.cycleValue(STAGE));
        }else {
            if(!ForgeEventFactory.saplingGrowTree(worldIn, rand, pos)){
                return;
            }
            tree.get().attemptGrowTree(worldIn, worldIn.getChunkProvider().getChunkGenerator(),
                    pos, state, rand);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }
}
