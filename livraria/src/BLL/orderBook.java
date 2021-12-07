package BLL;

public class orderBook {

	private int id;
	private order order;
	private book book;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public order getOrder() {
		return order;
	}
	public void setOrder(order order) {
		this.order = order;
	}
	public book getBook() {
		return book;
	}
	public void setBook(book book) {
		this.book = book;
	}
	
}
