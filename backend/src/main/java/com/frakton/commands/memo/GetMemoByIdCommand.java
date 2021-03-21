package com.frakton.commands.memo;

import com.frakton.entities.Memo;
import com.frakton.repositories.MemoRepository;
import org.commandmosaic.api.Command;
import org.commandmosaic.api.CommandContext;
import org.commandmosaic.api.Parameter;
import org.commandmosaic.security.annotation.RestrictedAccess;
import org.springframework.beans.factory.annotation.Autowired;

@RestrictedAccess(requiredRoles = "USER")
public class GetMemoByIdCommand implements Command<Memo> {

    @Parameter
    private Long id;

    @Autowired
    private MemoRepository memoRepository;

    @Override
    public Memo execute(CommandContext commandContext) {
        return memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Memo not found: " + id));
    }
}
