package de.vepexlegit.fastladder;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = "fastladder", version = "1.0")
public class FastLadder {
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEntityClimbLadder(LivingEvent.LivingUpdateEvent event) {
        if (event.entity instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) event.entity;
            World world = entity.worldObj;
            BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
            if (isLadder(world, pos) && isMovementKeyPressed()) {
                entity.motionY = 0.25;
            }
        }
    }

    private boolean isLadder(World world, BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();
        return block == Blocks.ladder || block == Blocks.vine;
    }

    private boolean isMovementKeyPressed() {
        return Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_D);
    }
}
