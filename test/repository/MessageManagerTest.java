package repository;

public class MessageManagerTest {
//	private static final String SAMPLE_TEXT = "オレと友達になってください！";
//	private static final String SAMPLE_NAME = "長谷";
//	private static final String SAMPLE_NAME2 = "藤宮";
//	private static final int SAMPLE_HOUR = 16;
//	private static final int SAMPLE_MINUTE = 53;
//
//	@Before
//	public void データベースの初期化() {
//		TestDataBase db = new TestDataBase();
//		db.setTestData("./test/db/testdata01.xls");
//	}
//
//	@Test
//	public void メッセージを既読にできる_1(){
//		MessageManager mm=new MessageManager();
//		mm.updateReadFlag(2,SAMPLE_NAME);
//
//		LinkedList<Message> messages = mm.getList();
//		assertThat(messages.get(0).isReadFlag(),is(false));
//		assertThat(messages.get(1).isReadFlag(),is(true));
//	}
//
//	@Test
//	public void メッセージを既読にできる_2(){
//		MessageManager mm=new MessageManager();
//		mm.updateReadFlag(2,SAMPLE_NAME2);
//
//		LinkedList<Message> messages = mm.getList();
//		assertThat(messages.get(0).isReadFlag(),is(true));
//		assertThat(messages.get(1).isReadFlag(),is(false));
//	}
//
//	@Test
//	public void メッセージを削除できる(){
//		MessageManager mm=new MessageManager();
//		mm.delete(1,SAMPLE_NAME);
//
//		LinkedList<Message> messages = mm.getList();
//		assertThat(messages.size(),is(1));
//
//		mm.delete(2,SAMPLE_NAME);
//		messages = mm.getList();
//		assertThat(messages.size(),is(1));
//	}
//
//	@Test
//	public void メッセージをデータベースから取得できる() {
//		MessageManager mm = new MessageManager();
//		LinkedList<Message> messages = mm.getList();
//
//		assertThat(messages.size(), is(2));
//	}
//
//	@Test
//	public void データベースにメッセージを保存できる() {
//		Message message = new Message();
//		message.setText(SAMPLE_TEXT);
//		message.setName(SAMPLE_NAME);
//		message.setHour(SAMPLE_HOUR);
//		message.setMinute(SAMPLE_MINUTE);
//
//		MessageManager mm = new MessageManager();
//		mm.insert(message);
//
//		LinkedList<Message> messages = mm.getList();
//		assertThat(messages.size(), is(3));
//		assertMessage(messages.get(2), SAMPLE_TEXT, SAMPLE_NAME, SAMPLE_HOUR, SAMPLE_MINUTE);
//	}
//
//	private void assertMessage(Message message, String text, String name, int hour, int minute) {
//		assertThat(message.getText(), is(text));
//		assertThat(message.getName(), is(name));
//		assertThat(message.getHour(), is(hour));
//		assertThat(message.getMinute(), is(minute));
//	}
}
