import {Injectable, OnInit} from '@angular/core';
import {Product, Query} from "../interfaces";
import {Apollo, gql} from 'apollo-angular';
import {map, Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class ProductService implements OnInit{

  private products!: Observable<[Product]>

  constructor(private apollo: Apollo) {
  }

  ngOnInit() {
  }

  getProductList(): Observable<any> {
    this.products = this.apollo.watchQuery<Query>({
      query : gql`
        query products {
          products {
            id
            title
            price
          }
        }`
    }).valueChanges.pipe(map(result => result.data.products));
    return this.products;
  }
}
