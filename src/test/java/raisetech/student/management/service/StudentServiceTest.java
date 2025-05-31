package raisetech.student.management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;

  private StudentService sut;

  @BeforeEach
//Testメソッドのつど。
  void before() {
    sut = new StudentService(repository, converter);
  }

  @Test
  void 受講生詳細一覧検索＿リポジトリとコンバーターの処理が適切に呼び出せていること() {//メソッドや変数名には、日本語使える！
    // Mock化　Mockito Stub
    //事前準備
    //Testするときに使う、sut＝system under test 正常に動作していることを表す。被試験対象
    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();

    when(repository.search()).thenReturn(studentList);//Mockitoは、返り値を疑似的に操作できる。
    when(repository.searchStudentCourseList()).thenReturn(studentCourseList);

    //実行
    List<StudentDetail> actual = sut.searchStudentList();

    //検証
    verify(repository, times(1)).search();//repositoryクラスのsearch1回呼び出してますよってこと。
    verify(repository, times(1)).search();//searchStudentCourseListも1回呼び出してますよ。
    verify(converter, times(1)).convertStudentDetails(studentList,
        studentCourseList);//convertクラスのconvertメソッドも1回呼び出してますよ。
    //後処理
    //ここでDBをもとに戻す。
  }

  @Test
  void 受講生詳細検索_リポジトリが正しく呼ばれ_正しい受講生詳細が返ること() {
    // --- 準備 ---
    String studentId = "100";

    // Mock用のStudentとStudentCourse作成
    Student mockStudent = new Student();
    mockStudent.setId(studentId); // getId()が動作するように

    List<StudentCourse> mockCourseList = List.of(new StudentCourse());

    // モックの戻り値設定
    when(repository.searchStudent(studentId)).thenReturn(mockStudent);
    when(repository.searchStudentCourse(studentId)).thenReturn(mockCourseList);

    // --- 実行 ---
    StudentDetail result = sut.searchStudent(studentId);

    // --- 検証 ---
    verify(repository, times(1)).searchStudent(studentId);
    verify(repository, times(1)).searchStudentCourse(studentId);

    // 戻り値が正しいか（identity比較OK）
    assertEquals(mockStudent, result.getStudent());
    assertEquals(mockCourseList, result.getStudentCourseList());

    //🔍 解説ポイント
    //mockStudent.setId(...) をちゃんとやらないと、2つ目の searchStudentCourse(student.getId()) が null で動かなくなる可能性がある
    //StudentDetail の中身（student と courseList）がちゃんとMockの値と一致してるかを assertEquals でチェック
    //verify(...) でメソッドが1回だけちゃんと呼ばれてるか確認
  }
}