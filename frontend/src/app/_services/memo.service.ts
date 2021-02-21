import {Injectable} from '@angular/core';
import {Observable, throwError} from 'rxjs';

import {environment} from '../../environment';
import {Memo} from '../_models/memo';
import {CommandDispatcher} from '../_commandmosaic/command-dispatcher';
import {Command} from '../_commandmosaic/command';
import {HttpClient} from '@angular/common/http';
import {tap} from 'rxjs/operators';



@Injectable({providedIn: 'root'})
export class MemoService {

    constructor(
        private http: HttpClient,
        private commandDispatcher: CommandDispatcher) {
    }

    getAllMemos(): Observable<Memo[]> {
        return this.commandDispatcher.dispatchCommand(
            new Command<[]>('memo/GetMemosOfCurrentUser', null));
    }

    getById(commandId: string): Observable<Memo> {
        return this.commandDispatcher.dispatchCommand(
            new Command<Memo>('memo/GetMemoByIdCommand', {id: commandId}))
            .pipe(tap(it => {
                console.log('Received:', it);
            }));
    }

    create(memoText: string) {
        return this.commandDispatcher.dispatchCommand(
            new Command<Memo>('memo/CreateMemoCommand', {text: memoText}));
    }

    update(theMemo: Memo) {
        return this.commandDispatcher.dispatchCommand(
            new Command<Memo>('memo/UpdateMemoCommand', {memo: theMemo}));
    }

    delete(memo: Memo) {
        return this.commandDispatcher.dispatchCommand(
            new Command<Memo>('memo/DeleteMemoByIdCommand', {id: memo.id}));
    }
}
