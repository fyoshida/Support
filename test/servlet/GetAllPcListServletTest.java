package servlet;

import common.TestServlet;

public class GetAllPcListServletTest extends TestServlet {

//	private static final String SERVLET_NAME = "MessagesServlet";
//
//	private static final int SAMPLE_MID1 = 1;
//	private static final String SAMPLE_NAME1 = "長谷";
//	private static final String SAMPLE_TEXT1 = "オレと友達になってください！";
//
//	private static final int SAMPLE_MID2 = 2;
//	private static final String SAMPLE_NAME2 = "藤宮";
//	private static final String SAMPLE_TEXT2 = "無理。";
//
//	@Before
//	public void データベースの初期化(){
//		TestDataBase db=new TestDataBase();
//		db.setTestData("./test/db/testdata01.xls");
//	}
//
//	@Test
//	public void servletにPOSTメソッドでアクセスできる() throws Exception {
//		postMessage();
//		assertNotNull(webResponse);
//	}
//
//	@Test
//	public void servletにGETメソッドでアクセスできる() throws Exception {
//		getMessages();
//		assertNotNull(webResponse);
//	}
//
//	@Test
//	public void servletにPUTメソッドでアクセスできる() throws Exception {
//		putMessages();
//		assertNotNull(webResponse);
//	}
//
//	@Test
//	public void servletにDELETEメソッドでアクセスできる() throws Exception {
//		deleteMessages();
//		assertNotNull(webResponse);
//	}
//
//	@Test
//	public void メッセージを既読にできる_1() throws Exception{
//		putMessages(2,SAMPLE_NAME1);
//
//		MessageManager mm=new MessageManager();
//		LinkedList<Message>messages=mm.getList();
//		assertThat(messages.get(0).isReadFlag(),is(false));
//		assertThat(messages.get(1).isReadFlag(),is(true));
//	}
//
//	@Test
//	public void メッセージを既読にできる_2() throws Exception{
//		putMessages(2,SAMPLE_NAME2);
//
//		startServer();
//		callServlet();
//
//		MessageManager mm=new MessageManager();
//		LinkedList<Message>messages=mm.getList();
//		assertThat(messages.get(0).isReadFlag(),is(true));
//		assertThat(messages.get(1).isReadFlag(),is(false));
//	}
//
//	@Test
//	public void メッセージを削除できる() throws Exception{
//		MessageManager mm=new MessageManager();
//
//		deleteMessages(SAMPLE_MID1,SAMPLE_NAME1);
//		assertThat(mm.getList().size(), is(1));
//
//		deleteMessages(SAMPLE_MID2,SAMPLE_NAME1);
//		assertThat(mm.getList().size(), is(1));
//	}
//
//	@Test
//	public void 投稿したメッセージをデータベースに保存できる() throws MalformedURLException, IOException, ServletException, Exception {
//		postMessage(SAMPLE_TEXT1,SAMPLE_NAME1);
//
//		MessageManager mm=new MessageManager();
//		LinkedList<Message> messages = mm.getList();
//
//		assertThat(messages.size(), is(3));
//		assertMessage(messages.get(2), SAMPLE_TEXT1, SAMPLE_NAME1);
//	}
//
//	@Test
//	public void データベースに保存したメッセージを取得できる() throws MalformedURLException, IOException, ServletException, Exception  {
//		getMessages();
//
//		LinkedList<Message> responseMessages = readMessagesFromResponse();
//		assertThat(responseMessages.size(), is(2));
//		assertMessage(responseMessages.get(0),SAMPLE_TEXT1,SAMPLE_NAME1);
//		assertMessage(responseMessages.get(1), SAMPLE_TEXT2,SAMPLE_NAME2);
//	}
//
//	@Test
//	public void 投稿したメッセージを取得できる() throws Exception {
//		postMessage(SAMPLE_TEXT1,SAMPLE_NAME1);
//		getMessages();
//
//		LinkedList<Message> responseMessages = readMessagesFromResponse();
//		assertThat(responseMessages.size(), is(3));
//		assertMessage(responseMessages.get(2),SAMPLE_TEXT1,SAMPLE_NAME1);
//	}
//
//	@Test
//	public void 投稿したメッセージを取得できる_２回() throws Exception {
//		postMessage(SAMPLE_TEXT1,SAMPLE_NAME1);
//		postMessage(SAMPLE_TEXT2,SAMPLE_NAME2);
//		getMessages();
//
//		LinkedList<Message> responseMessages = readMessagesFromResponse();
//		assertThat(responseMessages.size(), is(4));
//		assertMessage(responseMessages.get(2),SAMPLE_TEXT1,SAMPLE_NAME1);
//		assertMessage(responseMessages.get(3), SAMPLE_TEXT2,SAMPLE_NAME2);
//	}
//
//	private LinkedList<Message> readMessagesFromResponse() throws IOException {
//		String response = webResponse.getText();
//		Gson gson = new Gson();
//		Type type = new TypeToken<LinkedList<Message>>() {
//		}.getType();
//		LinkedList<Message> responseMessages = gson.fromJson(response, type);
//		return responseMessages;
//	}
//
//	private void getMessages() throws MalformedURLException, IOException, ServletException, Exception {
//		setServletName(SERVLET_NAME);
//		setMethodType(MethodType.GET);
//
//		startServer();
//		callServlet();
//	}
//
//	private void postMessage() throws MalformedURLException, IOException, ServletException, Exception {
//		setServletName(SERVLET_NAME);
//		setMethodType(MethodType.POST);
//
//		startServer();
//		callServlet();
//	}
//
//	private void putMessages() throws MalformedURLException, IOException, ServletException, Exception {
//		setServletName(SERVLET_NAME);
//		setMethodType(MethodType.PUT);
//
//		startServer();
//		callServlet();
//	}
//
//	private void putMessages(int lastMid,String name) throws MalformedURLException, IOException, ServletException, Exception {
//		setServletName(SERVLET_NAME);
//		setMethodType(MethodType.PUT);
//		webRequest.setParameter("LastMID", ""+lastMid);
//		webRequest.setParameter("Name", name);
//
//		startServer();
//		callServlet();
//	}
//
//	private void deleteMessages() throws MalformedURLException, IOException, ServletException, Exception {
//		setServletName(SERVLET_NAME);
//		setMethodType(MethodType.DELETE);
//
//		startServer();
//		callServlet();
//	}
//
//	private void deleteMessages(int mid,String name) throws MalformedURLException, IOException, ServletException, Exception {
//		setServletName(SERVLET_NAME);
//		setMethodType(MethodType.DELETE);
//		webRequest.setParameter("MID", ""+mid);
//		webRequest.setParameter("Name", name);
//
//		startServer();
//		callServlet();
//	}
//
//	private void postMessage(String text, String name)
//			throws MalformedURLException, IOException, ServletException, Exception {
//		setServletName(SERVLET_NAME);
//		setMethodType(MethodType.POST);
//		webRequest.setParameter("Text", text);
//		webRequest.setParameter("Name", name);
//		startServer();
//		callServlet();
//	}
//
//	private void assertMessage(Message message, String text, String name) {
//		assertThat(message.getText(), is(text));
//		assertThat(message.getName(), is(name));
//		assertThat(message.getHour(),is(greaterThanOrEqualTo(0)));
//		assertThat(message.getMinute(),is(greaterThanOrEqualTo(0)));
//	}
//
//	@Ignore
//	@Test
//	public void 投稿したメッセージがsessionに保存される() throws Exception {
//		postMessage(SAMPLE_TEXT1, SAMPLE_NAME1);
//
//		LinkedList<Message> messages = (LinkedList<Message>) session.getAttribute("Messages");
//		assertThat(messages.size(), is(1));
//
//		Message message = messages.get(0);
//		assertThat(message.getText(), is(SAMPLE_TEXT1));
//		assertThat(message.getName(), is(SAMPLE_NAME1));
//	}
//
//	@Ignore
//	@Test
//	public void sessionに保存されたメッセージを読み込むことができる() throws Exception {
//		setServletName(SERVLET_NAME);
//		setMethodType(MethodType.GET);
//
//		startServer();
//
//		Message requestMessage = new Message();
//		requestMessage.setText(SAMPLE_TEXT1);
//		requestMessage.setName(SAMPLE_NAME1);
//		LinkedList<Message> requestMessages = new LinkedList<Message>();
//		requestMessages.add(requestMessage);
//		session.setAttribute("Messages", requestMessages);
//
//		callServlet();
//
//		LinkedList<Message> responseMessages = readMessagesFromResponse();
//
//		assertThat(responseMessages.size(), is(1));
//		Message responseMessage = responseMessages.get(0);
//		assertThat(responseMessage.getText(), is(SAMPLE_TEXT1));
//		assertThat(responseMessage.getName(), is(SAMPLE_NAME1));
//	}



}
