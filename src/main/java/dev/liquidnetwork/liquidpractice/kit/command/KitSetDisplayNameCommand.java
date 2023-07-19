package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "kit setdisplayname", permission= "practice.admin")
public class KitSetDisplayNameCommand {
    public void execute(Player player, @CPL("kit") String kit, @CPL("displayname") String display) {
        Kit dakit = Kit.getByName(kit);
        if (dakit == null) {
            player.sendMessage(CC.translate("&7A Kit with that name does not exist."));
            return;
        }
        dakit.setDisplayName(display);
        dakit.save();
        player.sendMessage(CC.translate("&7Successfully updated the kit &b" + dakit.getName() + "'s &7display name."));

    }
}
