import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';

import {Command} from './command';

@Injectable({ providedIn: 'root' })
export class CommandDispatcher {

    constructor(private http: HttpClient) { }

    dispatchCommand<T>(command: Command<T>): Observable<T> {
        return this.http.post<T>(`commands`, command);
    }
}
