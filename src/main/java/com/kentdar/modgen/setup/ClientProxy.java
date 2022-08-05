package com.kentdar.modgen.setup;

import com.kentdar.modgen.ModGen;
import com.kentdar.modgen.block.ModBlocks;
import com.kentdar.modgen.container.ElectrifierContainer;
import com.kentdar.modgen.container.ModContainers;
import com.kentdar.modgen.screens.ElectrifierScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModGen.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy implements IProxy{
    @Override
    public void init() {
        RenderTypeLookup.setRenderLayer(ModBlocks.ZUCCHINI_CROP.get(), RenderType.getCutout());

        ScreenManager.registerFactory(ModContainers.ELECTRIFIER_CONTAINER.get(), ElectrifierScreen::new);

    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }
}
