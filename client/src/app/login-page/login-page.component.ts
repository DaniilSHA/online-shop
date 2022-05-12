import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../shared/services/auth.service";
import {Subscription} from "rxjs";
import {ActivatedRoute, Params, Router} from "@angular/router";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit, OnDestroy {

  // @ts-ignore
  form: FormGroup

  // @ts-ignore
  aSub: Subscription

  constructor(private auth: AuthService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required, Validators.minLength(6)])
    })
    this.route.queryParams.subscribe((params: Params) => {
      if (params['registered']) {
        alert('now you can enter in system use your data')
      } else if (params['accessDenied']) {
        alert('at first, you should make authorize')
      } else if (params['wrongLoginAndPassword']) {
        alert('login / password incorrect')
      }
    })
  }

  onSubmit() {
    this.form.disable();
    this.aSub = this.auth.login(this.form.value).subscribe(
      () => {
        this.router.navigate(["/overview"])
        localStorage.setItem('email', this.form.value.email);
      }, //redirect to main
      error => {
        console.warn(error)
        this.router.navigate(["/login"], {queryParams : {wrongLoginAndPassword: true}})
        this.form.enable();
      }
    )
  }

  ngOnDestroy(): void {
    if (this.aSub) {
      this.aSub.unsubscribe()
    }
  }
}
