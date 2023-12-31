package dev.liquidnetwork.liquidpractice.liquidpractice;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import net.md_5.bungee.api.chat.ClickEvent;
import net.minecraft.server.v1_8_R3.ICommand;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@CommandMeta(label={"practice", "practice help", "prac"})
public class LiquidPracticeCMD {
    public void execute(Player p) {
        if (p.hasPermission("practice.staff")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m--------&7&m" + StringUtils.repeat("-", 37) + "&b&m--------"));
            p.sendMessage(CC.translate("&3Practice &7» LiquidPractice Commands"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m--------&7&m" + StringUtils.repeat("-", 37) + "&b&m--------"));
            p.sendMessage(CC.translate(" &8• &b/practice setlobby &8- &8&o(&7&oSets the lobby to player's location&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/practice savekits &8- &8&o(&7&oSave all Kits&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/practice savearenas &8- &8&o(&7&oSave all Arenas&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/practice savedata &8- &8&o(&7&oSave all Profiles&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/practice goldenhead &8- &8&o(&7&oReceive a pre-made G-Head&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/practice update &8- &8&o(&7&oUpdate all leaderboards&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/practice savedata &8- &8&o(&7&oSave all Profiles&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/practice savearenas &8- &8&o(&7&oSave all Arenas&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/practice savekits &8- &8&o(&7&oSave all Kits&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/practice hcf &8- &8&o(&7&oHelp on how to setup HCF&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/practice resetstats &8<&7name&8> &8- &8&o(&7&oResets a profile&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/practice rename &8<&7name&8> &8- &8&o(&7&oRenames item in hand&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/practice spawn &8- &8&o(&7&oRefresh Profile & Teleport to spawn&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/kit help &8- &8&o(&7&oView kit commands&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/arena help &8- &8&o(&7&oView arena commands&8&o)"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m--------&7&m" + StringUtils.repeat("-", 37) + "&b&m--------"));
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m--------&7&m" + StringUtils.repeat("-", 37) + "&b&m--------"));
            p.sendMessage(CC.translate("&eThis Server is running &dPractice &4Version:&9 " + LiquidPractice.getInstance().getDescription().getVersion()));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m--------&7&m" + StringUtils.repeat("-", 37) + "&b&m--------"));

        }
    }
}
