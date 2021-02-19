import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {BehaviorSubject, Observable, throwError} from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '../../environment';
import { User } from '../_models';
import {Memo} from '../_models/memo';

@Injectable({ providedIn: 'root' })
export class MemoService {

    constructor(
        private router: Router,
        private http: HttpClient
    ) { }

    getAllMemos(): Observable<Memo[]> {
        return this.http.get<Memo[]>(`${environment.apiUrl}/memos`);
    }

    getById(id: string): Observable<Memo> {
        return this.http.get<Memo>(`${environment.apiUrl}/memos/${id}`);
    }

    update(memo: Memo) {
        return throwError('not implemented yet');
    }

    delete(memo: Memo) {
        return throwError('not implemented yet');
    }
}
