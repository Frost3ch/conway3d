package net.frosty.conway3d;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.frosty.conway3d.block.ModBlocks;
import net.frosty.conway3d.command.ResetConwayBlocksCommand;
import net.frosty.conway3d.gui.controllerConfigScreen;
import net.frosty.conway3d.gui.controllerGui;
import net.frosty.conway3d.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class Conway3D implements ModInitializer {
	public static final String MOD_ID = "conway3d";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private boolean wasLeftClick = false;

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ClientTickEvents.END_CLIENT_TICK.register(client ->{
			if (client.player != null){
				boolean isLeftClick = MinecraftClient.getInstance().options.attackKey.isPressed();
				if (isLeftClick && !wasLeftClick){
					if (client.player.getMainHandStack().getItem() == ModItems.GAME_CONTROLLER){
						client.player.sendMessage(Text.of("Opening GUI..."));
						MinecraftClient.getInstance().setScreen(new controllerConfigScreen(new controllerGui()));
					}
				}
			}
		});

		CommandRegistrationCallback.EVENT.register(ResetConwayBlocksCommand::register);

	}
}