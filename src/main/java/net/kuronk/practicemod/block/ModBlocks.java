package net.kuronk.practicemod.block;

import net.kuronk.practicemod.PracticeMod;
import net.kuronk.practicemod.block.custom.FirestoneBlock;
import net.kuronk.practicemod.item.ModItemGroup;
import net.kuronk.practicemod.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import sun.java2d.pipe.RegionSpanIterator;

import javax.swing.*;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PracticeMod.MOD_ID);


    public static final RegistryObject<Block> AMETHYST_ORE = registerBlock("amethyst_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).
                    setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(5f)));

    public static final RegistryObject<Block> AMETHYST_BLOCK = registerBlock("amethyst_block",
            ()-> new Block(AbstractBlock.Properties.create(Material.IRON).harvestLevel(2).
                    harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(5f)));

    public static final RegistryObject<Block> FIRESTONE_BLOCK = registerBlock("firestone_block",
            ()-> new FirestoneBlock(AbstractBlock.Properties.create(Material.IRON).harvestLevel(2).
                    harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f)));


    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);

        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(ModItemGroup.PRACTICE_GROUP)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
