package MainPackage;

public enum SPECIAL {
	
	Extratime("View/resources/BonusObjects/BonusTime.png","View/resources/Texts/hii.png"),
	Poison("View/resources/BonusObjects/poison.png","View/resources/Texts/Oops.png");
	private String idle,extraseconds;
	SPECIAL(String idle, String extraseconds){
		this.idle=idle;
		this.extraseconds=extraseconds;
	}
	public String getIdle() {
		return idle;
	}

	public String getExtraseconds() {
		return extraseconds;
	}


}
