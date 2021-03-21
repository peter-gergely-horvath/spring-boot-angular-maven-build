package com.frakton.commands.memo;

import com.frakton.repositories.MemoRepository;
import org.commandmosaic.api.Command;
import org.commandmosaic.api.CommandContext;
import org.commandmosaic.api.Parameter;
import org.commandmosaic.security.annotation.RestrictedAccess;
import org.springframework.beans.factory.annotation.Autowired;

@RestrictedAccess(requiredRoles = "USER")
public class DeleteMemoByIdCommand implements Command<Void> {

    @Parameter
    private Long id;

    @Autowired
    private MemoRepository memoRepository;

    @Override
    public Void execute(CommandContext commandContext) {
        memoRepository.deleteById(id);

        return null;
    }
}
