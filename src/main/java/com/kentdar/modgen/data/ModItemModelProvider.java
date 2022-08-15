package com.kentdar.modgen.data;

import com.kentdar.modgen.ModGen;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator,  ExistingFileHelper existingFileHelper) {
        super(generator, ModGen.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Bblocks
        withExistingParent("copper_block", modLoc("block/copper_block"));
        withExistingParent("copper_ore", modLoc("block/copper_ore"));
        withExistingParent("copper_button", modLoc("block/copper_block"));
        withExistingParent("copper_button_inventory", modLoc("block/copper_block"));
        withExistingParent("copper_button_pressed", modLoc("block/copper_block"));
        withExistingParent("copper_fence_gate", modLoc("block/copper_block"));
        withExistingParent("copper_fence_gate_open", modLoc("block/copper_block"));
        withExistingParent("copper_fence_gate_wall", modLoc("block/copper_block"));
        withExistingParent("copper_fence_gate_wall_open", modLoc("block/copper_block"));

        //Items
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        builder(itemGenerated, "copper_ingot");

        
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name){
        return (getBuilder(name)).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
