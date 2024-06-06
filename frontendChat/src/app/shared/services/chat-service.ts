import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {Chat} from "../model/chat";

@Injectable({
  providedIn: 'root',
})

export class ChatService {

  private apiUrl = environment.SERVER_URL + 'chats';

  constructor(private http: HttpClient) {
  }

  getAllChats(): Observable<Chat[]> {
    return this.http.get<Chat[]>(this.apiUrl);
  }
}
