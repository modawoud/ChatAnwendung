import { Component, EventEmitter, Inject, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { User } from '../../../../shared/model/user';
import { Userservice } from '../../../../shared/services/userservice';

@Component({
  selector: 'app-user-dialog',
  templateUrl: './user-dialog.component.html',
  styleUrls: ['./user-dialog.component.scss']
})
export class UserDialogComponent implements OnInit {
  users: User[] = [];
  @Output() userSelected: EventEmitter<User> = new EventEmitter<User>();

  constructor(
    public dialogRef: MatDialogRef<UserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: Userservice
  ) {}

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe({
      next: (res: any) => {
        this.users = res.data;
      },
      error: (err: any) => console.log(err)
    });
  }

  onSelect(user: User): void {
    this.userSelected.emit(user);
    this.dialogRef.close();
  }

  onClose(): void {
    this.dialogRef.close();
  }
}
