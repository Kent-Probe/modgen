package com.kentdar.modgen.enchantment;

import com.kentdar.modgen.item.weapon.BluntItem;
import com.kentdar.modgen.util.Registration;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;

public class ModEnchantments {

    public static final RegistryObject<Enchantment> BLUNTNESS
            = Registration.ENCHANTMENT.register("bluntness",
            () -> new BluntEnchantment(Enchantment.Rarity.COMMON, ModEnchantments.BLUNTNESS_TYPE, 0, EquipmentSlotType.MAINHAND));

    public static final EnchantmentType BLUNTNESS_TYPE
            = EnchantmentType.create("modgen:bluntness", (item) -> item instanceof BluntItem);

    public static void register(){ }
}
