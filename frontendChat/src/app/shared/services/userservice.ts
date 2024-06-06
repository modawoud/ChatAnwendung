import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {Chat} from "../model/chat";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class Userservice {

  private apiUrl = environment.SERVER_URL + '/api/v1/web/users';

  constructor(private http: HttpClient) {
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }


}
