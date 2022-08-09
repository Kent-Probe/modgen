package com.kentdar.modgen.entity.render;

import com.kentdar.modgen.ModGen;
import com.kentdar.modgen.entity.BuffaloEntity;
import com.kentdar.modgen.entity.model.BuffaloModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BuffaloRender extends MobRenderer<BuffaloEntity, BuffaloModel<BuffaloEntity>> {

    public BuffaloRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BuffaloModel<>(), 0.9f);
    }

    @Override
    public ResourceLocation getEntityTexture(BuffaloEntity entity) {
        return new ResourceLocation(ModGen.MOD_ID, "textures/entity/buffalo.png");
    }
}
