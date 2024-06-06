import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ChatComponent} from "./components/chat/chat.component";
import {UserDialogComponent} from "./components/User/user-dialog/user-dialog.component";

const routes: Routes = [
  {path: 'chats/:id', component: ChatComponent, pathMatch: 'full'},
  {path: 'users', component: UserDialogComponent, pathMatch: 'full'},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule {
}
