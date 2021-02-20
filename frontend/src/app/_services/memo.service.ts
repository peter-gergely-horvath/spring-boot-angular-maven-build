import {Injectable} from '@angular/core';
import {Observable, throwError} from 'rxjs';

import {environment} from '../../environment';
import {Memo} from '../_models/memo';
import {CommandDispatcher} from '../_commandmosaic/command-dispatcher';
import {Command} from '../_commandmosaic/command';
import {HttpClient} from '@angular/common/http';



@Injectable({providedIn: 'root'})
export class MemoService {

    constructor(
        private http: HttpClient,
        private commandDispatcher: CommandDispatcher) {
    }

    getAllMemos(): Observable<Memo[]> {
        return this.http.get<Memo[]>(`${environment.apiUrl}/memos`);
    }

    getById(commandId: string): Observable<Memo> {
        return this.commandDispatcher.dispatchCommand(
            new Command<Memo>('memo/GetMemoByIdCommand', {id: commandId}));
    }

    create(memoText: string) {
        return throwError('not implemented yet');
    }

    update(memo: Memo) {
        return throwError('not implemented yet');
    }

    delete(memo: Memo) {
        return throwError('not implemented yet');
    }
}
