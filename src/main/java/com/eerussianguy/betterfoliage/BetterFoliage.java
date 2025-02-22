package com.eerussianguy.betterfoliage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.eerussianguy.betterfoliage.BetterFoliage.MOD_ID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MOD_ID)
public class BetterFoliage
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "betterfoliage";

    public BetterFoliage()
    {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> "Nothing", (remote, isServer) -> true));
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        BFConfig.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        if (BFConfig.CLIENT.forceForgeLighting.get() && !ModList.get().isLoaded("optifine"))
        {
            ForgeConfig.CLIENT.experimentalForgeLightPipelineEnabled.set(true);
        }
    }
}
