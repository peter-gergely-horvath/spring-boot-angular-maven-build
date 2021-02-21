import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';

import {Command} from './command';
import {map} from 'rxjs/operators';

class Response<T> {
    constructor(public result: T) {}
}

@Injectable({ providedIn: 'root' })
export class CommandDispatcher {

    constructor(private http: HttpClient) { }

    dispatchCommand<T>(command: Command<T>): Observable<T> {
        return this.http.post<Response<T>>(`commands`, command)
            .pipe(map(response => response.result)) ;
    }
}
