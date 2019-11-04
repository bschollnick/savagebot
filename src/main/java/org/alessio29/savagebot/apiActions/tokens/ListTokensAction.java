package org.alessio29.savagebot.apiActions.tokens;

import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.alessio29.savagebot.apiActions.IDiscordAction;
import org.alessio29.savagebot.characters.Character;
import org.alessio29.savagebot.characters.Characters;
import org.alessio29.savagebot.internal.builders.ReplyBuilder;
import org.alessio29.savagebot.internal.commands.CommandExecutionResult;

import java.util.Set;

public class ListTokensAction implements IDiscordAction {

    private static final int NAME_SIZE = 15;
    private static final int TOKEN_SIZE = 5;

    @Override
    public CommandExecutionResult doAction(MessageReceivedEvent event, String[] args) {
        Guild guild = event.getGuild();
        Channel channel = event.getTextChannel();
        Set<Character> chars = Characters.getCharacters(guild, channel);
        if (chars.isEmpty()) {
            return new CommandExecutionResult("No characters with tokens defined!",1);
        }
        ReplyBuilder replyBuilder = new ReplyBuilder();
        replyBuilder.newLine().
                blockQuote().
                rightPad("NAME", NAME_SIZE).
                tab().
                rightPad("TOKENS", TOKEN_SIZE).
                newLine();
        for (Character chr : chars ) {
            replyBuilder.rightPad(chr.getName(), NAME_SIZE).
                    tab().
                    rightPad(String.valueOf(chr.getTokens()), TOKEN_SIZE).
                    newLine();
        }
        replyBuilder.blockQuote();
        return new CommandExecutionResult(replyBuilder.toString(), 2);
    }
}
