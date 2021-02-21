import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './_helpers';
import {MemoLayoutComponent} from './memos/memo-layout.component';
import {MemoListComponent} from './memos/memo-list.component';
import {MemoAddEditComponent} from './memos/memo-add-edit.component';

const accountModule = () => import('./account/account.module').then(x => x.AccountModule);
const usersModule = () => import('./users/users.module').then(x => x.UsersModule);

const routes: Routes = [
    {
        path: 'memos', component: MemoLayoutComponent,
        children: [
            { path: '', component: MemoListComponent },
            { path: 'add', component: MemoAddEditComponent },
            { path: 'edit/:id', component: MemoAddEditComponent }
        ]
    },
    { path: 'users', loadChildren: usersModule, canActivate: [AuthGuard] },
    { path: 'account', loadChildren: accountModule },

    // otherwise redirect to home
    { path: '**', redirectTo: 'memos' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
