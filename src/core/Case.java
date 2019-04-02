package core;

public class Case {
	
	public enum STATUS
	{
		PLACER,
		APPUIYER,
		RATE,
		TOUCHE;
	}
	
	private STATUS status;
	
	public Case() {
		status = STATUS.PLACER;
	}
	
	public STATUS setStatus(STATUS Status){
		this.status = Status;
		return status;
	}
	
	public STATUS getStatus(){
		return status;
	}
}
