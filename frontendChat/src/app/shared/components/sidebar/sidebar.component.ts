import { Component, Input, OnInit } from '@angular/core';
import { RouteItem } from "../../common/route-item";
import { Router } from "@angular/router";
import { ChatService } from "../../services/chat-service";
import { MatDialog } from "@angular/material/dialog";
import { UserDialogComponent } from "../../../dashboard/components/User/user-dialog/user-dialog.component";
import { User } from '../../model/user';
import {UserSelectionService} from "../../services/userSelectionService";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  @Input() public sideBarOpen = true;
  public routeItems: RouteItem[] = new Array<RouteItem>();
  private data: any[] = [];

  selectedUsers: User[] = [];

  constructor(private router: Router,
              private chatService: ChatService,
              private dialog: MatDialog,
              private userSelectionService: UserSelectionService ) {
  }

  showAllUsers(): void {
    const dialogRef = this.dialog.open(UserDialogComponent, {
      width: '400px',
      data: {}
    });

    dialogRef.componentInstance.userSelected.subscribe((user: User) => {
      this.userSelectionService.addUser(user);
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  ngOnInit(): void {
    this.selectedUsers = this.userSelectionService.getUsers();
    this.userSelectionService.userAdded.subscribe((user: User) => {
      this.selectedUsers = this.userSelectionService.getUsers();
      this.openChat(user);
    });

    this.chatService.getAllChats().subscribe({
      next: (res: any) => {
        this.data = res.data;
      },
      error: (err: any) => console.log(err)
    });
  }

  openChat(user: User) {
    console.log('Chat mit Benutzer geöffnet:', user);
    this.router.navigate(['/chats', user.id]);
  }

  addNewRaum() {
    this.router.navigate(['/chats']);
  }

  updateRaum(item: any) {
    const id = this.data.filter((room: any) => room.name === item.name)[0].id;
    this.router.navigate(['/rooms/edit/' + id]).then(() => {});
  }
  openchat() {
    this.router.navigate(['/chats']);
  }
}
