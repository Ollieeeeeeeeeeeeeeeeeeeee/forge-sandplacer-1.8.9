package com.example.sandplacer;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = SandPlacerMod.MODID, version = SandPlacerMod.VERSION)
public class SandPlacerMod {
    public static final String MODID = "sandplacer";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new SandTask());
    }
}

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = SandPlacerMod.MODID, version = SandPlacerMod.VERSION)
public class SandPlacerMod {
    ...

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandSandPlacer());
    }
}
