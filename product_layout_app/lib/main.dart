import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Product Layout Demo 123'),
    );
  }
}

class MyHomePage extends StatelessWidget {
  final String title;

  const MyHomePage({super.key, required this.title});

  @override
  Widget build(BuildContext context) {
    // Sample product data
    final List<Product> products = [
      Product(
        name: 'iPhone',
        description: 'A smartphone made by Apple',
        price: 999,
        image: 'iphone.png',
      ),
      Product(
        name: 'Laptop',
        description: 'A personal computer for productivity',
        price: 799,
        image: 'laptop.png',
      ),
      Product(
        name: 'Pixel',
        description: 'Google’s flagship smartphone',
        price: 899,
        image: 'pixel.png',
      ),
    ];

    return Scaffold(
      appBar: AppBar(
        title: Text(title),
      ),
      body: ListView.builder(
        itemCount: products.length,
        itemBuilder: (context, index) {
          final product = products[index];
          return ProductBox(
            name: product.name,
            description: product.description,
            price: product.price,
            image: product.image,
          );
        },
      ),
    );
  }
}

//model
class Product {
  final String name;
  final String description;
  final int price;
  final String image;

  Product({
    required this.name,
    required this.description,
    required this.price,
    required this.image,
  });
}

//
class ProductBox extends StatelessWidget {
  final String name;
  final String description;
  final int price;
  final String image;

  const ProductBox({
    super.key,
    required this.name,
    required this.description,
    required this.price,
    required this.image,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(2),
      height: 120,
      child: Card(
        child: Row(
          // mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Image.asset("assets/appimages/$image", width: 100, height: 100),
            Expanded(
              child: Container(
                padding: const EdgeInsets.all(5),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Text(
                      name,
                      style: const TextStyle(
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Text(description),
                    Text("Price: \$$price"),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
