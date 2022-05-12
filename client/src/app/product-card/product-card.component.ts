import {AfterContentChecked, Component, Input, OnInit, SecurityContext} from '@angular/core';
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css'],
})
export class ProductCardComponent implements OnInit, AfterContentChecked {

  @Input() title: string = '';
  @Input() imgInBase64String: string = '';
  @Input() price: number = 0;
  imagePath: string | null = '';

  constructor(private sanitizer: DomSanitizer) {
  }

  ngOnInit(): void {
  }

  ngAfterContentChecked(): void {
    this.imagePath = this.sanitizer.sanitize(
      SecurityContext.RESOURCE_URL,
      this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + this.imgInBase64String)
    );
  }

}
