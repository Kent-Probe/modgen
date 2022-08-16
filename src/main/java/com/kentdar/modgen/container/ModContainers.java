package com.kentdar.modgen.container;

import com.kentdar.modgen.util.Registration;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainers {
    public static final RegistryObject<ContainerType<ElectrifierContainer>> ELECTRIFIER_CONTAINER
            = Registration.CONTAINER.register("electrifier_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new ElectrifierContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<ContainerType<BigChestContainer>> BIG_CHEST_CONTAINER
            = Registration.CONTAINER.register("big_chest_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new BigChestContainer(windowId, world, pos, 11, 6, inv);
            })));
    public static void register(){}
}
