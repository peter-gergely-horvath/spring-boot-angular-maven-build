import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MemoLayoutComponent } from './memo-layout.component';
import { MemoListComponent } from './memo-list.component';
import { MemoAddEditComponent } from './memo-add-edit.component';


const routes: Routes = [
    {
        path: '', component: MemoLayoutComponent,
        children: [
            { path: '', component: MemoListComponent },
            { path: 'add', component: MemoAddEditComponent },
            { path: 'edit/:id', component: MemoAddEditComponent }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class MemosRoutingModule { }
