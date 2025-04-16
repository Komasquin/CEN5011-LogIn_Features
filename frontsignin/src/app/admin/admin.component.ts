import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service'; 
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.component.html'
})
export class AdminComponent implements OnInit {
  users: any[] = [];
  newPasswords: { [id: number]: { password?: string, password2?: string, password3?: string, password4?: string } } = {};
  form: any = {};

  constructor(private http: HttpClient, private auth: AuthService, private router: Router) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/users').subscribe(data => {
      this.users = data;
      for (const user of this.users) {
        this.newPasswords[user.id] = {
          password: '',
          password2: '',
          password3: '',
          password4: ''
        };
      }
    });
  }

  updatePassword(userId: number): void {
    const pwdData = this.newPasswords[userId] || {};
    const payload = {
      password: pwdData.password || '',
      password2: pwdData.password2 || '',
      password3: pwdData.password3 || '',
      password4: pwdData.password4 || ''
    };
  
    this.http.put(`http://localhost:8080/api/users/${userId}/passwords`, payload, {
      responseType: 'text'
    }).subscribe({
      next: msg => alert(msg),
      error: () => alert('Password failed to update, run debug mode')
    });
  }

  toDashboard() {
    this.router.navigate(['/dashboard']);
    }
  
  onSignOut() {
    this.router.navigate(['/login']);
    }
}
