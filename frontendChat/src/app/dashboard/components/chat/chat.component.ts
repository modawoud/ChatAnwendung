import { Component } from '@angular/core';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent {
  messages: string[] = [];
  newMessage: string = '';

  sendMessage(): void {
    if (this.newMessage.trim().length > 0) {
      this.messages.push(this.newMessage);
      this.newMessage = '';
    }
  }

}


