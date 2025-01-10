import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Cool App',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Cool App'),
          backgroundColor: Colors.green,
        ),
        body: Container(),
      ),
    );
  }
}
