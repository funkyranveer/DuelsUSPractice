package dev.liquidnetwork.liquidpractice.event.types.sumo.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "sumo setknockbackprofile", permission = "practice.staff")
public class SumoKnockbackCommand {

    public void execute(Player player, @CPL("knockback-profile") String kb) {
          if (kb == null) {
              player.sendMessage(CC.RED + "Please Specify a Knockback Profile.");
          }
          else {
              LiquidPractice.getInstance().getSumoManager().setSumoKnockbackProfile(kb);
              player.sendMessage(CC.GREEN + "Successfully set the knockback profile!");
          }
    }
}
