package me.border;

import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BorderAdvancement extends JavaPlugin implements Listener {

    private final double START_BORDER = 20;
    private final double INCREASE_AMOUNT = 3;

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(this, this);

        WorldBorder border = Bukkit.getWorlds().get(0).getWorldBorder();

        // Başlangıçta border 20 blok olsun
        if (border.getSize() > START_BORDER) {
            return;
        }

        border.setSize(START_BORDER);

        getLogger().info("BorderAdvancement aktif!");
    }


    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent event) {

        String advancement = event.getAdvancement().getKey().toString();

        // Tarif başarımlarını sayma
        if (advancement.startsWith("minecraft:recipes")) {
            return;
        }

        WorldBorder border = Bukkit.getWorlds().get(0).getWorldBorder();

        double yeniBoyut = border.getSize() + INCREASE_AMOUNT;

        border.setSize(yeniBoyut);

        Bukkit.broadcastMessage(
                "§a" + event.getPlayer().getName()
                + " bir başarım aldı! "
                + "§eBorder +3 büyüdü! "
                + "§7Yeni boyut: "
                + (int)yeniBoyut
        );
    }
}
