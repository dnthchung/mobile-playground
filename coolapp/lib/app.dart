//path: coolapp/lib/app.dart
import 'package:coolapp/features/authentication/screen/onboarding/onboarding.dart';
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
        home: const OnBoardingScreen());
  }
}
