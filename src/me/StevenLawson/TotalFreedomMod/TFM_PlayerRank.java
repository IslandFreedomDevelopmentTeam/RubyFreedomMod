package me.StevenLawson.TotalFreedomMod;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.COOWNER;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.DEVELOPERS;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.EXECUTIVES;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.FOP_DEVELOPERS;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.RF_DEVELOPERS;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.SYS;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum TFM_PlayerRank
{
    DEVELOPER("a " + ChatColor.DARK_PURPLE + "TotalFreedom Developer", ChatColor.DARK_PURPLE + "[TF-Dev]"),
    FOP_DEVELOPER("a " + ChatColor.DARK_PURPLE + "Old FreedomOp Developer", ChatColor.DARK_PURPLE + "[FOP-Dev]")
    IMPOSTOR("an " + ChatColor.YELLOW + ChatColor.UNDERLINE + "Impostor", ChatColor.YELLOW.toString() + ChatColor.UNDERLINE + "[IMP]"),
    FAKEIMPOSTOR("a " + ChatColor.YELLOW + ChatColor.UNDERLINE + "FAKE Impostor", ChatColor.YELLOW.toString() + ChatColor.UNDERLINE + "[IMP]"),
    NON_OP("a " + ChatColor.GREEN + "Non-OP", ChatColor.GREEN.toString()),
    OP("an " + ChatColor.RED + "OP", ChatColor.RED + "[OP]"),
    SUPER("a " + ChatColor.GOLD + "Super Admin", ChatColor.GOLD + "[SA]"),
    TELNET("a " + ChatColor.DARK_GREEN + "Super Telnet Admin", ChatColor.DARK_GREEN + "[STA]"),
    SENIOR("a " + ChatColor.LIGHT_PURPLE + "Senior Admin", ChatColor.LIGHT_PURPLE + "[SrA]"),
    LUKA("the " + ChatColor.BLUE + "Owner " + ChatColor.AQUA + "and" + ChatColor.BLUE + " Founder " + ChatColor.AQUA + "of " + ChatColor.RED + "IslandFreedom", ChatColor.translateAlternateColorCodes('&', "&8[&cO&4w&5n&6e&3r &bof &6Isl&5and&6Free&4dom&8]&9")),
    SYS_ADMIN("a " + ChatColor.DARK_RED + "System-Admin", ChatColor.DARK_RED + "[Sys-Admin]"),
    HORSE("a " + ChatColor.DARK_RED + "System-Admin" + ChatColor.AQUA + " and the " + ChatColor.RED + "Admin Manager", ChatColor.DARK_RED + "[Sys. + Man.]"),
    LEAD_DEVELOPER("the " + ChatColor.DARK_PURPLE + "Lead Developer" + ChatColor.AQUA + " of " + ChatColor.RED + "RubyFreedom", ChatColor.DARK_PURPLE + "[L-Dev]"),
    EXEC("an " + ChatColor.YELLOW + "Executive", ChatColor.YELLOW + "[Exec]"),
    DLG("the " + ChatColor.BLUE + "Master Ass Kicker" + ChatColor.AQUA + "of" + ChatColor.BLUE + "RubyFreedom!" + ChatColor.AQUA + "and a " + ChatColor.RED + "System-Admin!", ChatColor.translateAlternateColorCodes('&', "&8[&5Master&8-&6Ass&8-&2Kicker&8]&9")),
    CREEPER("the " + ChatColor.GREEN + "Creeper " + ChatColor.AQUA + "of " + ChatColor.BLUE + "RubyFreedom! " + ChatColor.AQUA + "and a " + ChatColor.RED + "System-Admin!", ChatColor.translateAlternateColorCodes('&', "&8[&eCreeper + &4Sys-Admin&8]")),
    CO_OWNER("a " + ChatColor.BLUE + "Co-Owner" + ChatColor.AQUA + " of " + ChatColor.RED + "RubyFreedom", ChatColor.BLUE + "[Co-Owner]"),
    DECY("a " + ChatColor.BLUE + "Co-Owner" + ChatColor.AQUA + " of " + ChatColor.RED + "RubyFreedom and the Decstar", ChatColor.BLUE + "[Decstar]"),
    MYSTERI("a " + ChatColor.GOLD + "Potato Caek", ChatColor.RED + "[Mysteri]"),
    DARK("an " + ChatColor.BLUE + "Former Owner " + ChatColor.AQUA + "but now a " + ChatColor.RED + "Co-Owner" + ChatColor.AQUA + ", and the " + ChatColor.DARK_PURPLE + "Development Team Manager", ChatColor.translateAlternateColorCodes('&', "&8[&CCo-Owner&8]&9")),
    RUBY("the " + ChatColor.RED + "Ruby Hacker and the Exec-Lead-Developer!", ChatColor.translateAlternateColorCodes('&', "&8[&4Ru&3by &6Hac&1ker&b-&5Ex&6ec-&5Le&6ad-&1D&9e&2v&8]")),
    CHAR("the " + ChatColor.DARK_PURPLE + "Co-Chief Forum Developer and a Roblox Pro!", ChatColor.translateAlternateColorCodes('&', "&8[&5Co-Chief Forum Dev&8]&9")),
    CONSOLE("the " + ChatColor.DARK_PURPLE + "Console", ChatColor.DARK_PURPLE + "[Console]");
    private final String loginMessage;
    private final String prefix;

    private TFM_PlayerRank(String loginMessage, String prefix)
    {
        this.loginMessage = loginMessage;
        this.prefix = prefix;
    }

    public static String getLoginMessage(CommandSender sender)
    {
        // Handle console
        if (!(sender instanceof Player))
        {
            return fromSender(sender).getLoginMessage();
        }

        // Handle admins
        final TFM_Admin entry = TFM_AdminList.getEntry((Player) sender);
        if (entry == null)
        {
            // Player is not an admin
            return fromSender(sender).getLoginMessage();
        }

        // Custom login message
        final String loginMessage = entry.getCustomLoginMessage();

        if (loginMessage == null || loginMessage.isEmpty())
        {
            return fromSender(sender).getLoginMessage();
        }

        return ChatColor.translateAlternateColorCodes('&', loginMessage);
    }

    public static TFM_PlayerRank fromSender(CommandSender sender)
    {
        if (!(sender instanceof Player))
        {
            return CONSOLE;
        }
        
        if (TFM_AdminList.isAdminImpostor((Player) sender))
        {
            return IMPOSTOR;
        }

        else if (DEVELOPERS.contains(sender.getName()))
        {
            return DEVELOPER;
        }
        
        else if (sender.getName().equals("reuben4545"))
        {
            return RUBY;
        }

        else if (FOP_DEVELOPERS.contains(sender.getName()))
        {
            return FOP_DEVELOPER;
        }

        else if (SYS.contains(sender.getName()))
        {
            return SYS_ADMIN;
        }

        else if (EXECUTIVES.contains(sender.getName()))
        {
            return EXEC;
        }

        else if (COOWNER.contains(sender.getName()))
        {
            return CO_OWNER;
        }

        final TFM_Admin entry = TFM_AdminList.getEntryByIp(TFM_Util.getIp((Player) sender));

        final TFM_PlayerRank rank;

        if (entry != null && entry.isActivated())
        {
            if (TFM_ConfigEntry.SERVER_OWNERS.getList().contains(sender.getName()))
            {
                return OWNER;
            }

            if (entry.isSeniorAdmin())
            {
                rank = SENIOR;
            }
            else if (entry.isTelnetAdmin())
            {
                rank = TELNET;
            }
            else
            {
                rank = SUPER;
            }
        }
        else
        {
            if (sender.isOp())
            {
                rank = OP;
            }
            else
            {
                rank = NON_OP;
            }

        }
        return rank;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public String getLoginMessage()
    {
        return loginMessage;
    }
}
