import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-snackbar',
  templateUrl: './app/snackbar/snackbar.component.html',
  styleUrls: ['./app/snackbar/snackbar.component.css'],
})
export class SnackbarComponent {
  private message: string;
  private isError: boolean;
  private isVisible: boolean = false;

  showSnackbar(_message: string, _error: boolean) {
    this.message = _message;
    this.isError = _error;

    setTimeout(() => {
      this.isVisible = true;
    }, 500);
  }

  hideSnackbar() {
    this.isVisible = false;
  }
}
