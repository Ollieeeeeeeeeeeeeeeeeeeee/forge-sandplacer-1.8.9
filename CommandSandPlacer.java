package com.example.sandplacer;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandSandPlacer extends CommandBase {

    @Override
    public String getCommandName() {
        return "sandplacer";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/sandplacer <start|stop|status>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.addChatMessage(new ChatComponentText("§cUsage: /sandplacer <start|stop|status>"));
            return;
        }

        switch (args[0].toLowerCase()) {
            case "start":
                SandTask.ENABLED = true;
                sender.addChatMessage(new ChatComponentText("§aSand Placer enabled."));
                break;
            case "stop":
                SandTask.ENABLED = false;
                sender.addChatMessage(new ChatComponentText("§cSand Placer disabled."));
                break;
            case "status":
                sender.addChatMessage(new ChatComponentText("§eSand Placer is currently: " + (SandTask.ENABLED ? "§aON" : "§cOFF")));
                break;
            default:
                sender.addChatMessage(new ChatComponentText("§cUnknown command. Use start, stop, or status."));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0; // Allow anyone (including client) to use
    }
}
