package net.kuronk.practicemod.item.custom;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class Firestone extends Item {
    public Firestone(Properties properties) {
        super(properties);
    }

    /**
     * Override of the function that will allow the item to do something when used.
     */
    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {

        // Creating a new world-class to get information of the world.
        World world = context.getWorld();

        // If the world is remote we execute the code.
        if(!world.isRemote){
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            BlockState clickedBlock = world.getBlockState(context.getPos());

            rightClickOnCertainBlockState(clickedBlock, context, playerEntity);

            // The item is going to damage by 1 when used.
            stack.damageItem(1,playerEntity, player -> player.sendBreakAnimation(context.getHand()));
        }

        return super.onItemUseFirst(stack, context);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()){
            tooltip.add(new TranslationTextComponent("tooltip.practicemod.firestone_shift"));
        }else{
            tooltip.add(new TranslationTextComponent("tooltip.practicemod.firestone"));
        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    /**
     * Here's where the logic for the item goes.
     */
    private void rightClickOnCertainBlockState(BlockState clickedBlock, ItemUseContext context,
                                               PlayerEntity playerEntity) {

        boolean playerIsNotOnFire = !playerEntity.isBurning();

        if(random.nextFloat()> 0.6f){
            //Light player on fire
            lightEntityOnFire(playerEntity, 10);
        }else if(playerIsNotOnFire && blockIsValidForResistance(clickedBlock)){
            //Gain fire resistance and destroy the block
            gainFireResistanceAndDestroyBlock(playerEntity, context.getWorld(), context.getPos());
        }else{
            //Light the ground on fire
            lightGroundOnFire(context);
        }

    }

    /**
     * Method to check if the block is valid for resistance or not
     */
    private boolean blockIsValidForResistance(BlockState clickedBlock) {
        return clickedBlock.getBlock() == Blocks.OBSIDIAN;
    }

    /**
     * Method to light the entity on fire
     */
    public static void lightEntityOnFire(Entity entity, int seconds){
        entity.setFire(seconds);
    }

    /**
     * Method to gain fire resistance and destroy block
     */
    private void gainFireResistanceAndDestroyBlock(PlayerEntity playerEntity, World world, BlockPos pos){
        gainFireResistance(playerEntity);
        world.destroyBlock(pos, false);
    }

    /**
     * Method to gain fire resistance
     */
    public static void gainFireResistance(PlayerEntity playerEntity){
        playerEntity.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200));
    }

    /**
     * Method to light the ground on fire
     */
    public static void lightGroundOnFire(ItemUseContext context){
        PlayerEntity playerentity = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockpos = context.getPos().offset(context.getFace());

        //if the block can be light then
        if(AbstractFireBlock.canLightBlock(world, blockpos, context.getPlacementHorizontalFacing())){
            // play the FireCharge sound
            world.playSound(playerentity, blockpos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0f,
                    random.nextFloat() * 0.4f + 0.8f);

            //place fire on block
            BlockState blockstate = AbstractFireBlock.getFireForPlacement(world, blockpos);
            world.setBlockState(blockpos,blockstate,11);
        }
    }
}
