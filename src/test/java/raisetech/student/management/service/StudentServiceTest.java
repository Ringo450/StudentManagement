package raisetech.student.management.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;

  @Test
  void 受講生詳細一覧検索＿リポジトリとコンバーターの処理が適切に呼び出せていること() {//メソッドや変数名には、日本語使える！
    // Mock化　Mockito Stub
    //事前準備
    StudentService sut = new StudentService(repository,
        converter);//Testするときに使う、sut＝system under test 正常に動作していることを表す。被試験対象
    List<StudentDetail> expected = new ArrayList<>();

    //実行
    List<StudentDetail> actual = sut.searchStudentList();

    //検証・・・動作を確認するだけなら、検証で終わる。＝シンプルVer.
    Assertions.assertEquals(expected, actual);

    //後処理
    //ここでDBをもとに戻す。
  }
}