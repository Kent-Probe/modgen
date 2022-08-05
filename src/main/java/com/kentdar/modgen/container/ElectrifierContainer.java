package com.kentdar.modgen.container;

import com.kentdar.modgen.block.Electrifier;
import com.kentdar.modgen.block.ModBlocks;
import com.kentdar.modgen.tileentity.ElectrifierTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ElectrifierContainer extends Container {

    private TileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;

    public ElectrifierContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player){
        super(ModContainers.ELECTRIFIER_CONTAINER.get(), windowId);
        tileEntity = world.getTileEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
        if (tileEntity != null){
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0, 30, 57));
                addSlot(new SlotItemHandler(h, 1, 134, 15));
                addSlot(new SlotItemHandler(h, 2, 134, 57));
            });
        }

        layoutPlayerInventorySlots(8, 84);

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(),
                tileEntity.getPos()), playerEntity, ModBlocks.ELECTRIFIER.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()){
            ItemStack stack = slot.getStack();
            itemStack = stack.copy();
            if(index == 0){
                if(!this.mergeItemStack(stack, 1, 37, true)) return ItemStack.EMPTY;
                slot.onSlotChange(stack, itemStack);
            }else {
                if(stack.getItem() == Items.DIAMOND){
                    if(!this.mergeItemStack(stack, 0,1, false)) return ItemStack.EMPTY;
                } else if (index < 28) {
                    if (!this.mergeItemStack(stack, 28, 37, false)) return  ItemStack.EMPTY;

                } else if (index < 37 && !this.mergeItemStack(stack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if(stack.isEmpty()) slot.putStack(ItemStack.EMPTY);
            else slot.onSlotChanged();

            if(stack.getCount() == itemStack.getCount()) return ItemStack.EMPTY;

            slot.onTake(playerIn, stack);
        }
        return itemStack;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx){
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int varAmount, int dy){
        for (int i = 0; i < varAmount; i++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow){
        //Player Inventario
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);
        //Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    @OnlyIn(Dist.CLIENT)
    public int getEnergyLevel(){
        return ((ElectrifierTile) this.tileEntity).getEnergyLevel();
    }

}
