package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = {"kit remove", "kit delete"}, permission = "practice.admin")
public class KitRemoveCommand {
    public void execute(final Player player, @CPL("name") final String name) {
        if (name == null) {
            player.sendMessage(CC.translate("&7Please provide a name."));
            return;
        }
        final Kit kit = Kit.getByName(name);
        if (kit != null) {
            kit.delete();
            Kit.getKits().forEach(Kit::save);
            player.sendMessage(CC.translate("&7Removed the kit &b" + kit.getName() + "&7."));
        }
    }
}
