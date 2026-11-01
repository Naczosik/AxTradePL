package com.artillexstudios.axtrade.commands.subcommands;

import com.artillexstudios.axapi.nms.wrapper.ServerPlayerWrapper;
import com.artillexstudios.axapi.utils.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

import static com.artillexstudios.axtrade.AxTrade.LANG;

public enum Help {
    INSTANCE;

    public void execute(CommandSender sender) {
        List<String> lines = sender.hasPermission("axtrade.admin")
                ? LANG.getStringList("admin-help")
                : LANG.getStringList("player-help");

        // naczos.pl: players get full components, so <click> and <hover> from lang.yml work
        if (sender instanceof Player player) {
            ServerPlayerWrapper wrapper = ServerPlayerWrapper.wrap(player);
            for (String m : lines) {
                wrapper.message(StringUtils.format(m, Map.of()));
            }
            return;
        }

        for (String m : lines) {
            sender.sendMessage(StringUtils.formatToString(m));
        }
    }
}
