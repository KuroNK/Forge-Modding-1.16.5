package net.kuronk.practicemod.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup PRACTICE_GROUP = new ItemGroup("practiceModTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.AMETHYST.get()) ;
        }
    };

}
