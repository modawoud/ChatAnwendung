import {User} from "./user";

export interface Chat {
  id: string;
  content: string;
  type: string;
  user: User;
  chat: Chat;
  updatedAt: Date;

}
