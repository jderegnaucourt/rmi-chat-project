package iad.eventprog;

public class ImageChangedEvent {
	protected String _nameOfNewImage;

	public ImageChangedEvent(int current) {
		_nameOfNewImage = String.valueOf(current);
	}
	
	public ImageChangedEvent(String current) {
		_nameOfNewImage = current;
	}

	public String getNameOfNewImage() {
		return _nameOfNewImage;
	}

	public void setNameOfNewImage(String _nameOfNewImage) {
		this._nameOfNewImage = _nameOfNewImage;
	}
}
