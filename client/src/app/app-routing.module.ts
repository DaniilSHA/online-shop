import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AuthLayoutComponent} from "./shared/layouts/auth-layout/auth-layout.component";
import {SiteLayoutComponent} from "./shared/layouts/site-layout/site-layout.component";
import {LoginPageComponent} from "./login-page/login-page.component";
import {RegisterPageComponent} from "./register-page/register-page.component";
import {AuthGuard} from "./shared/classes/auth.guard";
import {ProductManagingComponent} from "./product-managing/product-managing.component";

const routes: Routes = [
  {
    path: "", component: AuthLayoutComponent, children: [
      { path: "", redirectTo: "/login", pathMatch: "full" },
      { path: "login", component: LoginPageComponent },
      { path: "register", component: RegisterPageComponent }
    ]
  },
  {
    path: "overview", component: SiteLayoutComponent, canActivate: [AuthGuard], children: [
      { path: "product-managing", component: ProductManagingComponent},
    ]
  }
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {

}
