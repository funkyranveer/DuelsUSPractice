package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandMeta(label={"practice ver", "practice version"})
public class VersionCMD {
    public void execute(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m--------&7&m" + StringUtils.repeat("-", 37) + "&b&m--------"));
        p.sendMessage(CC.translate("&7This server is running &Practice version&4 " + LiquidPractice.getInstance().getDescription().getVersion()));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m--------&7&m" + StringUtils.repeat("-", 37) + "&b&m--------"));
    }
}
