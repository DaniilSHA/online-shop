import {Component, Inject, OnInit} from '@angular/core';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css'],
  inputs: ['title', 'price', 'img']
})
export class ProductCardComponent implements OnInit {

  title: string = '';
  img: string = '';
  price: number = 0;

  constructor() { }

  ngOnInit(): void {
  }

}
