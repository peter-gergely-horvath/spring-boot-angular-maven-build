package com.frakton.commands.memo;

import com.frakton.entities.Memo;
import com.frakton.repositories.MemoRepository;
import org.commandmosaic.api.Command;
import org.commandmosaic.api.CommandContext;
import org.commandmosaic.api.Parameter;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateMemoCommand implements Command<Void> {

    @Parameter
    private String text;

    @Autowired
    private MemoRepository memoRepository;

    @Override
    public Void
    execute(CommandContext commandContext) {

        Memo memo = new Memo();
        memo.setText(text);

        memoRepository.save(memo);

        return null;
    }
}
