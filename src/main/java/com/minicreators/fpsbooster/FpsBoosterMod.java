package com.minicreators.fpsbooster;

import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.commands.Commands.literal;

@Mod(FpsBoosterMod.MOD_ID)
public class FpsBoosterMod {
    public static final String MOD_ID = "fpsbooster";

    private static boolean enabled = true;
    private static int ticks;

    public FpsBoosterMod() {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase != TickEvent.Phase.END || !enabled) {
                return;
            }

            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player == null || minecraft.level == null) {
                return;
            }

            ticks++;
            if (ticks < 20) {
                return;
            }
            ticks = 0;

            var options = minecraft.options;

            options.renderDistance().set(8);
            options.simulationDistance().set(5);
            options.entityDistanceScaling().set(0.75D);
            options.particles().set(ParticleStatus.MINIMAL);
            options.entityShadows().set(false);
            options.mipmapLevels().set(0);
            options.save();
        }

        @SubscribeEvent
        public static void onRegisterCommands(RegisterClientCommandsEvent event) {
            event.getDispatcher().register(literal("fpsbooster")
                    .then(literal("on").executes(context -> {
                        enabled = true;
                        context.getSource().sendSystemMessage(net.minecraft.network.chat.Component.literal("FPS Booster: ON"));
                        return 1;
                    }))
                    .then(literal("off").executes(context -> {
                        enabled = false;
                        context.getSource().sendSystemMessage(net.minecraft.network.chat.Component.literal("FPS Booster: OFF"));
                        return 1;
                    }))
                    .then(literal("status").executes(context -> {
                        context.getSource().sendSystemMessage(net.minecraft.network.chat.Component.literal("FPS Booster: " + (enabled ? "ON" : "OFF")));
                        return 1;
                    }))
            );
        }
    }
}
