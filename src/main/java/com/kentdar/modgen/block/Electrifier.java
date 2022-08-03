package com.kentdar.modgen.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class Electrifier extends Block {

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(15, 11, 2, 16, 13, 14),
            Block.makeCuboidShape(0, 0, 0, 1, 1, 1),
            Block.makeCuboidShape(0, 0, 15, 1, 1, 16),
            Block.makeCuboidShape(15, 0, 15, 16, 1, 16),
            Block.makeCuboidShape(15, 0, 0, 16, 1, 1),
            Block.makeCuboidShape(1, 1, 2, 15, 11, 14),
            Block.makeCuboidShape(0, 1, 14, 2, 11, 16),
            Block.makeCuboidShape(14, 1, 14, 16, 11, 16),
            Block.makeCuboidShape(14, 1, 0, 16, 11, 2),
            Block.makeCuboidShape(0, 1, 0, 2, 11, 2),
            Block.makeCuboidShape(0, 11, 0, 16, 13, 2),
            Block.makeCuboidShape(0, 11, 14, 16, 13, 16),
            Block.makeCuboidShape(0, 11, 2, 1, 13, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();


    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(2, 11, 0, 14, 13, 1),
            Block.makeCuboidShape(0, 0, 15, 1, 1, 16),
            Block.makeCuboidShape(15, 0, 15, 16, 1, 16),
            Block.makeCuboidShape(15, 0, 0, 16, 1, 1),
            Block.makeCuboidShape(0, 0, 0, 1, 1, 1),
            Block.makeCuboidShape(2, 1, 1, 14, 11, 15),
            Block.makeCuboidShape(14, 1, 14, 16, 11, 16),
            Block.makeCuboidShape(14, 1, 0, 16, 11, 2),
            Block.makeCuboidShape(0, 1, 0, 2, 11, 2),
            Block.makeCuboidShape(0, 1, 14, 2, 11, 16),
            Block.makeCuboidShape(0, 11, 0, 2, 13, 16),
            Block.makeCuboidShape(14, 11, 0, 16, 13, 16),
            Block.makeCuboidShape(2, 11, 15, 14, 13, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(15, 11, 2, 16, 13, 14),
            Block.makeCuboidShape(0, 0, 0, 1, 1, 1),
            Block.makeCuboidShape(0, 0, 15, 1, 1, 16),
            Block.makeCuboidShape(15, 0, 15, 16, 1, 16),
            Block.makeCuboidShape(15, 0, 0, 16, 1, 1),
            Block.makeCuboidShape(1, 1, 2, 15, 11, 14),
            Block.makeCuboidShape(0, 1, 14, 2, 11, 16),
            Block.makeCuboidShape(14, 1, 14, 16, 11, 16),
            Block.makeCuboidShape(14, 1, 0, 16, 11, 2),
            Block.makeCuboidShape(0, 1, 0, 2, 11, 2),
            Block.makeCuboidShape(0, 11, 0, 16, 13, 2),
            Block.makeCuboidShape(0, 11, 14, 16, 13, 16),
            Block.makeCuboidShape(0, 11, 2, 1, 13, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(2, 11, 0, 14, 13, 1),
            Block.makeCuboidShape(0, 0, 15, 1, 1, 16),
            Block.makeCuboidShape(15, 0, 15, 16, 1, 16),
            Block.makeCuboidShape(15, 0, 0, 16, 1, 1),
            Block.makeCuboidShape(0, 0, 0, 1, 1, 1),
            Block.makeCuboidShape(2, 1, 1, 14, 11, 15),
            Block.makeCuboidShape(14, 1, 14, 16, 11, 16),
            Block.makeCuboidShape(14, 1, 0, 16, 11, 2),
            Block.makeCuboidShape(0, 1, 0, 2, 11, 2),
            Block.makeCuboidShape(0, 1, 14, 2, 11, 16),
            Block.makeCuboidShape(0, 11, 0, 2, 13, 16),
            Block.makeCuboidShape(14, 11, 0, 16, 13, 16),
            Block.makeCuboidShape(2, 11, 15, 14, 13, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public Electrifier(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)){
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            case SOUTH:
                return SHAPE_S;
            default:
                return SHAPE_N;

        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.with(FACING, direction.rotate(state.get(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
