import {Injectable} from "@angular/core";
import {User} from "../interfaces";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";
import {Router} from "@angular/router";

@Injectable({
  providedIn: "root"
})
export class AuthService {

  private token = null;
  private email = null

  constructor(private http: HttpClient, private router: Router) {
  }

  login(user: User): Observable<{ token: string }> {
    return this.http.post<{ token: string }>('/api/auth/login', user)
      .pipe(
        tap(
          ({token}) => {
            localStorage.setItem("auth-token", token)
            this.setToken(token)
          }
        )
      )
  }

  isAdmin() {
    // @ts-ignore
    return this.parseJwt(this.token)['roles'].includes('ADMIN_ROLE');
  }

  setToken(token: string) {
    // @ts-ignore
    this.token = token
  }

  getToken(): string {
    // @ts-ignore
    return this.token
  }

  isAuthenticated(): boolean {
    return !!this.token
  }

  logout() {
    // @ts-ignore
    this.setToken(null)
    localStorage.clear()
    this.router.navigate(["/"])
  }

  register(user: User): Observable<User> {
    return this.http.post<User>('/api/auth/register', user)
  }

  setEmail(email: string){
    // @ts-ignore
    this.email=email;
  }

  getEmail(){
    return this.email;
  }



  parseJwt (token: string) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
  };

}
