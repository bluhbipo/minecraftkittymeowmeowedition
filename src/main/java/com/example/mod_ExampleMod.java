package com.example;

import com.example.item.ModifiedItem;
import com.example.item.creation.ItemTypeAG;
import com.example.item.creation.MaterialAG;
import com.example.override.ItemOverride;
import com.example.player.KeyBinds;
import com.example.tick.ClientTickHandler;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import net.minecraft.src.*;
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
        doRecipes();
        doEntities();
        doBiomes();
        doListeners();
        KeyBinds.inject();
        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
        MinecraftForge.EVENT_BUS.register(new Listeners());
        proxy.registerRenderers();

    }

    private void doRecipes()
    {
        System.out.println("doing recipes:");
        for(MaterialAG m : MaterialAG.values())
        {
            for(ItemTypeAG i : ItemTypeAG.values())
            {
                ModifiedItem item = MaterialAG.modItemMap.get(m,i);
                if(item == null) continue;
                String[] pattern;
                switch (i)
                {
                    case LEGGINGS:
                        pattern = ItemTypeAG.leggingsPattern;
                        break;
                    case CHESTPLATE:
                        pattern = ItemTypeAG.chestplatePattern;
                        break;
                    case PICKAXE:
                        pattern = ItemTypeAG.pickaxePattern;
                        break;
                    case SHOVEL:
                        pattern = ItemTypeAG.shovelPattern;
                        break;
                    case SWORD:
                        pattern = ItemTypeAG.swordPattern;
                        break;
                    case HOE:
                        pattern = ItemTypeAG.hoePattern1;
                        registerAutogen((Item)item, m, ItemTypeAG.hoePattern2);
                        break;
                    case AXE:
                        pattern = ItemTypeAG. axePattern1;
                        registerAutogen((Item)item, m, ItemTypeAG.axePattern2);
                        break;
                    case BOOTS:
                        pattern = ItemTypeAG.bootsPattern;
                        break;
                    case HELMET:
                        pattern = ItemTypeAG.helmetPattern;
                        break;
                    case INGOT:
                    default: pattern = null;

                }
                if(pattern == null) continue;
                registerAutogen((Item)item, m, pattern);

            }
            if(m.getBlock() == null) continue;
            ModifiedItem ingot = MaterialAG.modItemMap.get(m, ItemTypeAG.INGOT);
            if (ingot == null) continue;
            GameRegistry.addRecipe(
                new ItemStack(m.getBlock()),
                "mmm",
                "mmm",
                "mmm",
                'm', ingot
            );
            GameRegistry.addRecipe(
                new ItemStack((Item)ingot,9),
                "b",
                'b', m.getBlock()
            );
            System.out.println("registered recipe: "+m.getBlock().getBlockName());
        }
        System.out.println("finished recipes:");
    }
    private void registerAutogen(Item i, MaterialAG m, String[] pattern)
    {
        ModifiedItem ingot = MaterialAG.modItemMap.get(m, ItemTypeAG.INGOT);
        if (ingot == null) return;
        if(pattern.length == 2)
        {
            GameRegistry.addRecipe(
                new ItemStack(i),
                pattern[0],
                pattern[1],
                'm', ingot,
                's', m.toolStick
            );
        }
        if(pattern.length == 3)
        {
            GameRegistry.addRecipe(
                new ItemStack(i),
                pattern[0],
                pattern[1],
                pattern[2],
                'm', ingot,
                's', m.toolStick
            );
        }
        System.out.println("registered recipe: "+i.getItemName());

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
