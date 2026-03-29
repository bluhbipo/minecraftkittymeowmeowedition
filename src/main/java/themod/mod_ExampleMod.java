package themod;

import cpw.mods.fml.common.SidedProxy;
import net.minecraft.src.BaseMod;
import net.minecraft.src.Block;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.MinecraftForge;
import themod.block.ModifiedBlock;
import themod.block.ModBlockList;
import themod.item.ModItemClickEvents;
import themod.item.ModItemList;
import themod.override.BiomeOverride;
import themod.override.BlockOverride;
import themod.override.MobDropOverride;
import themod.proxy.CommonProxy;

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
        System.out.println("gay slutty sloppy furry faggot pawgocks");
        doBlocks();
        doItems();
        doEntities();
        doBiomes();
        doListeners();

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
        return true;
    }

    public boolean doListeners()
    {
        MinecraftForge.EVENT_BUS.register(new ModItemClickEvents());
        return true;
    }

    @SidedProxy(
        clientSide = "themod.proxy.ClientProxy",
        serverSide = "themod.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    boolean firstTick = true;


}
