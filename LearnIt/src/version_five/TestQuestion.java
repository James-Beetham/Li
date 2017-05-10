package version_five;

import java.util.LinkedList;

public class TestQuestion implements Question {
	private String data;
	public TestQuestion(String data) {
		
	}
	
	@Override
	public LinkedList<Stat> getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ask(Engine e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getKeywords() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		return data;
	}

}
