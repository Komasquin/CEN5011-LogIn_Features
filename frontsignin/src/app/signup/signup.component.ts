import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './signup.component.html'
})
export class SignupComponent {
  form = {
    username: '',
    email: '',
    role: 'user',
    logInMethod: 'r',
    password: '',
    password2: '',
    password3: '',
    password4: ''
  };
  constructor(private http: HttpClient, private router: Router, private auth: AuthService) {}

  onSubmit() {
    this.http.post('http://localhost:8080/api/signup', this.form).subscribe({
      next: () => {
        alert('Signup successful!');
        this.router.navigate(['/login']);
      },
      error: () => {
        alert('Signup failed.');
      }
    });
  }

  onLogin() {
    this.router.navigate(['/login']);
    }
}
