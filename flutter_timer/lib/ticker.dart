class Ticker {
  const Ticker();

  Stream<int> tick({required int ticks}) {
    return Stream.periodic(const Duration(seconds: 1), (x) => ticks - x - 1)
        .take(ticks);
  }
  //Tham số ticks: Số nguyên bắt buộc, đại diện cho số giây mà bộ đếm sẽ đếm ngược.
  //return: Trả về một Stream<int> mà mỗi giây sẽ phát ra một giá trị nguyên, bắt đầu từ ticks-1 và giảm dần về 0.
}
