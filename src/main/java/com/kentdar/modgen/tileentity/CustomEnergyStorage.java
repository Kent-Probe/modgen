package com.kentdar.modgen.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT> {

    public CustomEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
        this.maxReceive = 0;
    }

    protected void onEnergyChanged(){

    }
    public void generatePower(int energy){
        this.energy += energy;
        if(this.energy > getMaxEnergyStored()) this.energy = getEnergyStored();
        onEnergyChanged();
    }
    public void consumePower(int energy){
        this.energy -= energy;
        if(this.energy < 0) this.energy = 0;
        onEnergyChanged();
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return super.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return super.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return super.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return super.canExtract();
    }

    @Override
    public boolean canReceive() {
        return super.canReceive();
    }

    public boolean isFullEnergy(){
        return getEnergyStored() >= getMaxEnergyStored();
    }

    public void setEnergy(int energy){
        this.energy = energy;
        onEnergyChanged();
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("energy", getEnergyStored());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        setEnergy(nbt.getInt("energy"));
    }


}
