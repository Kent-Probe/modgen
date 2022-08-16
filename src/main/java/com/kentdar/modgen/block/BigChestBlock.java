package com.kentdar.modgen.block;

import com.kentdar.modgen.container.BigChestContainer;
import com.kentdar.modgen.tileentity.BigChestTile;
import com.kentdar.modgen.tileentity.ModTileEntities;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.block.*;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class BigChestBlock extends AbstractChestBlock<BigChestTile> {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape SHAPE_NORTH = Block.makeCuboidShape(1.0D, 0.0D, 0.0D, 15.0D, 14.0D, 15.0D);
    protected static final VoxelShape SHAPE_SOUTH = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 16.0D);
    protected static final VoxelShape SHAPE_WEST = Block.makeCuboidShape(0.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    protected static final VoxelShape SHAPE_EAST = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 16.0D, 14.0D, 15.0D);
    protected static final VoxelShape SHAPE_SINGLE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);

    protected BigChestBlock(Properties builder, Supplier<TileEntityType<? extends BigChestTile>> tileEntityTypeSupplier) {
        super(builder, tileEntityTypeSupplier);
    }

    public TileEntityMerger.ICallbackWrapper<? extends ChestTileEntity> combine(BlockState state, World world, BlockPos pos, boolean override) {
        BiPredicate<IWorld, BlockPos> bipredicate;
        if (override) {
            bipredicate = (worldIn, posIn) -> {
                return false;
            };
        } else {
            bipredicate = ChestBlock::isBlocked;
        }

        return TileEntityMerger.func_226924_a_(this.tileEntityType.get(), ChestBlock::getChestMergerType,
                ChestBlock::getDirectionToAttached, FACING, state, world, pos, bipredicate);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return ModTileEntities.BIG_CHEST_TILE_ENTITY.get().create();
    }

    @SuppressWarnings("deprecation")
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.get(TYPE) == ChestType.SINGLE) {
            return SHAPE_SINGLE;
        } else {
            switch(getDirectionToAttached(state)) {
                case NORTH:
                default:
                    return SHAPE_NORTH;
                case SOUTH:
                    return SHAPE_SOUTH;
                case WEST:
                    return SHAPE_WEST;
                case EAST:
                    return SHAPE_EAST;
            }
        }
    }

    public static Direction getDirectionToAttached(BlockState state) {
        Direction direction = state.get(FACING);
        return state.get(TYPE) == ChestType.LEFT ? direction.rotateY() : direction.rotateYCCW();
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        ChestType chesttype = ChestType.SINGLE;
        Direction direction = context.getPlacementHorizontalFacing().getOpposite();
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());

        return this.getDefaultState().with(FACING, direction).with(TYPE, chesttype).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }

    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote())
        {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof BigChestTile)
            {
                INamedContainerProvider containerProvider = new INamedContainerProvider()
                {
                    @Override
                    public ITextComponent getDisplayName()
                    {
                        return new TranslationTextComponent("screen.mccourse.bigchest");
                    }

                    @Override
                    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity)
                    {
                        return new BigChestContainer(i, worldIn, pos, 11, 6, playerInventory);
                    }
                };
                NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());
            }
            else
            {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return ActionResultType.SUCCESS;
    }

    @OnlyIn(Dist.CLIENT)
    public static TileEntityMerger.ICallback<ChestTileEntity, Float2FloatFunction> getLidRotationCallback(final IChestLid lid) {
        return new TileEntityMerger.ICallback<ChestTileEntity, Float2FloatFunction>() {
            public Float2FloatFunction func_225539_a_(ChestTileEntity p_225539_1_, ChestTileEntity p_225539_2_) {
                return (angle) -> {
                    return Math.max(p_225539_1_.getLidAngle(angle), p_225539_2_.getLidAngle(angle));
                };
            }

            public Float2FloatFunction func_225538_a_(ChestTileEntity p_225538_1_) {
                return p_225538_1_::getLidAngle;
            }

            public Float2FloatFunction func_225537_b_() {
                return lid::getLidAngle;
            }
        };
    }

    public static boolean isBlocked(IWorld world, BlockPos pos) {
        return isBelowSolidBlock(world, pos) || isCatSittingOn(world, pos);
    }

    private static boolean isBelowSolidBlock(IBlockReader reader, BlockPos worldIn) {
        BlockPos blockpos = worldIn.up();
        return reader.getBlockState(blockpos).isNormalCube(reader, blockpos);
    }

    private static boolean isCatSittingOn(IWorld world, BlockPos pos) {
        List<CatEntity> list = world.getEntitiesWithinAABB(CatEntity.class, new AxisAlignedBB((double)pos.getX(), (double)(pos.getY() + 1), (double)pos.getZ(), (double)(pos.getX() + 1), (double)(pos.getY() + 2), (double)(pos.getZ() + 1)));
        if (!list.isEmpty()) {
            for(CatEntity catentity : list) {
                if (catentity.isEntitySleeping()) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        BlockState rotated = state.rotate(mirrorIn.toRotation(state.get(FACING)));
        return mirrorIn == Mirror.NONE ? rotated : rotated.with(TYPE, rotated.get(TYPE).opposite());  // Forge: Fixed MC-134110 Structure mirroring breaking apart double chests
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED);
    }

    @SuppressWarnings("deprecation")
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }
}
