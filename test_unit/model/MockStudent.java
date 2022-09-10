package model;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import _data.Const;


public class MockStudent {

//	@Mock
	private Pc pc;
	
//	@Mock
//	private WaitingManager<Student> waitingManager;
//
//	@InjectMocks
//	private Student student;

//	private ArrayList testList;
//	private Pc pc1;
//	private WaitingManager<Student> waitingManager1;
//	private Student student1;

	@BeforeEach
	public void setUp() {
//		testList=mock(ArrayList.class);
//		waitingManager=mock(WaitingManager.class);
		pc = mock(Pc.class,withSettings().useConstructor(Const.IPADDRESS_1,Const.HOSTNAME_1,true));
//		MockitoAnnotations.openMocks(this);
		
//		pc1 = new Pc(Const.IPADDRESS_1,Const.HOSTNAME_1,true);
//		waitingManager1 = new WaitingManager<Student>();
//		student1 = new Student(pc1,waitingManager1);
	}
	@Test
	public void MockTest() {
		assertThat(pc.getHostName()).isNull();

		//	    List mockList = mock(ArrayList.class);
//
//	    mockList.add("apple");
//	    assertEquals("apple", mockList.get(0));
	    
//		ArrayList testList=mock(ArrayList.class);
//		testList.add("1");
//		assertThat(testList.get(0)).isNull();
	    
//		waitingManager.regist(student1);
//		assertThat(waitingManager.getPriority(student1)).isNull();
		
//		Pc dummyPc = new Pc(Const.IPADDRESS_1,Const.HOSTNAME_1,true);
//        WaitingManager waitingManagerMock = mock(WaitingManager.class);
        // インスタンスにスタブメソッドを定義
        // ある引数が渡された場合の戻り値を設定する
        // 本来の実装だと6を返すはずだけど、テストのため4を返すことにする
//        doReturn(1).when(waitingManagerMock).regist(null);
//         
//        // ちゃんと定義通り値を返すか確認
//        // 上で定義した通り4を返すはず
//        System.out.println(waitingManagerMock.regist("a")));
//         
//        // 定義されていない引数でメソッドを呼ぶと0が返ってくる
//        // 戻り値がObjectだとnulが返される
//        System.out.println(mockSample.saveLength("not defined"));
//         
//        // メソッドが指定回数呼び出されていることを確認
//        // 指定した回数呼ばれていた場合は何も出力しない
//        verify(mockSample, times(1)).saveLength("string");
//         
//        // もし指定通りの回数呼ばれていないと例外をthrowする
//        verify(mockSample, times(2)).saveLength("string");
	}


}