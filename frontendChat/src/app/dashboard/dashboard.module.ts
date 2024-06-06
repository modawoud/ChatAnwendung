import {NgModule} from '@angular/core';
import {MatCardModule} from "@angular/material/card";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatDialogModule} from "@angular/material/dialog";
import {MatInputModule} from "@angular/material/input";
import {DashboardRoutingModule} from "./dashboard-routing.module";
import {MatButtonModule} from "@angular/material/button";
import {MatSelectModule} from "@angular/material/select";
import {MatOptionModule} from '@angular/material/core';
import {NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';
import {SharedModule} from "../shared/shared.module";
import {NgForOf, NgIf, NgSwitch, NgSwitchCase, NgSwitchDefault} from "@angular/common";
import {MatIconModule} from "@angular/material/icon";
import { ChatComponent } from './components/chat/chat.component';
import { UserDialogComponent } from './components/User/user-dialog/user-dialog.component';


@NgModule({
  declarations: [

    ChatComponent,
    UserDialogComponent,

  ],
  exports: [
  ChatComponent,
    UserDialogComponent
  ],
  imports: [
    DashboardRoutingModule,
    MatCardModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatInputModule,
    MatButtonModule,
    NgMultiSelectDropDownModule.forRoot(),
    MatSelectModule,
    MatOptionModule,
    SharedModule,
    NgForOf,
    NgIf,
    FormsModule,
    NgIf,
    NgForOf,
    NgSwitch,
    NgSwitchCase,
    NgSwitchDefault,
    MatIconModule,
  ]
})
export class DashboardModule {
}
