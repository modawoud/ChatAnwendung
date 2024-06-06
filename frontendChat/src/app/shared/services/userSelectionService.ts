import {EventEmitter, Injectable} from '@angular/core';
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class UserSelectionService {

  private selectedUsers: User[] = [];
  public userAdded: EventEmitter<User> = new EventEmitter<User>();

  getUsers(): User[] {
    return this.selectedUsers;
  }

  addUser(user: User): void {
    this.selectedUsers.push(user);
    this.userAdded.emit(user);
  }

  clearUsers(): void {
    this.selectedUsers = [];
  }
}
