//path: coolapp/lib/features/authentication/screen/onboarding.dart
import 'package:coolapp/utils/constants/image_strings.dart';
import 'package:coolapp/utils/constants/text_strings.dart';
import 'package:flutter/material.dart';
import 'widgets/onboarding_page.dart';
import 'widgets/onboarding_skip.dart';
import 'widgets/onboarding_next_button.dart';
import 'widgets/onboarding_dot_navigation.dart';

class OnBoardingScreen extends StatelessWidget {
  const OnBoardingScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          //horizontal scroll view => use pageview, default scroll direction is horizontal
          PageView(
            scrollDirection: Axis.horizontal,
            children: [
              OnBoardingPage(
                image: TImages.onBoardingImage1,
                title: TTexts.onBoardingTitle1,
                subTitle: TTexts.onBoardingSubTitle1,
              ),
              OnBoardingPage(
                image: TImages.onBoardingImage2,
                title: TTexts.onBoardingTitle2,
                subTitle: TTexts.onBoardingSubTitle2,
              ),
              OnBoardingPage(
                image: TImages.onBoardingImage3,
                title: TTexts.onBoardingTitle3,
                subTitle: TTexts.onBoardingSubTitle3,
              ),
            ],
          ),
          //skip button
          const OnBoardingSkip(),
          //dot navigation smooth page indicator
          OnBoardingDotNavigation(),
          //circular button
          OnBoardingNextButton(),
        ],
      ),
    );
  }
}
