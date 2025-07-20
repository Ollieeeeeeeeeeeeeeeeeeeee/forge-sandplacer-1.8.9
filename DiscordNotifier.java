package com.example.sandplacer;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

public class DiscordNotifier {
    private static final String TOKEN = "MTM1NzczMDQ5OTcxOTg1NjM5MA.GwASgj.6YQgq6KcPU3Cci75SzK_smQKU6b2iRmyKiPzXQ";
    private static final String USER_ID = "1257352856168566886";

    private static JDA jda;

    static {
        try {
            jda = JDABuilder.createDefault(TOKEN).build();
            jda.awaitReady();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void send(String msg) {
        try {
            User user = jda.retrieveUserById(USER_ID).complete();
            user.openPrivateChannel().complete().sendMessage(msg).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package com.example.sandplacer;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordNotifier extends ListenerAdapter {
    private static final String TOKEN = "YOUR_DISCORD_BOT_TOKEN";
    private static final String USER_ID = "YOUR_DISCORD_USER_ID";

    private static JDA jda;

    static {
        try {
            jda = JDABuilder.createDefault(TOKEN)
                    .addEventListeners(new DiscordNotifier())
                    .build()
                    .awaitReady();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void send(String message) {
        try {
            User user = jda.retrieveUserById(USER_ID).complete();
            user.openPrivateChannel().complete().sendMessage(message).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.isFromType(ChannelType.PRIVATE)) return;
        if (!event.getAuthor().getId().equals(USER_ID)) return;

        String content = event.getMessage().getContentRaw().trim().toLowerCase();

        switch (content) {
            case "sandplacer start":
                SandTask.ENABLED = true;
                send("✅ Sand Placer ENABLED");
                break;
            case "sandplacer stop":
                SandTask.ENABLED = false;
                send("🛑 Sand Placer DISABLED");
                break;
            case "sandplacer status":
                send("ℹ️ Sand Placer is currently " + (SandTask.ENABLED ? "ON ✅" : "OFF 🛑"));
                break;
            default:
                send("❓ Unknown command. Use: `sandplacer start`, `stop`, or `status`.");
                break;
        }
    }
}
