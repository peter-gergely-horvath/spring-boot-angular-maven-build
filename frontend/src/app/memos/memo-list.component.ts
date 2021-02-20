import {Component, OnDestroy, OnInit} from '@angular/core';
import { first } from 'rxjs/operators';

import { AccountService } from '../_services';
import {User} from '../_models';
import {Memo} from '../_models/memo';
import {Subscription} from 'rxjs';
import {MemoService} from '../_services/memo.service';

@Component({ templateUrl: 'memo-list.component.html' })
export class MemoListComponent implements OnInit, OnDestroy {
    user: User;

    memos: Memo[];

    private memoSubscription: Subscription;

    constructor(private accountService: AccountService,
                private memoService: MemoService) {
        this.user = this.accountService.userValue;
    }

    ngOnInit() {
        this.memoSubscription = this.memoService.getAllMemos()
            .subscribe(loadedMemos => this.memos = loadedMemos);
    }

    ngOnDestroy(): void {
        this.memoSubscription.unsubscribe();
    }

    deleteMemo(id: string) {

    }
}
