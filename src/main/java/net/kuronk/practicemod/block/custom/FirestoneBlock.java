package net.kuronk.practicemod.block.custom;

import net.kuronk.practicemod.item.custom.Firestone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class FirestoneBlock extends Block {
    public FirestoneBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    /**
     * Method to detect the player hitting a block
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {

        if(!worldIn.isRemote()){
            System.out.println("I hit a Firestone Block");
        }

        super.onBlockClicked(state, worldIn, pos, player);
    }

    /**
     * Method to detect the player right-clicking a block
     */
    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
                                             Hand handIn, BlockRayTraceResult hit) {

        if(!worldIn.isRemote()){
            if(handIn == handIn.MAIN_HAND){
                System.out.println("I right-clicked a Firestone Block with my main hand");
            }
            if(handIn == handIn.OFF_HAND){
                System.out.println("I right-clicked a Firestone Block with my off hand");
            }
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    /**
     * Method to light an entity on fire when it walks on the block
     */
    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {

        Firestone.lightEntityOnFire(entityIn, 5);

        super.onEntityWalk(worldIn, pos, entityIn);
    }
}
