package net.frosty.conway3d.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.frosty.conway3d.item.ControllerItem;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import java.util.HashSet;

public class ResetConwayBlocksCommand {
     public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registry, CommandManager.RegistrationEnvironment environment) {
         dispatcher.register(CommandManager.literal("resetConwayBlocks").executes(ResetConwayBlocksCommand::run));
     }

     private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
         ControllerItem.conwayBlocks = new HashSet<>();
         return 1;
     }

}
