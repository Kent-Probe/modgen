package com.kentdar.modgen.sound;

import com.kentdar.modgen.ModGen;
import com.kentdar.modgen.util.Registration;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;

public class ModSoundsEvents {

    public static final RegistryObject<SoundEvent> SMALL_EXPLOSION
            = Registration.SOUND_EVENT.register("small_explosion",
            () -> new SoundEvent(new ResourceLocation(ModGen.MOD_ID, "small_explosion")));


    public static void register(){ }
}
