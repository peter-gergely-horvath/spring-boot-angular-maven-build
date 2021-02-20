import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { MemosRoutingModule } from './memos-routing.module';
import { MemoLayoutComponent } from './memo-layout.component';
import { MemoListComponent } from './memo-list.component';
import { MemoAddEditComponent } from './memo-add-edit.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MemosRoutingModule
  ],
  declarations: [
    MemoLayoutComponent,
    MemoListComponent,
    MemoAddEditComponent
  ]
})
export class MemosModule { }
