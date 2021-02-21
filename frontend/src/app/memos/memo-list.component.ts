import {Component, OnDestroy, OnInit} from '@angular/core';
import { first } from 'rxjs/operators';

import { AccountService } from '../_services';
import {User} from '../_models';
import {Memo} from '../_models/memo';
import {Subscription} from 'rxjs';
import {MemoService} from '../_services/memo.service';

@Component({ templateUrl: 'memo-list.component.html' })
export class MemoListComponent implements OnInit {
    user: User;

    memos: Memo[];

    constructor(private accountService: AccountService,
                private memoService: MemoService) {
        this.user = this.accountService.userValue;
    }

    ngOnInit() {
        this.loadMemos();
    }

    private loadMemos() {
        this.memoService.getAllMemos()
            .pipe(first())
            .subscribe(loadedMemos => this.memos = loadedMemos);
    }

    deleteMemo(memo: Memo) {
        this.memoService.delete(memo)
            .pipe(first())
            .subscribe(() => {
                this.loadMemos();
            });
    }
}
