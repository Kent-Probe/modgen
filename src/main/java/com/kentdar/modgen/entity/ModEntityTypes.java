package com.kentdar.modgen.entity;

import com.kentdar.modgen.ModGen;
import com.kentdar.modgen.util.Registration;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class ModEntityTypes {
    public static final RegistryObject<EntityType<BuffaloEntity>> BUFFALO
            = Registration.ENTITIES.register("buffalo",
                    () -> EntityType.Builder.create(BuffaloEntity::new, EntityClassification.CREATURE)
                            .size(1.5f, 1.5f)
                            .build(new ResourceLocation(ModGen.MOD_ID + "buffalo").toString()));

    public static void register(){ }
}
