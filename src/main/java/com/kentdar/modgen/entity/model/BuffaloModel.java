package com.kentdar.modgen.entity.model;

import com.kentdar.modgen.entity.BuffaloEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class BuffaloModel<T extends BuffaloEntity> extends EntityModel<T> {

    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer legFrontRight;
    private final ModelRenderer legFrontLeft;
    private final ModelRenderer legBackRight;
    private final ModelRenderer legBackLeft;

    public BuffaloModel() {
        textureWidth = 71;
        textureHeight = 45;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 3.0F, -4.0F);
        head.setTextureOffset(1, 2).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
        head.setTextureOffset(26, 3).addBox(3.0F, -6.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(26, 2).addBox(-4.0F, -6.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 7.5F, 1.5F);
        body.setTextureOffset(0, 28).addBox(-5.0F, -7.5F, -5.5F, 10.0F, 7.0F, 10.0F, 0.0F, false);
        body.setTextureOffset(30, 10).addBox(-4.0F, -5.5F, -4.5F, 8.0F, 12.0F, 8.0F, 0.0F, false);
        setRotationAngle(body, -1.5708F, 0.0F, 0.0F);

        legFrontRight = new ModelRenderer(this);
        legFrontRight.setRotationPoint(-3.0F, 12.5F, -4.0F);
        legFrontRight.setTextureOffset(63, 32).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 11.0F, 2.0F, 0.0F, false);

        legFrontLeft = new ModelRenderer(this);
        legFrontLeft.setRotationPoint(3.0F, 12.5F, -4.0F);
        legFrontLeft.setTextureOffset(63, 32).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 11.0F, 2.0F, 0.0F, false);

        legBackRight = new ModelRenderer(this);
        legBackRight.setRotationPoint(-3.0F, 12.5F, 7.0F);
        legBackRight.setTextureOffset(63, 32).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 11.0F, 2.0F, 0.0F, false);

        legBackLeft = new ModelRenderer(this);
        legBackLeft.setRotationPoint(3.0F, 12.5F, 7.0F);
        legBackLeft.setTextureOffset(63, 32).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 11.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.legBackRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legBackLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legFrontRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legFrontLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        legFrontRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        legFrontLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        legBackRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        legBackLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
