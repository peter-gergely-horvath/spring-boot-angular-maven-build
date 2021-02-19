import {Component, OnDestroy, OnInit} from '@angular/core';

import { User } from '../_models';
import { AccountService } from '../_services';
import {Memo} from '../_models/memo';
import {first} from 'rxjs/operators';
import {MemoService} from '../_services/memo.service';
import {Subscription} from 'rxjs';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent implements OnInit, OnDestroy {
    user: User;

    memos: Memo[];

    private memoSubscription: Subscription;

    constructor(private accountService: AccountService,
                private memoService: MemoService) {
        this.user = this.accountService.userValue;
    }

    ngOnInit() {
        this.memoService.getAllMemos()
            .subscribe(loadedMemos => this.memos = loadedMemos);
    }

    ngOnDestroy(): void {
        this.memoSubscription.unsubscribe();
    }

    deleteMemo(id: string) {

    }
}
