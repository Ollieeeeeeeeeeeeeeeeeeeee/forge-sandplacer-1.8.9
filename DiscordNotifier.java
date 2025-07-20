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
