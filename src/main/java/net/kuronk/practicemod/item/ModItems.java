package net.kuronk.practicemod.item;

import net.kuronk.practicemod.PracticeMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PracticeMod.MOD_ID);

    //Creating the item amethyst
    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst",
            () -> new Item(new Item.Properties().group(ModItemGroup.PRACTICE_GROUP)));

    public static final RegistryObject<Item> EXCALIBUR = ITEMS.register("excalibur",
            () -> new Item(new Item.Properties().maxDamage(1000).group(ModItemGroup.PRACTICE_GROUP)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
