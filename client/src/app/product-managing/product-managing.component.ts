import { Component, OnInit } from '@angular/core';
import {ProductService} from "../shared/services/product.service";
import {Product} from "../shared/interfaces";

@Component({
  selector: 'app-product-managing',
  templateUrl: './product-managing.component.html',
  styleUrls: ['./product-managing.component.css']
})
export class ProductManagingComponent implements OnInit {

  products!: [Product];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.reloadProducts();
  }

  private reloadProducts() : void {
    this.productService.getProductList().subscribe(data => this.products = data);
  }
}
