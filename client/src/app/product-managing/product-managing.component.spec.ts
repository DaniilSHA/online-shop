import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductManagingComponent } from './product-managing.component';

describe('ProductManagingComponent', () => {
  let component: ProductManagingComponent;
  let fixture: ComponentFixture<ProductManagingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductManagingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductManagingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
