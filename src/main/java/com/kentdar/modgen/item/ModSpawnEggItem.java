package com.kentdar.modgen.item;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModSpawnEggItem extends SpawnEggItem {

    protected static final List<ModSpawnEggItem> MOD_EGGS = new ArrayList<>();
    private final Lazy<? extends EntityType<?>> entityTypeSupplier;


    @SuppressWarnings("deprecation")
    public ModSpawnEggItem(final RegistryObject<? extends EntityType<?>> entitySupplier, int primaryColorIn, int secondaryColorIn, Properties builder) {
        super(null, primaryColorIn, secondaryColorIn, builder);
        entityTypeSupplier = Lazy.of(entitySupplier::get);
        MOD_EGGS.add(this);
    }

    public static void initSpawnEgg(){
        final Map<EntityType<?>, SpawnEggItem> EGGS =
                ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "EGGS");

        DefaultDispenseItemBehavior dispenseItemBehavior = new DefaultDispenseItemBehavior(){
            @Override
            protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().get(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                type.spawn(source.getWorld(), stack, null,
                        source.getBlockPos(), SpawnReason.DISPENSER, direction != Direction.UP, false);
                stack.shrink(1);

                return stack;
            }
        };

        for (SpawnEggItem spwnEgg: ModSpawnEggItem.MOD_EGGS) {

            EGGS.put(spwnEgg.getType(null), spwnEgg);
            DispenserBlock.registerDispenseBehavior(spwnEgg, dispenseItemBehavior);

        }

        ModSpawnEggItem.MOD_EGGS.clear();
    }

    @Override
    public EntityType<?> getType(@Nullable CompoundNBT nbt) {
        return entityTypeSupplier.get();
    }
}
