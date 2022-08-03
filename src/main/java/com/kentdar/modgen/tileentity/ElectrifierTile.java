package com.kentdar.modgen.tileentity;

import com.kentdar.modgen.block.Electrifier;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ElectrifierTile extends TileEntity {

    private final ItemStackHandler itemHandler = createHandler();

    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private ItemStackHandler createHandler(){
        return new ItemStackHandler(1){

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    public ElectrifierTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public ElectrifierTile(){
        this(ModTileEntities.ELECTRIFIER_TILE_ENTITY.get());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return handler.cast();

        return super.getCapability(cap, side);

    }

    @Override
    public void read(BlockState state, CompoundNBT tag){
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        super.read(state, tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag){
        tag.put("inv", itemHandler.serializeNBT());
        return super.write(tag);
    }

}
