package com.kentdar.modgen.events;

import com.kentdar.modgen.ModGen;
import com.kentdar.modgen.command.ReturnHomeCommand;
import com.kentdar.modgen.command.SetHomeCommand;
import com.kentdar.modgen.item.ModItems;
import com.kentdar.modgen.util.Config;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.server.command.ConfigCommand;
import org.apache.logging.log4j.LogManager;

import java.util.Collection;

public class ModEvents {

    @SubscribeEvent
    public void onCopperedSheep(AttackEntityEvent event){

        //Se pregunta si en la mano hay una manzana de cobre
        if(event.getPlayer().getHeldItemMainhand().getItem() == ModItems.COPPERED_APPLE.get()){
            //Pregunta si la entidad esta viva
            if(event.getTarget().isAlive()){
                LivingEntity target = (LivingEntity)event.getTarget();
                //Pregunta si lo que se golpio fue una obeja
                if(target instanceof SheepEntity){
                    PlayerEntity player = event.getPlayer();

                    //"Borre" una de las manzanas del inventario
                    player.getHeldItemMainhand().shrink(1);

                    target.addPotionEffect(new EffectInstance(Effects.GLOWING, Config.COPPERED_GLOW_DURATION.get(), 2));

                    //Pregunta si esta en remoto, osea en un servidor o algo asi
                    if(!player.world.isRemote()){
                        String msg = TextFormatting.YELLOW + "Obeja tiene ahora Glowing";
                        player.sendMessage(new StringTextComponent(msg), player.getUniqueID());
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void onCommandsRegister(RegisterCommandsEvent event){
        new SetHomeCommand(event.getDispatcher());
        new ReturnHomeCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
    @SubscribeEvent
    public void onCopperedSheepDrops(LivingDropsEvent event){
        LivingEntity entity = event.getEntityLiving();

        if(entity instanceof SheepEntity){
            World world = entity.getEntityWorld();
            Collection<ItemEntity> drops = event.getDrops();

            LogManager.getLogger().debug(entity.getActivePotionEffects());

            if(entity.isPotionActive(Effects.GLOWING)){
                drops.add(new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(),
                        new ItemStack(ModItems.COPPER_WIRE.get())));
            }
        }
    }
}
