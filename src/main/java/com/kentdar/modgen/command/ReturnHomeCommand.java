package com.kentdar.modgen.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class ReturnHomeCommand {
    public ReturnHomeCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("home")
                .then(Commands.literal("set")
                        .executes((command) -> {
                            return retunrHome(command.getSource());
                        })));

    }

    public int retunrHome(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        if(player.getPersistentData().getIntArray("homepos").length != 0){

            int[] playerPos = player.getPersistentData().getIntArray("homepos");
            player.setPositionAndUpdate(playerPos[0], playerPos[1], playerPos[2]);
            source.sendFeedback(new StringTextComponent("Player Returned Home!"), true);
            return 1;

        }else {
            source.sendFeedback(new StringTextComponent("No Home Position Has Been Set!"), true);
            return -1;
        }
    }
}
