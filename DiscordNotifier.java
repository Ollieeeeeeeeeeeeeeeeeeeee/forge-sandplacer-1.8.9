package com.example.sandplacer;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

public class DiscordNotifier {
    private static final String TOKEN = "MTM5NjQwMDYxODcwODUzNzM5NA.GXvA-S.I7lF1U47pdjABd5-rQarh0dppt4alj5YGZiA1w"; 
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
