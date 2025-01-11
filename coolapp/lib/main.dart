import 'dart:math';

import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Cool App',
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Cool App'),
          backgroundColor: Colors.green,
        ),
        body: ListView.builder(
          itemCount: 20, // Define the number of items you want in the list
          itemBuilder: (context, index) {
            return Container(
              margin: const EdgeInsets.all(10), // Add spacing between items
              color: randomColor(),
              height: 150, // Set a fixed height for better appearance
              child: Center(
                child: Text(
                  'Item $index',
                  style: const TextStyle(
                    color: Colors.white,
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
            );
          },
        ),
      ),
    );
  }
}

Color randomColor() {
  return Color.fromARGB(
    255,
    Random().nextInt(256),
    Random().nextInt(256),
    Random().nextInt(256),
  );
}
