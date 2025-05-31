package raisetech.student.management.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import raisetech.student.management.domain.StudentDetail;

class StudentServiceTest {

  @Test
  void 受講生詳細一覧検索＿リポジトリとコンバーターの処理が適切に呼び出せていること() {//メソッドや変数名には、日本語使える！
    // Mock化　Mockito Stub
    //事前準備
    StudentService sut = new StudentService();//Testするときに使う、sut＝system under test 正常に動作していることを表す。被試験対象
    List<StudentDetail> expected = new ArrayList<>();

    //実行
    List<StudentDetail> actual = sut.searchStudentList();

    //検証・・・動作を確認するだけなら、検証で終わる。＝シンプルVer.
    Assertions.assertEquals(expected, actual);

    //後処理
    //ここでDBをもとに戻す。
  }
}