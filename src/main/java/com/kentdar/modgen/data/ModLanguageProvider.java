package com.kentdar.modgen.data;

import com.kentdar.modgen.ModGen;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider
{
    public ModLanguageProvider(DataGenerator gen, String locale)
    {
        super(gen, ModGen.MOD_ID, locale);
    }

    @Override
    protected void addTranslations()
    {
        String locale = this.getName().replace("Languages: ", "");

        switch (locale)
        {
            case "en_us":
                add("advancements.story.copper_block.title", "Coppering");
                add("advancements.story.copper_block.description", "Acquire Copper Blocks");

                //Blocks
                add("block.modgen.copper_stairs", "Copper Stairs");
                add("block.modgen.copper_fence", "Copper Fence");
                add("block.modgen.copper_fence_gate", "Copper Fence Gate");
                add("block.modgen.copper_pressure_plate", "Copper Pressure Plate");
                add("block.modgen.copper_slab", "Copper Slab");
                add("block.modgen.copper_button", "Copper Button");
                add("block.modgen.copper_ore", "Copper Ore");
                add("block.modgen.electrifier", "Electrifier");
                add("block.modgen.big_chest", "Big Chest");
                add("block.modgen.copper_block","Copper Block");

                //Items
                add("item.modgen.copper_ingot", "Copper Ingot");
                add("item.modgen.copper_wire", "Copper Wire");
                add("item.modgen.coppered_apple", "Coppered Apple");
                add("item.modgen.copper_raw", "Copper Raw");
                add("item.modgen.cum_bucket", "Cum Bucket");

                //Tools
                add("item.modgen.copper_shovel", "Copper Shovel");
                add("item.modgen.copper_sword", "Copper Sword");
                add("item.modgen.copper_hoe", "Copper Hoe");
                add("item.modgen.copper_axe", "Copper Axe");
                add("item.modgen.copper_pickaxe", "Copper Pickaxe");

                //Armor
                add("item.modgen.copper_helmet", "Copper Helmet");
                add("item.modgen.copper_chestplate", "Copper Chestplate");
                add("item.modgen.copper_boots", "Copper Boots");
                add("item.modgen.copper_leggings", "Copper Leggings");

                //Seeds
                add("block.modgen.zucchini_crop", "Zucchini Seed");

                //Planks
                add("block.modgen.redwood_planks", "Redwood Planks");
                add("block.modgen.redwood_log", "Redwood Log");
                add("block.modgen.redwood_leaves", "Redwood Leaves");
                add("block.modgen.redwood_sapling", "Redwood Sapling");

                //Entity Mobs
                add("entity.modgen.buffalo", "Buffalo");
                add("item.modgen.buffalo_spawning_egg", "Buffalo Spawn Egg");

                //Enchants
                add("item.modgen.copper_club", "Copper CLub");
                add("enchantment.modgen.bluntness", "Bluntness");

                //Screen
                add("screen.modgen.bigchest", "Big Chest");

                //Name of Course Tab
                add("itemGroup.coursetab", "Mod next Generation");
                break;
            default:
                break;
        }
    }
}
