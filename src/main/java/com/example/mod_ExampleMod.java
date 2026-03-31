package com.example;

import com.example.override.ItemOverride;
import com.example.player.KeyBinds;
import com.example.tick.ClientTickHandler;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.registry.TickRegistry;
import net.minecraft.src.BaseMod;
import net.minecraft.src.Block;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.MinecraftForge;
import com.example.block.ModifiedBlock;
import com.example.block.ModBlockList;
import com.example.item.ModItemClickEvents;
import com.example.item.ModItemList;
import com.example.override.BiomeOverride;
import com.example.override.BlockOverride;
import com.example.override.MobDropOverride;
import com.example.proxy.CommonProxy;

import java.util.logging.Logger;

public class mod_ExampleMod extends BaseMod {
    public static Logger LOGGER = Logger.getLogger("ExampleMod");

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public void load() {

        LOGGER.info("Hello World");
        System.out.println("[themod] loading!");
        doBlocks();
        doItems();
        doEntities();
        doBiomes();
        doListeners();
        KeyBinds.inject();
        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);

        proxy.registerRenderers();

    }

    public void doBiomes()
    {
        BiomeOverride.inject();
    }

    public void doEntities()
    {
        MobDropOverride.inject();
    }

    public boolean doBlocks()
    {
        for(ModifiedBlock bu : ModBlockList.get())
        {
            Block b = (Block)bu;
            ModLoader.registerBlock(b);
            ModLoader.addName(b, b.getBlockName().substring(5));
            System.out.println("loaded block with name: "+b.getBlockName()+" and id: "+b.blockID);
        }

        BlockOverride.inject();

        return true;
    }
    public boolean doItems()
    {
        ModItemList.generate();
        ItemOverride.inject();
        return true;
    }

    public boolean doListeners()
    {
        MinecraftForge.EVENT_BUS.register(new ModItemClickEvents());
        return true;
    }

    @SidedProxy(
        clientSide = "com.example.proxy.ClientProxy",
        serverSide = "com.example.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    boolean firstTick = true;


}
