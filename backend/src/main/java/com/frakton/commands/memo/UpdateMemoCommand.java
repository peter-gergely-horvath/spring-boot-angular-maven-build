package com.frakton.commands.memo;

import com.frakton.entities.Memo;
import com.frakton.repositories.MemoRepository;
import org.commandmosaic.api.Command;
import org.commandmosaic.api.CommandContext;
import org.commandmosaic.api.Parameter;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateMemoCommand implements Command<Void> {

    @Parameter
    private Memo memo;

    @Autowired
    private MemoRepository memoRepository;

    @Override
    public Void execute(CommandContext commandContext) {

        memoRepository.updateMemo(memo.getId(), memo.getText());

        return null;
    }
}
