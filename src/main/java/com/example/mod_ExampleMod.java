package com.example;

import com.example.item.ModifiedItem;
import com.example.item.creation.ItemTypeAG;
import com.example.item.creation.MaterialAG;
import com.example.override.*;
import com.example.player.KeyBinds;
import com.example.proxy.ServerProxy;
import com.example.tick.ClientTickHandler;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraftforge.common.MinecraftForge;
import com.example.block.ModifiedBlock;
import com.example.block.ModBlockList;
import com.example.item.ModItemClickEvents;
import com.example.item.ModItemList;
import com.example.proxy.CommonProxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.logging.Logger;

public class mod_ExampleMod extends BaseMod {
    public static Logger LOGGER = Logger.getLogger("ExampleMod");

    @Override
    public String getVersion() {
        return null;
    }

    public static mod_ExampleMod instance;


    @Override
    public void load() {
        instance = this;
        LOGGER.info("Hello World");
        System.out.println("[themod] loading!");
        doNetworking();
        doItems();
		doBlocks();
        doRecipes();
        doEntities();
		doBiomes();
		doListeners();
        GuiOverride.inject(Minecraft.getMinecraft());
        KeyBinds.inject();
        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
        MinecraftForge.EVENT_BUS.register(new Listeners());
        proxy.registerRenderers();

    }

    private void doNetworking()
    {
        NetworkRegistry.instance().registerGuiHandler(this, (IGuiHandler) proxy);

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
        serverSide = "com.example.proxy.ServerProxy"
    )
    public static CommonProxy proxy;

    boolean firstTick = true;


}
