package com.kentdar.modgen.item;

import com.kentdar.modgen.ModGen;
import com.kentdar.modgen.block.ModBlocks;
import com.kentdar.modgen.block.ModFluids;
import com.kentdar.modgen.entity.ModEntityTypes;
import com.kentdar.modgen.item.weapon.BluntItem;
import com.kentdar.modgen.util.Registration;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {


    //Eggs Spawn
    public static final RegistryObject<Item> BUFFALO_SPAWNING_EGG =
            Registration.ITEMS.register("buffalo_spawning_egg",
                    () -> new ModSpawnEggItem(ModEntityTypes.BUFFALO,
                            0x451212, 0x000000,
                            new Item.Properties().group(ModGen.COURSE_TAB) ));

    //Item del bloque
    public  static final RegistryObject<Item> COPPER_INGOT =
            Registration.ITEMS.register("copper_ingot",
                    () -> new Item(new Item.Properties().group(ModGen.COURSE_TAB)));

    //Items que no son bloques
    //Item extra utilidad
    public static final RegistryObject<Item> COPPER_WIRE =
            Registration.ITEMS.register("copper_wire",
                    () -> new Item(new Item.Properties().group(ModGen.COURSE_TAB)));
    //comida- manzana
    public static final RegistryObject<Item> COPPERED_APPLE =
            Registration.ITEMS.register("coppered_apple",
                    () -> new CopperedApple());

    //Cultivo
    public static final RegistryObject<Item> ZUCCHINI_SEED =
            Registration.ITEMS.register("zucchini_seed",
                    () -> new BlockItem(ModBlocks.ZUCCHINI_CROP.get(),
                            new Item.Properties().group(ModGen.COURSE_TAB)));

    //Bucket cubo
    public static final RegistryObject<Item> CUM_BUCKET =
            Registration.ITEMS.register("cum_bucket",
                    () -> new BucketItem(ModFluids.CUM_FLUID::get,
                            new Item.Properties().group(ModGen.COURSE_TAB).maxStackSize(1)));

    //Mineral del cobre
    public static final RegistryObject<Item> COPPER_RAW =
            Registration.ITEMS.register("copper_raw",
                    () -> new Item(new Item.Properties().group(ModGen.COURSE_TAB)));

    /* Herramientas */
    //Pala
    public static final RegistryObject<Item> COPPER_SHOVEL =
            Registration.ITEMS.register("copper_shovel",
                    () -> new ShovelItem(ModItemTier.COPPER, 0, 0f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .addToolType(ToolType.SHOVEL, 2).group(ModGen.COURSE_TAB)));
    //Espada
    public static final RegistryObject<Item> COPPER_SWORD =
            Registration.ITEMS.register("copper_sword",
                    () -> new SwordItem(ModItemTier.COPPER, 4, 1f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .group(ModGen.COURSE_TAB)));

    public static final RegistryObject<Item> COPPER_CLUB =
            Registration.ITEMS.register("copper_club",
                    () -> new CopperClub(ModItemTier.COPPER, 4, 1f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .group(ModGen.COURSE_TAB)));

    //Pico
    public static final RegistryObject<Item> COPPER_PICKAXE =
            Registration.ITEMS.register("copper_pickaxe",
                    () -> new PickaxeItem(ModItemTier.COPPER, 1, 0f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .addToolType(ToolType.PICKAXE, 2).group(ModGen.COURSE_TAB)));
    //Hacha
    public static final RegistryObject<Item> COPPER_AXE =
            Registration.ITEMS.register("copper_axe",
                    () -> new AxeItem(ModItemTier.COPPER, 2.5f, 0f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .addToolType(ToolType.AXE, 2).group(ModGen.COURSE_TAB)));
    //Azada
    public static final RegistryObject<Item> COPPER_HOE =
            Registration.ITEMS.register("copper_hoe",
                    () -> new HoeItem(ModItemTier.COPPER, 0, 0f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .addToolType(ToolType.HOE, 2).group(ModGen.COURSE_TAB)));

    public static void register() { }


    /* Armaduras */

    public static final RegistryObject<Item> COPPER_HELMET =
            Registration.ITEMS.register("copper_helmet",
                    () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.HEAD,
                            new Item.Properties().group(ModGen.COURSE_TAB)));

    public static final RegistryObject<Item> COPPER_CHESTPLATE =
            Registration.ITEMS.register("copper_chestplate",
                    () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.CHEST,
                            new Item.Properties().group(ModGen.COURSE_TAB)));

    public static final RegistryObject<Item> COPPER_LEGGINGS =
            Registration.ITEMS.register("copper_leggings",
                    () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.LEGS,
                            new Item.Properties().group(ModGen.COURSE_TAB)));

    public static final RegistryObject<Item> COPPER_BOOTS =
            Registration.ITEMS.register("copper_boots",
                    () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.FEET,
                            new Item.Properties().group(ModGen.COURSE_TAB)));

    public enum ModArmorMaterial implements IArmorMaterial{
        COPPER(50,10, new int[] { 2, 7, 5, 4}, SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                Ingredient.fromStacks(new ItemStack(ModItems.COPPER_INGOT.get())),
                ModGen.MOD_ID + ":copper", 0f,0.1f );

        ModArmorMaterial(int durability, int enchantability, int[] damageReductionAmountArray, SoundEvent soundEvent, Ingredient repairMaterial, String name, float toughness, float knockbackResistence) {
            this.durability = durability;
            this.enchantability = enchantability;
            this.damageReductionAmountArray = damageReductionAmountArray;
            this.soundEvent = soundEvent;
            this.repairMaterial = repairMaterial;
            this.name = name;
            this.toughness = toughness;
            this.knockbackResistence = knockbackResistence;
        }

        private final int durability;
        private final int enchantability;
        private final int[] damageReductionAmountArray;
        private final SoundEvent soundEvent;
        private final Ingredient repairMaterial;
        private final String name;
        private final float toughness;
        private final float knockbackResistence;


        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return durability;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return damageReductionAmountArray[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return soundEvent;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return repairMaterial;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public float getToughness() {
            return toughness;
        }

        @Override
        public float getKnockbackResistance() {
            return knockbackResistence;
        }
    }


    /* Herramientas */
    public enum ModItemTier implements IItemTier{
        COPPER(2, 150, 2.5f, 0.5f, 15,
                Ingredient.fromStacks(new ItemStack(ModItems.COPPER_INGOT.get())));

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final Ingredient repairMaterial;

        ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage,
                    int enchantability, Ingredient repairMaterial) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = repairMaterial;
        }


        @Override
        public int getMaxUses() {
            return maxUses;
        }

        @Override
        public float getEfficiency() {
            return efficiency;
        }

        @Override
        public float getAttackDamage() {
            return attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return repairMaterial;
        }
    }
}
