package com.kentdar.modgen.screens;

import com.kentdar.modgen.ModGen;
import com.kentdar.modgen.container.BigChestContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class BigChestScreen extends ContainerScreen<BigChestContainer> {

    private final ResourceLocation GUI = new ResourceLocation(ModGen.MOD_ID,"textures/gui/bigchest_gui.png");

    public BigChestScreen(BigChestContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 212;
        this.ySize = 221;

        // positioning of the Text
        this.titleX = 8;
        this.titleY = 4;
        this.playerInventoryTitleX = 8;
        this.playerInventoryTitleY = this.ySize - 98;


    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }


    @SuppressWarnings("deprecation")
    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
    }
}
