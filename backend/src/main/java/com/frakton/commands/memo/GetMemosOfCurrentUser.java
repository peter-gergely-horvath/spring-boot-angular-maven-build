package com.frakton.commands.memo;

import com.frakton.entities.Memo;
import com.frakton.repositories.MemoRepository;
import org.commandmosaic.api.Command;
import org.commandmosaic.api.CommandContext;
import org.commandmosaic.security.annotation.RestrictedAccess;
import org.springframework.beans.factory.annotation.Autowired;

@RestrictedAccess(requiredRoles = "USER")
public class GetMemosOfCurrentUser implements Command<Iterable<Memo>> {

    @Autowired
    private MemoRepository memoRepository;

    @Override
    public Iterable<Memo> execute(CommandContext commandContext) {

        return memoRepository.findAll();
    }
}
