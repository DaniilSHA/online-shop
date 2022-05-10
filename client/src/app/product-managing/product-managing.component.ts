import {Component, OnInit, SecurityContext} from '@angular/core';
import {ProductService} from "../shared/services/product.service";
import {Product} from "../shared/interfaces";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";

@Component({
  selector: 'app-product-managing',
  templateUrl: './product-managing.component.html',
  styleUrls: ['./product-managing.component.css']
})
export class ProductManagingComponent implements OnInit {

  products!: [Product];
  imagePath!: string;

  constructor(private productService: ProductService,
              private sanitizer : DomSanitizer) {}

  ngOnInit(): void {
    this.reloadProducts();
  }

  private reloadProducts() : void {
    this.productService.getProductList().subscribe(data => this.products = data)

    // // @ts-ignore
    // setTimeout(()=>console.log(this.products[8].img), 1000)
    // // @ts-ignore
    // setTimeout(()=>console.log(this.products[8].id), 1000)


    setTimeout(()=> {
      // @ts-ignore
      this.imagePath = this.sanitizer.sanitize(SecurityContext.RESOURCE_URL, this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + this.products[8].img));
      // @ts-ignore
      // console.log(this.products[8].img)
      console.log(this.imagePath)
    }, 1000)


  }
}
