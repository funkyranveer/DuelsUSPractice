package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit infinitestrength"}, permission = "practice.admin")
public class KitSetInfiniteStrengthCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage(CC.translate("&7That kit does not exist."));
        } else {
            if (kit.getGameRules().isInfinitestrength()) {
                kit.getGameRules().setInfinitestrength(false);
            } else if (!kit.getGameRules().isInfinitestrength()) {
                kit.getGameRules().setInfinitestrength(true);
            }
            kit.save();
            player.sendMessage(CC.translate("&7Updated infinite-strength mode for &b" + kit.getName() +  " &7to &b" + (kit.getGameRules().isInfinitestrength() ? "true!" : "false!")));
        }
    }
}
