import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  username = '';
  enteredPassword = '';
  step = 1;
  error = '';
  loggingIn = false;
  form = { username: '', password: '' };

  constructor(private http: HttpClient, private router: Router, private auth: AuthService) {}

  onSubmit() {
    const payload = {
      username: this.username,
      enteredPassword: this.enteredPassword,
      step: this.step
    };
  
    this.loggingIn = true;
  
    this.error = 'Username or password are incorrect. Please try again.';
  
    this.http.post('http://localhost:8080/api/login', payload, { responseType: 'text' }).subscribe({
      next: (res) => {
        if (res.startsWith('PROCEED_TO_STEP')) {
          const nextStep = parseInt(res.split('_').pop() || '', 10);
          if (!isNaN(nextStep)) {
            this.step = nextStep;
            this.enteredPassword = '';
          }
        } else {
          const user = JSON.parse(res);
          localStorage.setItem('user', JSON.stringify(user));
          this.router.navigate(['/dashboard']);
          this.error = ''; 
        }
        this.loggingIn = false;
      },
      error: (err) => {
        this.loggingIn = false;
      }
    });
  }
  

  onSignup() {
    this.router.navigate(['/signup']);
  }
}

