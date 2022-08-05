package com.kentdar.modgen.block;

import com.kentdar.modgen.container.ElectrifierContainer;
import com.kentdar.modgen.tileentity.ElectrifierTile;
import com.kentdar.modgen.tileentity.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

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

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote()){
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof ElectrifierTile){
                INamedContainerProvider containerProvider = new INamedContainerProvider() {
                    @Override
                    public ITextComponent getDisplayName() {
                        return new TranslationTextComponent("screen.modgen.electrifier");
                    }

                    @Nullable
                    @Override
                    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {

                        return new ElectrifierContainer(i, worldIn, pos, playerInventory, playerEntity);
                    }
                };
                NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());
            }else {
                throw new IllegalStateException("Our Named Container Provider Is Missing");
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.ELECTRIFIER_TILE_ENTITY.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
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
