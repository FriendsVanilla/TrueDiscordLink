package com.visualfiredev.truediscordlink.listeners;

import com.visualfiredev.truediscordlink.TrueDiscordLink;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.Map;

public class DiscordChatListener implements MessageCreateListener {

    // Instance Variables
    private final TrueDiscordLink discordlink;

    // Constructor
    public DiscordChatListener(TrueDiscordLink discordlink) {
        this.discordlink = discordlink;
    }

    // Event Handler
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Message message = event.getMessage();

        // Exclude Non-Server Messages, System Messages, Bot Messages, & Webhook Messages
        if (!message.isServerMessage() || message.getAuthor() == null || message.getAuthor().isBotUser() || message.getAuthor().isWebhook()) {
            return;
        }

        // Get & Parse Prefix
        String prefix = discordlink.getConfig().getString("bot.discord.prefix");
        if (prefix == null || prefix.isEmpty()) {
            prefix = "tdl!";
        }

        // Check for Prefix / Mention Tag
        if (
            message.getContent().startsWith(prefix) ||
            (message.getMentionedUsers().size() > 0 && message.getMentionedUsers().contains(discordlink.getDiscord().getYourself()))
        ) {
            String content = message.getContent().replace("<@!", "<@").replace(discordlink.getDiscord().getYourself().getMentionTag() + " ", prefix);
            String command = content.substring(prefix.length());

            // Custom Commands
            ConfigurationSection commands = discordlink.getConfig().getConfigurationSection("bot.discord.commands");
            if (commands != null) {

                // Fetch Commands
                Map<String, Object> values = commands.getValues(false);
                if (values.size() > 0) {

                    // Find Command
                    for (Map.Entry<String, Object> entry : values.entrySet()) {
                        if (!(entry.getValue() instanceof String)) {
                            continue;
                        }

                        // Handle Command
                        if (command.equalsIgnoreCase((String) entry.getKey())) {

                            String value = (String) entry.getValue();

                            // Handle Playerholder API Support
                            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                value = PlaceholderAPI.setPlaceholders(null, value);
                                value = ChatColor.stripColor(value);
                            }

                            message.getChannel().sendMessage(value);
                            return;
                        }
                    }
                }
            }

            // Pre-Made Commands
            if (command.equalsIgnoreCase("ping")) {
                message.getChannel().sendMessage("Pong!");
                return;
            }
        }

        // If not a command, send the message
        discordlink.getDiscordManager().sendMinecraftMessage(message);
    }

}
