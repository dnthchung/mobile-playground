import 'package:flutter/material.dart';

class WelcomePage extends StatelessWidget {
  const WelcomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 20.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            const SizedBox(height: 50), // Khoảng cách trên cùng
            // ==== Image ====
            Center(
              child: Image.asset(
                "assets/images/image1.png",
                height: 300, // Điều chỉnh chiều cao hình ảnh
                width: 300, // Điều chỉnh chiều rộng hình ảnh (tuỳ chọn)
                fit: BoxFit.contain, // Đảm bảo hình ảnh không bị cắt
              ),
            ),
            const SizedBox(height: 20), // Khoảng cách giữa hình ảnh và tiêu đề
            // ==== Text ====
            const Text(
              "Discover Your \nDream Job here",
              textAlign: TextAlign.center,
              style: TextStyle(
                fontSize: 35,
                fontWeight: FontWeight.bold,
                color: Color.fromARGB(255, 27, 37, 129),
              ),
            ),
            const SizedBox(height: 20), // Khoảng cách giữa hình ảnh và tiêu đề
            // ==== Description ====
            const Text(
              "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
              "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
              textAlign: TextAlign.center,
              style: TextStyle(
                fontSize: 14,
                color: Color.fromARGB(255, 27, 37, 129),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
