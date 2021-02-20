package com.frakton.commands.memo;

import org.commandmosaic.api.Command;
import org.commandmosaic.api.CommandContext;
import org.commandmosaic.api.Parameter;

public class GetMemoByIdCommand implements Command<Object> {

    @Parameter
    private String id;

    @Override
    public Object execute(CommandContext commandContext) {
        return "Retrieving data for: " + id;
    }
}
