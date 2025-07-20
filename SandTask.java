package com.example.sandplacer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class SandTask {
    private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        try {
            if (mc.thePlayer == null || mc.theWorld == null) return;

            EntityPlayerSP player = mc.thePlayer;

            // Sprint & walk
            player.setSprinting(true);
            mc.gameSettings.keyBindForward.pressed = true;

            BlockPos front = player.getPosition().offset(player.getHorizontalFacing());
            BlockPos top = findTopBlock(front);

            if (!isFullStack(top)) {
                int sandSlot = findSandInHotbar();
                if (sandSlot != -1) {
                    mc.thePlayer.inventory.currentItem = sandSlot;
                    mc.playerController.onPlayerRightClick(mc.thePlayer, mc.theWorld, mc.thePlayer.getHeldItem(),
                            top.up(), EnumFacing.UP, player.getLookVec());
                } else {
                    DiscordNotifier.send("❌ No sand in hotbar!");
                }
            }
        } catch (Exception e) {
            DiscordNotifier.send("❌ Error in sand placer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private BlockPos findTopBlock(BlockPos pos) {
        for (int y = pos.getY(); y < 255; y++) {
            if (mc.theWorld.isAirBlock(new BlockPos(pos.getX(), y, pos.getZ()))) {
                return new BlockPos(pos.getX(), y - 1, pos.getZ());
            }
        }
        return pos;
    }

    private boolean isFullStack(BlockPos pos) {
        return mc.theWorld.getBlockState(pos).getBlock() == Blocks.sand && pos.getY() >= 254;
    }

    private int findSandInHotbar() {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.thePlayer.inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() instanceof ItemBlock &&
                    ((ItemBlock) stack.getItem()).getBlock() == Blocks.sand) {
                return i;
            }
        }
        return -1;
    }
}
