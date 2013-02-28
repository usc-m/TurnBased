package inf1.oop.turnbased.graphics;

public class RenderingParameters {
	private float xAxisOffset, yAxisOffset;
	
	public float getXOffset() { return xAxisOffset; }
	public float getYOffset() { return yAxisOffset; }
	
	public RenderingParameters() {
		xAxisOffset = yAxisOffset = 0;
	}
	
	public RenderingParameters setXOffset(float xOffset) {
		xAxisOffset = xOffset;
		return this;
	}
	
	public RenderingParameters setYOffset(float yOffset) {
		yAxisOffset = yOffset;
		return this;
	}
}
