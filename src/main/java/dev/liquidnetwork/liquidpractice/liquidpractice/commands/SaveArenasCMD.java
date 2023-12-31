package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"practice savearenas", "practice arenas save"}, permission="practice.admin")
public class SaveArenasCMD {
    public void execute(Player p) {
        Arena.getArenas().forEach(Arena::save);
        p.sendMessage(CC.translate("&b") + "Arenas has been saved!");
    }
}

