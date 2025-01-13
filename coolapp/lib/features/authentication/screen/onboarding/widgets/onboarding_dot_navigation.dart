import 'package:coolapp/utils/constants/colors.dart';
import 'package:coolapp/utils/constants/sizes.dart';
import 'package:coolapp/utils/device/device_utility.dart';
import 'package:coolapp/utils/helpers/helper_functions.dart';
import 'package:flutter/material.dart';
import 'package:smooth_page_indicator/smooth_page_indicator.dart';

class OnBoardingDotNavigation extends StatelessWidget {
  const OnBoardingDotNavigation({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    final dark = THelperFunctions.isDarkMode(context);

    return Positioned(
      bottom: TDeviceUtils.getBottomNavigationBarHeight() + 25,
      left: TSizes.defaultSpace,
      child: SmoothPageIndicator(
        controller: PageController(),
        count: 3,
        effect: ExpandingDotsEffect(
          // dotColor: Colors.grey,
          activeDotColor: dark ? TColors.white : TColors.black,
          dotHeight: 6,
          // dotWidth: 8,
          // spacing: 4,
          // expansionFactor: 4,
        ),
      ),
    );
  }
}
