package dev.liquidnetwork.liquidpractice.kiteditor.menu;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.PlayerUtil;
import dev.liquidnetwork.liquidpractice.util.external.BukkitReflection;
import dev.liquidnetwork.liquidpractice.util.external.ItemBuilder;
import dev.liquidnetwork.liquidpractice.util.external.menu.Button;
import dev.liquidnetwork.liquidpractice.util.external.menu.Menu;
import dev.liquidnetwork.liquidpractice.util.external.menu.button.DisplayButton;
import dev.liquidnetwork.liquidpractice.kit.KitInventory;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitEditorMenu extends Menu {

    private static final int[] ITEM_POSITIONS = new int[]{
            18, 19, 20, 21, 22, 23, 24, 25
    };
    private static final int[] BORDER_POSITIONS = new int[]{9, 10, 11, 12, 13, 14, 15, 16, 17};
    private static final Button BORDER_BUTTON = Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 8, " ");

    KitEditorMenu() {
        setUpdateAfterClick(false);
    }

    @Override
    public String getTitle(Player player) {
        Profile profile = Profile.getByUuid(player.getUniqueId());
        return "&bEditing &7(" + profile.getKitEditor().getSelectedKit().getName() + ")";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        for (int border : BORDER_POSITIONS) {
            buttons.put(border, BORDER_BUTTON);
        }

        buttons.put(2, new CurrentKitButton());
        buttons.put(3, new SaveButton());
        buttons.put(4, new LoadDefaultKitButton());
        buttons.put(5, new ClearInventoryButton());
        buttons.put(6, new CancelButton());

        Profile profile = Profile.getByUuid(player.getUniqueId());
        Kit kit = profile.getKitEditor().getSelectedKit();
        KitInventory kitInventory= profile.getKitEditor().getSelectedKitInventory();

        List<ItemStack> items = kit.getEditRules().getEditorItems();

        if (!kit.getEditRules().getEditorItems().isEmpty()) {
            for (int i = 20; i < (kit.getEditRules().getEditorItems().size() + 20); i++) {
                buttons.put(ITEM_POSITIONS[i - 20], new InfiniteItemButton(items.get(i - 20)));
            }
        }

        return buttons;
    }

    @Override
    public void onOpen(Player player) {
        if (!isClosedByMenu()) {
            PlayerUtil.reset(player);

            Profile profile = Profile.getByUuid(player.getUniqueId());
            profile.getKitEditor().setActive(true);

            if (profile.getKitEditor().getSelectedKit() != null) {
                player.getInventory().setContents(profile.getKitEditor().getSelectedKitInventory().getContents());
            }

            player.updateInventory();
        }
    }

    @Override
    public void onClose(Player player) {
        Profile profile = Profile.getByUuid(player.getUniqueId());
        profile.getKitEditor().setActive(false);

        if (!profile.isInFight()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                PlayerUtil.reset(player, false);
                profile.refreshHotbar();
                }
            }.runTask(LiquidPractice.getInstance());
        }
    }



    @AllArgsConstructor
    private static class CurrentKitButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            Profile profile = Profile.getByUuid(player.getUniqueId());

            return new ItemBuilder(Material.NAME_TAG)
                    .name("&bEditing &r" + profile.getKitEditor().getSelectedKit().getName())
                    .build();
        }

    }

    @AllArgsConstructor
    private static class ClearInventoryButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.INK_SACK)
                    .durability(14)
                    .name("&b&lClear Inventory")
                    .lore(Arrays.asList(
                            "&7This will clear your inventory",
                            "&7so you can start over."
                    ))
                    .build();
        }

        @Override
        public void clicked(Player player, int i, ClickType clickType, int hb) {
            Button.playNeutral(player);
            player.getInventory().setContents(new ItemStack[36]);
            player.updateInventory();
        }

        @Override
        public boolean shouldUpdate(Player player, ClickType clickType) {
            return true;
        }

    }

    @AllArgsConstructor
    private static class LoadDefaultKitButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.INK_SACK)
                    .durability(11)
                    .name(CC.RED + CC.BOLD + "&bLoad default kit")
                    .lore(Arrays.asList(
                            CC.RED + "&7Click this to load the default kit",
                            CC.RED + "&7into the kit editing menu."
                    ))
                    .build();
        }

        @Override
        public void clicked(Player player, int i, ClickType clickType, int hb) {
            Button.playNeutral(player);

            Profile profile = Profile.getByUuid(player.getUniqueId());

            player.getInventory()
                    .setContents(profile.getKitEditor().getSelectedKit().getKitInventory().getContents());
            player.updateInventory();
        }

        @Override
        public boolean shouldUpdate(Player player, ClickType clickType) {
            return true;
        }

    }

    @AllArgsConstructor
    private static class SaveButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.INK_SACK)
                    .durability(10)
                    .name("&a&lSave")
                    .lore("&7Click this to save your kit.")
                    .build();
        }

        @Override
        public void clicked(Player player, int i, ClickType clickType, int hb) {
            Button.playNeutral(player);
            player.closeInventory();

            Profile profile = Profile.getByUuid(player.getUniqueId());

            if (profile.getKitEditor().getSelectedKitInventory() != null) {
                profile.getKitEditor().getSelectedKitInventory().setContents(player.getInventory().getContents());
            }

        PlayerUtil.reset(player, false);
        profile.refreshHotbar();

            new KitManagementMenu(profile.getKitEditor().getSelectedKit()).openMenu(player);
        }

    }

    @AllArgsConstructor
    private static class CancelButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.INK_SACK)
                    .durability(1)
                    .name("&b&lCancel")
                    .lore(Arrays.asList(
                            "&7Click this to abort editing your kit,",
                            "&7and return to the kit menu."
                    ))
                    .build();
        }

        @Override
        public void clicked(Player player, int i, ClickType clickType, int hb) {
            Button.playNeutral(player);

            Profile profile = Profile.getByUuid(player.getUniqueId());

            if (profile.getKitEditor().getSelectedKit() != null) {
                new KitManagementMenu(profile.getKitEditor().getSelectedKit()).openMenu(player);
            }
        }

    }

    private static class InfiniteItemButton extends DisplayButton {

        InfiniteItemButton(ItemStack itemStack) {
            super(itemStack, false);
        }

        @Override
        public void clicked(Player player, int slot, ClickType clickType, int hotbar) {
            Inventory inventory=player.getOpenInventory().getTopInventory();
            ItemStack itemStack=inventory.getItem(slot);

            inventory.setItem(slot, itemStack);

            player.setItemOnCursor(itemStack);
            player.updateInventory();
        }

    }

}
