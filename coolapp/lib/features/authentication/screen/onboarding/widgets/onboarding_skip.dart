import 'package:coolapp/utils/constants/sizes.dart';
import 'package:coolapp/utils/device/device_utility.dart';
import 'package:flutter/material.dart';

class OnBoardingSkip extends StatelessWidget {
  const OnBoardingSkip({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Positioned(
        top: TDeviceUtils.getAppBarHeight(),
        right: TSizes.defaultSpace,
        child: TextButton(
          onPressed: () {
            // Xử lý khi nhấn nút
          },
          style: TextButton.styleFrom(
            foregroundColor: Colors.black, // Màu chữ
            backgroundColor: const Color(0xFFB8D8D8),
            padding: const EdgeInsets.symmetric(
                horizontal: 20, vertical: 10), // Padding
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(8), // Bo góc
            ),
          ),
          child: const Text("Skip"),
        ));
  }
}
