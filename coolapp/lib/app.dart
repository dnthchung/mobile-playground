//path: coolapp/lib/app.dart
// import 'package:coolapp/features/authentication/screen/onboarding/onboarding.dart';
import 'package:flutter/material.dart';
import 'package:coolapp/utils/theme/theme.dart';

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      themeMode: ThemeMode.system,
      theme: TAppTheme.lightTheme,
      darkTheme: TAppTheme.darkTheme,
      home: Screen1(),
    );
  }
}

//widget - screen 1 : screen show list of cars - navigate to screen 2 (show car details) on click
class Screen1 extends StatelessWidget {
  Screen1({super.key});

  final List<Map<String, dynamic>> cars = [
    {
      "carId": 124,
      "make": "Honda",
      "model": "Olivia",
      "year": 280,
      "price": 200.99,
      "mileage": 801
    },
    {
      "carId": 522,
      "make": "Toyota",
      "model": "Alex",
      "year": 122,
      "price": 200.99,
      "mileage": 815
    },
    {
      "carId": 72,
      "make": "Chevrolet",
      "model": "Michael",
      "year": 399,
      "price": 175.45,
      "mileage": 787
    },
    {
      "carId": 330,
      "make": "Kia",
      "model": "Olivia",
      "year": 964,
      "price": 200.99,
      "mileage": 665
    },
    {
      "carId": 5,
      "make": "BMW",
      "model": "Michael",
      "year": 354,
      "price": 600.5,
      "mileage": 778
    },
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Car List'),
      ),
      body: ListView.builder(
        itemCount: cars.length,
        itemBuilder: (context, index) {
          final car = cars[index];
          return ListTile(
            title: Text("${car['make']} ${car['model']}"),
            subtitle: Text("Year: ${car['year']} - Price: \$${car['price']}"),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  // builder: (context) => Screen2(car: car), => using constructor
                  builder: (context) => Screen2(), //using settings.arguments
                  settings: RouteSettings(
                    arguments: car,
                    name: "/details", //optional
                  ),
                ),
              );
            },
          );
        },
      ),
    );
  }
}

class Screen2 extends StatelessWidget {
  const Screen2({super.key});

  @override
  Widget build(BuildContext context) {
    // Lấy dữ liệu từ settings.arguments
    final Map<String, dynamic> car =
        ModalRoute.of(context)!.settings.arguments as Map<String, dynamic>;

    return Scaffold(
      appBar: AppBar(
        title: Text('Chi tiết xe'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Hãng: ${car['make']}', style: TextStyle(fontSize: 18)),
            SizedBox(height: 8),
            Text('Model: ${car['model']}', style: TextStyle(fontSize: 18)),
            SizedBox(height: 8),
            Text('Năm: ${car['year']}', style: TextStyle(fontSize: 18)),
            SizedBox(height: 8),
            Text('Giá: \$${car['price']}', style: TextStyle(fontSize: 18)),
            SizedBox(height: 8),
            Text('Số km: ${car['mileage']} miles',
                style: TextStyle(fontSize: 18)),
          ],
        ),
      ),
    );
  }
}

//widget - screen 2 : screen show car details => using constructor
// class Screen2 extends StatelessWidget {
//   final Map<String, dynamic> car;

//   const Screen2({super.key, required this.car});

//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       appBar: AppBar(
//         title: Text('${car['make']} ${car['model']}'),
//       ),
//       body: Padding(
//         padding: const EdgeInsets.all(16.0),
//         child: Column(
//           crossAxisAlignment: CrossAxisAlignment.start,
//           children: [
//             // Make
//             Text('Make: ${car['make']}', style: const TextStyle(fontSize: 18)),
//             const SizedBox(height: 8),
//             // Model
//             Text('Model: ${car['model']}',
//                 style: const TextStyle(fontSize: 18)),
//             const SizedBox(height: 8),
//             // Year
//             Text('Year: ${car['year']}', style: const TextStyle(fontSize: 18)),
//             const SizedBox(height: 8),
//             // Price
//             Text('Price: \$${car['price']}',
//                 style: const TextStyle(fontSize: 18)),
//             const SizedBox(height: 8),
//             // Mileage
//             Text('Mileage: ${car['mileage']} miles',
//                 style: const TextStyle(fontSize: 18)),
//           ],
//         ),
//       ),
//     );
//   }
// }
