package com.kentdar.modgen.data;

import com.kentdar.modgen.ModGen;
import com.kentdar.modgen.block.ModBlocks;
import com.kentdar.modgen.item.ModItems;
import net.minecraft.data.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    public ModRecipeProvider(DataGenerator generatorIn)
    {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.COPPER_BLOCK.get())
                .key('C', ModItems.COPPER_INGOT.get())
                .patternLine("CCC")
                .patternLine("CCC")
                .patternLine("CCC")
                .addCriterion("copper_ingot", hasItem(ModItems.COPPER_INGOT.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.COPPER_BLOCK.get())
                .key('C', ModItems.COPPER_INGOT.get())
                .key('W', ModItems.COPPER_WIRE.get())
                .patternLine("CCC")
                .patternLine("WWW")
                .patternLine("CCC")
                .addCriterion("copper_ingot", hasItem(ModItems.COPPER_INGOT.get()))
                .build(consumer, new ResourceLocation(ModGen.MOD_ID, "copper_block_alt1"));

    }
}
