package com.kentdar.modgen.container;

import com.kentdar.modgen.tileentity.BigChestTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class BigChestContainer extends Container {

    private final int numCols;
    private final int numRows;
    private TileEntity tileEntity;
    private IItemHandler playerInv;

    public BigChestContainer(int id, World world, BlockPos pos, int numCols, int numRows, PlayerInventory playerInv) {

        super(ModContainers.BIG_CHEST_CONTAINER.get(), id);
        tileEntity = world.getTileEntity(pos);
        this.numCols = numCols;
        this.numRows = numRows;
        this.playerInv = new InvWrapper(playerInv);

        if(tileEntity != null)
        {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {

                GeneratedChestInventorySlots(handler);
                GeneratedPlayerInventorySlots();

            });
        }
    }

    private void GeneratedChestInventorySlots(IItemHandler handler){

        for(int j = 0; j < this.numRows; ++j) {
            for(int k = 0; k < numCols; ++k) {
                this.addSlot(new SlotItemHandler(handler, k + j * this.numCols,
                        8 + k * 18, 14 + j * 18));
            }
        }
    }

    private void GeneratedPlayerInventorySlots(){

        int i = (this.numRows - 4) * 18;

        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new SlotItemHandler(playerInv, j1 + l * 9 + 9,
                        26 + j1 * 18, 103 + l * 18 + i));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new SlotItemHandler(playerInv, i1,
                    26 + i1 * 18, 161 + i));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return ((BigChestTile) this.tileEntity).isUsableByPlayer(playerIn);
    }


    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < this.numRows * this.numCols) {
                if (!this.mergeItemStack(itemstack1, this.numRows * this.numCols, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.numRows * this.numCols, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        ((BigChestTile) tileEntity).closeInventory(playerIn);
    }


    public IInventory getChestInventory() {
        return (BigChestTile) this.tileEntity;
    }

}
