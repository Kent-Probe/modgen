package com.kentdar.modgen;

import com.kentdar.modgen.block.ModBlocks;
import com.kentdar.modgen.block.ModFluids;
import com.kentdar.modgen.container.ModContainers;
import com.kentdar.modgen.enchantment.ModEnchantments;
import com.kentdar.modgen.entity.BuffaloEntity;
import com.kentdar.modgen.entity.ModEntityTypes;
import com.kentdar.modgen.entity.render.BuffaloRender;
import com.kentdar.modgen.events.ModEvents;
import com.kentdar.modgen.item.ModItems;
import com.kentdar.modgen.setup.ClientProxy;
import com.kentdar.modgen.setup.IProxy;
import com.kentdar.modgen.setup.ServerProxy;
import com.kentdar.modgen.sound.ModSoundsEvents;
import com.kentdar.modgen.tileentity.ModTileEntities;
import com.kentdar.modgen.util.Config;
import com.kentdar.modgen.util.Registration;
import com.kentdar.modgen.world.biome.ModBiomes;
import com.kentdar.modgen.world.biome.ModSurfaceBuilders;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModGen.MOD_ID)
public class ModGen
{
    public static final String MOD_ID = "modgen";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static IProxy proxy;

    public static final ItemGroup COURSE_TAB = new ItemGroup("coursetab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.COPPER_INGOT.get());
        }
    };

    public ModGen() {

        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        //Registra las adiciones
        registerModAddition();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SuppressWarnings("deprecation")
    private void setup(final FMLCommonSetupEvent event)
    {
        proxy.init();

        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntityTypes.BUFFALO.get(), BuffaloEntity.setCustomAttributes().create());
        });

        registerConfig();
        loadConfigs();
    }

    private void registerConfig(){
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
    }
    private void loadConfigs(){
        Config.loadConfigFile(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("modgen-client.toml").toString());
        Config.loadConfigFile(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("modgen-server.toml").toString());
    }
    private void registerModAddition(){
        //Inicia los registros de nuestras adiciones
        //Init the registration of our additions
        Registration.init();

        //register Items, Blocks, etc...
        //Regstra Items, Bloques, etc...
        ModItems.register();
        ModBlocks.register();
        ModFluids.register();

        //Register mod EVENTS
        //Registro los eventos
        MinecraftForge.EVENT_BUS.register(new ModEvents());

        //Biomes
        ModSurfaceBuilders.register();
        ModBiomes.register();

        //Enchantment
        ModEnchantments.register();

        //Entidades
        ModTileEntities.register();

        //GUI
        ModContainers.register();

        //Mobs
        ModEntityTypes.register();

        //Sonidos
        ModSoundsEvents.register();
    }



    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
