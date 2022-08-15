package com.kentdar.modgen.data;

import com.kentdar.modgen.ModGen;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider,  @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, ModGen.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {


        super.registerTags();
    }
}
