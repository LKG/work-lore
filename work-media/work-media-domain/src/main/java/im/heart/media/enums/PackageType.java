package im.heart.media.enums;

public enum PackageType {
	price(1, "price", "信息价"), 
	supplement_price(2,"supplementPrice", "副刊"), 
	price_rule(5,"priceRule", "规则库"),
	supplement_rule(6,"supplementRule", "副刊规则库"),
	base(9,"base", "基础库"),
	city(0,"city", "城市库");

	public String code;
	public int value;
	public final String info;
	private PackageType(int value, String code, String info) {
		this.code = code;
		this.value = value;
		this.info = info;
	}
	
	public static PackageType findPackageType(int value){
		for(PackageType packageType:PackageType.values()){
			if(packageType.value==value){
				return packageType;
			}
		}
		return null;
	}
}
