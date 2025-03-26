public interface IShoppingCart {
	public void addItem();
	public void deleteItem();
	public void updateShoppingCart();
	public void deleteShoppinCart();
	public void computeTotalAmount();
	public void computeItemAmount();
}

class ShoppingCartA implements IShoppingCart{
	public void addItem() {
		System.out.println("A카트 아이템 추가");
	}
	public void deleteItem() {
		System.out.println("A카트 아이템 제거");
	}
	public void updateShoppingCart() {
		System.out.println("A카트 업데이트");
	}
	public void deleteShoppinCart() {
		System.out.println("A카트 지우기");
	}
	public void computeTotalAmount() {
		System.out.println("A카트 총 금액");
	}
	public void computeItemAmount() {
		System.out.println("A카트 총 양");
	}
}

class ShoppingCartB implements IShoppingCart{
	public void addItem() {
		System.out.println("B카트 아이템 추가");
	}
	public void deleteItem() {
		System.out.println("B카트 아이템 제거");
	}
	public void updateShoppingCart() {
		System.out.println("B카트 업데이트");
	}
	public void deleteShoppinCart() {
		System.out.println("B카트 지우기");
	}
	public void computeTotalAmount() {
		System.out.println("B카트 총 금액");
	}
	public void computeItemAmount() {
		System.out.println("B카트 총 양");
	}
}

class ShoppingCartC implements IShoppingCart{
	public void addItem() {
		System.out.println("C카트 아이템 추가");
	}
	public void deleteItem() {
		System.out.println("C카트 아이템 제거");
	}
	public void updateShoppingCart() {
		System.out.println("C카트 업데이트");
	}
	public void deleteShoppinCart() {
		System.out.println("C카트 지우기");
	}
	public void computeTotalAmount() {
		System.out.println("C카트 총 금액");
	}
	public void computeItemAmount() {
		System.out.println("C카트 총 양");
	}
}