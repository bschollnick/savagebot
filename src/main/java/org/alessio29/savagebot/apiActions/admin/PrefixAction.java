package org.alessio29.savagebot.apiActions.admin;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.alessio29.savagebot.apiActions.IDiscordAction;
import org.alessio29.savagebot.internal.Prefixes;
import org.alessio29.savagebot.internal.commands.CommandExecutionResult;

public class PrefixAction implements IDiscordAction {
    @Override
    public CommandExecutionResult doAction(MessageReceivedEvent event, String[] args) {
        CommandExecutionResult result;
        if (args.length>0) {
            String newPrefix = args[0].trim();
            if (newPrefix.length()>1) {
                result = new CommandExecutionResult("Prefix must be one-character long!", 1);
            } else {

                if (newPrefix.equals("?") || newPrefix.equals("*") || newPrefix.equals("^") || newPrefix.equals("\\")  ) {
                    result = new CommandExecutionResult("Prefix must not be question sign, asterisk, backslash or circumflex!", 1);
                } else {
                    Prefixes.setPrefix(event.getAuthor(), newPrefix);
                    result = new CommandExecutionResult("Prefix is set to "+newPrefix, 2);
                }
            }
        } else {
            String prfx = Prefixes.getPrefix(event.getAuthor());
            if (prfx == null) {
                result = new CommandExecutionResult("Custom prefix is not set! Default prefix is "+Prefixes.DEFAULT_BOT_PREFIX, 1);
            } else {
                result = new CommandExecutionResult("Prefix is '"+prfx+"'", 1);
            }
        }
        return result;
    }
}