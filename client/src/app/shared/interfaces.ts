export interface User {
  email: string,
  password: string
}

export type Product = {
  id: number
  title: string,
  price: number
  categories : [number]
  img: string
}

export type Query = {
  products: [Product]
}

// export interface Category {
//   id: number,
//   title: string
// }
