package dev.liquidnetwork.liquidpractice.party.command;

import dev.liquidnetwork.liquidpractice.enums.PartyPrivacyType;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.entity.Player;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;

@CommandMeta(label = { "party open", "p open" })
public class PartyOpenCommand
{
    public void execute(final Player player) {
        final Profile profile = Profile.getByUuid(player.getUniqueId());
        if (!player.hasPermission("practice.donator")) {
            player.sendMessage(CC.translate("&7You do not have permission to use Party Settings."));
            return;
        }
        if (profile.getParty() == null) {
            player.sendMessage(CC.RED + "You do not have a party.");
            return;
        }
        if (!profile.getParty().isLeader(player.getUniqueId())) {
            player.sendMessage(CC.RED + "You are not the leader of your party.");
            return;
        }
        profile.getParty().setPrivacy(PartyPrivacyType.OPEN);
    }
}
