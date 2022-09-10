package model;

import static org.mockito.Mockito.*;

public class MockSample {

	public static void main(String[] args) {
		// モックを作成
		ServiceSample mockSample = mock(ServiceSample.class);
		// インスタンスにスタブメソッドを定義
		// ある引数が渡された場合の戻り値を設定する
		// 本来の実装だと6を返すはずだけど、テストのため4を返すことにする
//		doReturn(4).when(mockSample).saveLength("string");
//
//		// ちゃんと定義通り値を返すか確認
//		// 上で定義した通り4を返すはず
//		System.out.println(mockSample.saveLength("string"));
//
//		// 定義されていない引数でメソッドを呼ぶと0が返ってくる
//		// 戻り値がObjectだとnulが返される
//		System.out.println(mockSample.saveLength("not defined"));
//
//		// メソッドが指定回数呼び出されていることを確認
//		// 指定した回数呼ばれていた場合は何も出力しない
//		verify(mockSample, times(1)).saveLength("string");
//
//		// もし指定通りの回数呼ばれていないと例外をthrowする
//		verify(mockSample, times(2)).saveLength("string");

	}
}
