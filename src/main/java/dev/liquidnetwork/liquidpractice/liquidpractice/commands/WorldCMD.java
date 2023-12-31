package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.external.ItemBuilder;
import dev.liquidnetwork.liquidpractice.util.external.menu.Button;
import dev.liquidnetwork.liquidpractice.util.external.menu.Menu;
import lombok.AllArgsConstructor;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

@CommandMeta(label = {"worlds","world"}, permission = "practice.staff")
public class WorldCMD {
    public void execute(Player player) {
        new WorldMenu().openMenu(player);
    }

    private static class WorldMenu extends Menu {
        @Override
        public String getTitle(Player player) {
            return CC.translate("&bWorlds");
        }

        @Override
        public Map<Integer, Button> getButtons(Player player) {
            Map<Integer, Button> buttons = new HashMap<>();
            for ( World world : Bukkit.getWorlds() ) {
                buttons.put(buttons.size(), new WorldButton(world));
            }
            return buttons;
        }
    }

    @AllArgsConstructor
    private static class WorldButton extends Button {

        World world;

        @Override
        public ItemStack getButtonItem(Player player) {
            boolean isNether = world.getEnvironment() == World.Environment.NETHER;
            boolean isEnd = world.getEnvironment() == World.Environment.THE_END;
            return new ItemBuilder((isNether ? Material.NETHERRACK : isEnd ? Material.ENDER_STONE : Material.GRASS))
                    .name((isNether ? "&c" : isEnd ? "&b" : "&a") + world.getName())
                    .lore((player.getWorld() == world ? "&cYou are already in that world!" : "&7&oClick to teleport"))
                    .build();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.closeInventory();
            if (player.getWorld() != world) {
                player.teleport(world.getSpawnLocation());
                player.sendMessage(CC.translate("&7You are now in world &b" + world.getName() + "&7!"));
            }
        }
    }
}


